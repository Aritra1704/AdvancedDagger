package in.arpaul.advanceddagger.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SingleClickDialog extends BaseDialog {

    public static final String ARG_TITLE = "ARG_TITLE";
    public static final String ARG_DATA = "ARG_DATA";
    public static final String ARG_ISCANCEL = "ARG_ISCANCEL";

    public SingleClickDialog newInstance(String title, ArrayList<String> data) {
        Bundle args = new Bundle();
        args.putSerializable("title", title);
        args.putStringArrayList("data", data);
        SingleClickDialog fragment = new SingleClickDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString(ARG_TITLE);
        ArrayList<String> data = getArguments().getStringArrayList(ARG_DATA);
        boolean isCancel = getArguments().getBoolean(ARG_ISCANCEL);

        CharSequence[] charSequences = new CharSequence[data.size()];
        for (int i = 0; i < data.size(); i++)
            charSequences[i] = data.get(i);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setCancelable(isCancel)
                .setItems(charSequences, (dialog, which) -> {
                        mListener.onSelect(which);
                })
                .create();
    }

    private OnSelectedListener mListener = null;

    public void setOnSelectedListener(OnSelectedListener listener) {
        mListener = listener;
    }

    public interface OnSelectedListener {
        public void onSelect(int position);
    }
}
