package in.arpaul.advanceddagger.dependencyinjection.components;

import dagger.Subcomponent;
import in.arpaul.advanceddagger.dependencyinjection.customannotations.UIScope;
import in.arpaul.advanceddagger.dependencyinjection.modules.ControllerModule;
import in.arpaul.advanceddagger.ui.dialogs.BaseDialog;
import in.arpaul.advanceddagger.ui.dialogs.DialogsFactory;
import in.arpaul.advanceddagger.ui.dialogs.DialogsManager;
import in.arpaul.advanceddagger.ui.screens.BaseActivity;

@UIScope
@Subcomponent(modules = {
        ControllerModule.class,
//        ViewModelFactoryModule.class,
//        ViewModelModule.class
        })
public interface ControllerComponent {
    public void injectActivity(BaseActivity activity);

    public DialogsManager getDialogManager();

    public DialogsFactory getDialogsFactory();

    public void injectDialog(BaseDialog dialog);
}
