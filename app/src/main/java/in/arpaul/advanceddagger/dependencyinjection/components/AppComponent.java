package in.arpaul.advanceddagger.dependencyinjection.components;

import android.app.Application;

import dagger.Component;
import in.arpaul.advanceddagger.common.AppInstance;
import in.arpaul.advanceddagger.common.AppPreference;
import in.arpaul.advanceddagger.dependencyinjection.customannotations.ApplicationScope;
import in.arpaul.advanceddagger.dependencyinjection.modules.ApplicationModule;
import in.arpaul.advanceddagger.dependencyinjection.modules.ControllerModule;
import in.arpaul.advanceddagger.dependencyinjection.modules.PrefModule;

@ApplicationScope
@Component(modules = {
        ApplicationModule.class,
        PrefModule.class
})
public interface AppComponent {
    public void inject(AppInstance appInstance);

    public AppPreference getAppPref();

    public Application getApplication();

    public ControllerComponent newControllerComponent(ControllerModule controllerModule);
}
