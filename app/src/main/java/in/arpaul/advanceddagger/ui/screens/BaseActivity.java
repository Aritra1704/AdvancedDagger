package in.arpaul.advanceddagger.ui.screens;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arpaul.utilitieslib.PermissionUtils;
import com.arpaul.utilitieslib.ValidationUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import in.arpaul.advanceddagger.BuildConfig;
import in.arpaul.advanceddagger.R;
import in.arpaul.advanceddagger.common.AppConst;
import in.arpaul.advanceddagger.common.AppInstance;
import in.arpaul.advanceddagger.dependencyinjection.components.ControllerComponent;
import in.arpaul.advanceddagger.dependencyinjection.modules.ControllerModule;
import in.arpaul.advanceddagger.ui.dialogs.CustomDialog;
import in.arpaul.advanceddagger.ui.dialogs.DialogsFactory;
import in.arpaul.advanceddagger.ui.dialogs.DialogsManager;
import in.arpaul.advanceddagger.ui.dialogs.ProgressDialog;
import in.arpaul.advanceddagger.ui.dialogs.SingleClickDialog;

import static in.arpaul.advanceddagger.common.AppConst.PERM_READ_EXTERNAL_STORAGE;

public class BaseActivity extends AppCompatActivity implements CustomDialog.DialogClick {

    private ProgressDialog progressDialog;
    private CustomDialog customDialog;
    private SingleClickDialog singleDialog;
    private boolean mIsControllerComponentUsed = false;

    private DialogsManager mDialogsManager;
    private DialogsFactory mDialogsFactory;
    private ControllerComponent controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = getControllerComponent();
        controller.injectActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mDialogsFactory = controller.getDialogsFactory();
        mDialogsManager = controller.getDialogManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @UiThread
    protected ControllerComponent getControllerComponent() {
//        check(!mIsControllerComponentUsed) { "must not use ControllerComponent more than once" }
        mIsControllerComponentUsed = true;
        return ((AppInstance)getApplication()).getAppComponent().newControllerComponent(new ControllerModule(this));
    }

