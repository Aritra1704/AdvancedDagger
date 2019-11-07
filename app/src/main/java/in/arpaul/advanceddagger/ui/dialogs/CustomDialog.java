package in.arpaul.advanceddagger.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import in.arpaul.advanceddagger.R;

public class CustomDialog extends BaseDialog {

    private String strTitle;
    private String strMessage;
    private String positiveBtnName;
    private String negativeBtnName;
    private String neutralBtnName;
    private String from;
    private boolean isCancel;
    private AlertDialog alertDialog;
    private Context mContext;
    private DialogClick dialogClick;

    public CustomDialog(String strTitle,
                        String strMessage,
                        String positiveBtnName,
                        String negativeBtnName,
                        String neutralBtnName,
                        String from,
                        Boolean isCancelable,
                        DialogClick dialogClick) {
        try {
            mContext = getActivity();
            this.strTitle = strTitle;
            this.strMessage = strMessage;
            this.positiveBtnName = positiveBtnName;
            this.negativeBtnName = negativeBtnName;
            this.neutralBtnName = neutralBtnName;
            this.isCancel = isCancelable;
            this.dialogClick = dialogClick;
            if (!TextUtils.isEmpty(from))
                this.from = from;
            else
                this.from = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        if(!TextUtils.isEmpty(positiveBtnName))
            builder.setPositiveButton(positiveBtnName, (dialog, which) -> {
                dialogClick.dialogYesClick(from.toString());
                dialog.dismiss();
        });
        if(!TextUtils.isEmpty(negativeBtnName))
            builder.setNegativeButton(negativeBtnName, ( dialog, which ) -> {
                dialogClick.dialogNoClick(from.toString());
                dialog.dismiss();
        });
        if(!TextUtils.isEmpty(neutralBtnName))
            builder.setNeutralButton(neutralBtnName, (dialog, which) -> {

                dialogClick.dialogNeutralClick(from.toString());
                dialog.dismiss();
        });
        builder.setCancelable(isCancel);
        alertDialog = builder.create();

        return alertDialog;
    }

    public Boolean isShowing() {
        if(alertDialog != null && alertDialog.isShowing())
            return true;
        return false;
    }

    public void dismissDialog() {
        alertDialog.dismiss();
    }

    public interface DialogClick {
        public void dialogYesClick(String from);
        public void dialogNeutralClick(String from);
        public void dialogNoClick(String from);
    }
}
