package in.arpaul.advanceddagger.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import in.arpaul.advanceddagger.R;

public class ProgressDialog extends BaseDialog {
    private String strTitle;
    private String strMessage;
    private boolean isCancel = false;
    private boolean isCustom = false;
    private int progress = 0;
    private Context mContext = null;
    private Dialog dialogLoader = null;

    private TextView tvLoadHead;
    private TextView tvLoadMsg;

    private View dialogView;

    public ProgressDialog(Boolean isCancelable) {
        this.mContext = getActivity();
        this.strTitle = null;
        this.strMessage = null;
        this.progress = 0;
        this.isCancel = isCancelable;
    }

    public ProgressDialog(String strTitle, String strMessage, Boolean isCancelable, Boolean isCustom) {
        this.mContext = getActivity();
        this.strTitle = strTitle;
        this.strMessage = strMessage;
        this.progress = 0;
        this.isCancel = isCancelable;
        this.isCustom = isCustom;
    }

    public ProgressDialog(String strTitle, String strMessage, int progress, boolean isCancelable, boolean isCustom) {
        this.mContext = getActivity();
        this.strTitle = strTitle;
        this.strMessage = strMessage;
        this.progress = progress;
        this.isCancel = isCancelable;
        this.isCustom = isCustom;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(isCustom) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.custom_loader, null);

            if (TextUtils.isEmpty(strTitle))
                strTitle = getString(R.string.please_wait);
            if(TextUtils.isEmpty(strMessage))
                strMessage = getString(R.string.loading);

            tvLoadHead = view.findViewById(R.id.tvLoadHead);
            tvLoadMsg = view.findViewById(R.id.tvLoadMsg);
            tvLoadHead.setText(strTitle);
            tvLoadMsg.setText(strMessage);
            builder.setCustomTitle(view);
        } else {
            builder.setTitle(strTitle);
            builder.setMessage(strMessage);
        }
        builder.setCancelable(isCancel);

        dialogLoader = builder.create();
        return dialogLoader;
    }

    public Boolean isShowing() {
        if(dialogLoader != null && dialogLoader.isShowing())
            return true;
        return false;
    }

    public void dismissDialog() {
        dialogLoader.dismiss();
    }
}