    public boolean isGpsEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isGpsProviderEnabled;
    }

    public void vibrateDevice() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    public boolean checkIfReadExternalStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;
        if (new PermissionUtils().checkPermission(BaseActivity.this,
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}) == PackageManager.PERMISSION_GRANTED)
            return true;
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                BaseActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            new AlertDialog.Builder(this)
                    .setTitle("Hamlet")
                    .setMessage("Access required for reading external storage files")
                    .setPositiveButton("Ok", (dialog, which) -> {
                        new PermissionUtils().requestPermission(BaseActivity.this,
                                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERM_READ_EXTERNAL_STORAGE);
                    } ).create().show();
        } else {
            new PermissionUtils().requestPermission(BaseActivity.this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERM_READ_EXTERNAL_STORAGE);
        }
        return false;
    }

    public boolean isPhNoValid(String phoneNo) {
        return ValidationUtils.validatePhoneNumber(phoneNo);
    }

    public boolean isPasswordValid(String password) {
        return password.length() >= AppConst.PASSWORD_LENGTH;
    }

    public void showLoader(boolean isCancelable, String is_from) {
        try {
            progressDialog = mDialogsFactory.progressDialog(isCancelable);
            controller.injectDialog(progressDialog);
            mDialogsManager.showDialog(progressDialog, null);

        } catch (Exception e) {
//            Crashlytics.log(Log.ERROR, "Loader without title", is_from)
        }
    }

    /**
     * Shows Indefinite Progress Dialog.
     *
     * @param title
     * @param message
     * @param isCancelable
     */
    public void showLoader(String title, String message, boolean isCancelable) {
        try {
            progressDialog = mDialogsFactory.progressDialog(title, message, isCancelable, false);
            controller.injectDialog(progressDialog);
            mDialogsManager.showDialog(progressDialog, null);
        } catch (Exception e) {
//            Crashlytics.log(Log.ERROR, "Loader without title", is_from)
        }
    }

    /**
     * Shows Indefinite Progress Dialog.
     *
     * @param title
     * @param message
     * @param progress
     * @param isCancelable
     */
    public void showLoader(String title, String message, int progress, boolean isCancelable) {
        try {
            progressDialog = mDialogsFactory.progressDialog(title, message, progress, isCancelable, false);
            controller.injectDialog(progressDialog);
            mDialogsManager.showDialog(progressDialog, null);
        } catch (Exception e) {

        }
    }

    public void hideLoader() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismissDialog();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Shows Dialog with user defined buttons.
     *
     * @param title
     * @param message
     * @param okButton
     * @param noButton
     * @param from
     * @param isCancelable
     */
    public void showCustomDialog(
            String title,
            String message,
            String okButton,
            String noButton,
            String neutralButton,
            String from,
            boolean isCancelable
    ) {
        customDialog = mDialogsFactory.customDialog(title, message, okButton, noButton, neutralButton, from, isCancelable, this);
        controller.injectDialog(customDialog);
        mDialogsManager.showDialog(customDialog, null);
    }

    public void hideCustomDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (customDialog != null && customDialog.isShowing())
                    customDialog.dismissDialog();
            }
        });
    }

    /*
    Hide Window Keyboard.
     */
    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void dialogYesClick(String from) {
        if (from.equalsIgnoreCase("")) {

        }
    }

    @Override
    public void dialogNoClick(String from) {
        if (from.equalsIgnoreCase("")) {

        }
    }

    @Override
    public void dialogNeutralClick(String from) {
        if (from.equalsIgnoreCase("")) {

        }
    }

    /**
     * Shows Single click string list
     *
     * @param title
     * @param list
     * @param isCancelable
     * @param listener
     */
    public void showSingleClickListDialog(
            String title,
            ArrayList<String> list,
            boolean isCancelable,
            SingleClickDialog.OnSelectedListener listener
    ) {
        singleDialog = mDialogsFactory.singleClickDialog(title, list, isCancelable);
        singleDialog.setOnSelectedListener(listener);

        controller.injectDialog(singleDialog);
        mDialogsManager.showDialog(singleDialog, null);
    }

    public static ViewGroup getParentView(View v) {
        ViewGroup vg = null;

        if (v != null)
            vg = (ViewGroup) v.getRootView();

        return vg;
    }

    public static <T> Collection<T> filter(Collection<T> col, Predicate<T> predicate) {

        Collection<T> result = new ArrayList<T>();
//        if (col != null) {
//            for (T element : col) {
//                if (predicate.apply(element)) {
//                    result.add(element);
//                }
//            }
//        }
        return result;
    }

    public static void applyTypeface(ViewGroup v, Typeface f, int style) {
        if (v != null) {
            int vgCount = v.getChildCount();
            for (int i = 0; i < vgCount; i++) {
                if (v.getChildAt(i) == null) continue;
                if (v.getChildAt(i) instanceof ViewGroup)
                    applyTypeface((ViewGroup) v.getChildAt(i), f, style);
                else {
                    View view = v.getChildAt(i);
                    if (view instanceof TextView)
                        ((TextView) (view)).setTypeface(f, style);
                    else if (view instanceof EditText)
                        ((EditText) (view)).setTypeface(f, style);
                    else if (view instanceof Button)
                        ((Button) (view)).setTypeface(f, style);
                }
            }
        }
    }

    public int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int getScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public void sendDeviceDetails() {
        StringBuilder message = new StringBuilder();
        message.append("Board: ").append(android.os.Build.BOARD).append('\n');
        message.append("Brand: ").append(android.os.Build.BRAND).append('\n');
        message.append("Device: ").append(android.os.Build.DEVICE).append('\n');
        message.append("Host: ").append(android.os.Build.HOST).append('\n');
        message.append("ID: ").append(android.os.Build.ID).append('\n');
        message.append("Model: ").append(android.os.Build.MODEL).append('\n');
        message.append("Product: ").append(android.os.Build.PRODUCT).append('\n');

        //        logFireAnalytic(FIRE_DEVICE_DETAIL, message.toString(), FIRE_CT_TEXT);
    }

    public void shareApplication() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" +
                    BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            e.toString();
        }
    }
}
