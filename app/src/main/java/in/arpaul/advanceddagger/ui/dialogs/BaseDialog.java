package in.arpaul.advanceddagger.ui.dialogs;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatDialogFragment;

import in.arpaul.advanceddagger.common.AppInstance;
import in.arpaul.advanceddagger.dependencyinjection.components.ControllerComponent;
import in.arpaul.advanceddagger.dependencyinjection.modules.ControllerModule;

public class BaseDialog extends AppCompatDialogFragment {
    private boolean mIsControllerComponentUsed = false;

    @UiThread
    protected ControllerComponent getControllerComponent() {
//        check(!mIsControllerComponentUsed) { "must not use ControllerComponent more than once" }
        mIsControllerComponentUsed = true;
        return ((AppInstance) getActivity().getApplication())
            .getAppComponent().newControllerComponent(new ControllerModule(getActivity()));
    }

    /**
     * Get this dialog's ID that was supplied with a call to
     * [DialogsManager.showDialogWithId]
     * @return dialog's ID, or null if none was set
     */
    protected String getDialogId() {
        if (getArguments() == null) {
            return null;
        } else {
            return getArguments().getString(DialogsManager.ARGUMENT_DIALOG_ID);
        }
    }
}
