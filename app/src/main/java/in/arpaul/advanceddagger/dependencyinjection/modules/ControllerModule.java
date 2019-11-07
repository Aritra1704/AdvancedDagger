package in.arpaul.advanceddagger.dependencyinjection.modules;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import in.arpaul.advanceddagger.ui.dialogs.DialogsFactory;
import in.arpaul.advanceddagger.ui.dialogs.DialogsManager;

@Module
public class ControllerModule {
    private FragmentActivity mActivity;
    public ControllerModule(FragmentActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    public Context getContext() {
        return mActivity;
    }

    @Provides
    public Activity getActivity() {
        return mActivity;
    }

    @Provides
    public FragmentManager getFragmentManager(){
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    public DialogsManager getDialogManager(FragmentManager fragmentManager) {
        return new DialogsManager(fragmentManager);
    }

    @Provides
    public DialogsFactory getDialogsFactory() {
        return new DialogsFactory();
    }
}
