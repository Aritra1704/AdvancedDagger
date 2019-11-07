package in.arpaul.advanceddagger.ui.dialogs;

import android.os.Bundle;

import java.util.ArrayList;

public class DialogsFactory {
    public SingleClickDialog singleClickDialog(String title, ArrayList<String> data, Boolean isCancelable) {
        Bundle args = new Bundle(3);
        args.putString(SingleClickDialog.ARG_TITLE, title);
        args.putStringArrayList(SingleClickDialog.ARG_DATA, data);
        args.putBoolean(SingleClickDialog.ARG_ISCANCEL, isCancelable);

        SingleClickDialog singleClickDialog = new SingleClickDialog();
        singleClickDialog.setArguments(args);

        return singleClickDialog;
    }

    public CustomDialog customDialog(String strTitle,
                                     String strMessage,
                                     String positiveBtnName,
                                     String negativeBtnName,
                                     String neutralBtnName,
                                     String from,
                                     Boolean isCancelable,
                                     CustomDialog.DialogClick dialogClick) {

        return new CustomDialog(strTitle, strMessage, positiveBtnName, negativeBtnName, neutralBtnName, from, isCancelable, dialogClick);
    }

    public ProgressDialog progressDialog(String strTitle, String strMessage, boolean isCancelable, boolean isCustom) {

        return new ProgressDialog(strTitle, strMessage, isCancelable, isCustom);
    }

    public ProgressDialog progressDialog(String strTitle, String strMessage, int progress, boolean isCancelable, boolean isCustom) {

        return new ProgressDialog(strTitle, strMessage, progress, isCancelable, isCustom);
    }

    public ProgressDialog progressDialog(Boolean isCancelable) {

        return new ProgressDialog(isCancelable);
    }
}
