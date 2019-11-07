package in.arpaul.advanceddagger.ui.dialogs;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.UiThread;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

@UiThread
public class DialogsManager {

    public static final String DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT_TAG";
    public static final String ARGUMENT_DIALOG_ID = "ARGUMENT_DIALOG_ID";

    private FragmentManager mFragmentManager;
    private DialogFragment mCurrentlyShownDialog;

    public DialogsManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;

        // there might be some dialog already shown
        Fragment fragmentWithDialogTag = fragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG);
        if (fragmentWithDialogTag != null) {
            mCurrentlyShownDialog = (DialogFragment)fragmentWithDialogTag;
        }
    }

    /**
     * @return a reference to currently shown dialog, or null if no dialog is shown.
     */
    public DialogFragment getCurrentlyShownDialog() {
        return mCurrentlyShownDialog;
    }

    /**
     * Obtain the id of the currently shown dialog.
     * @return the id of the currently shown dialog; null if no dialog is shown, or the currently
     * shown dialog has no id
     */
    public String getCurrentlyShownDialogId() {
        if (mCurrentlyShownDialog == null ||
                mCurrentlyShownDialog.getArguments() == null ||
                !mCurrentlyShownDialog.getArguments().containsKey(ARGUMENT_DIALOG_ID)) {
            return null;
        } else {
            return mCurrentlyShownDialog.getArguments().getString(ARGUMENT_DIALOG_ID);
        }
    }

    /**
     * Check whether a dialog with a specified id is currently shown
     * @param id dialog id to query
     * @return true if a dialog with the given id is currently shown; false otherwise
     */
    public Boolean isDialogCurrentlyShown(String id) {
        String shownDialogId = getCurrentlyShownDialogId();
        return !TextUtils.isEmpty(shownDialogId) && shownDialogId == id;
    }

    /**
     * Dismiss the currently shown dialog. Has no effect if no dialog is shown. Please note that
     * we always allow state loss upon dismissal.
     */
    public void dismissCurrentlyShownDialog() {
        if (mCurrentlyShownDialog != null) {
            mCurrentlyShownDialog.dismissAllowingStateLoss();
            mCurrentlyShownDialog = null;
        }
    }

    /**
     * Show dialog and assign it a given "id". Replaces any other currently shown dialog.<br></br>
     * Note that all dialogs implemented with DialogFragment and they will be committed allowing
     * state loss to prevent IllegalStateException.
     * @param dialog dialog to show
     * @param id string that uniquely identifies the dialog; can be null
     */
    public void showDialog(DialogFragment dialog, String id) {
        dismissCurrentlyShownDialog();
        setId(dialog, id);
        showDialog(dialog);
    }

    private void setId(DialogFragment dialog, String id) {
        Bundle args;
        if (dialog.getArguments() != null)
            args = dialog.getArguments();
        else
            args = new Bundle(1);
        args.putString(ARGUMENT_DIALOG_ID, id);
        dialog.setArguments(args);
    }

    private void showDialog(DialogFragment dialog) {
        mFragmentManager.beginTransaction()
                .add(dialog, DIALOG_FRAGMENT_TAG)
                .commitAllowingStateLoss();
        mCurrentlyShownDialog = dialog;
    }
}
