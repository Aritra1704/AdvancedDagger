package in.arpaul.advanceddagger.common;

import android.app.Application;

import androidx.annotation.UiThread;

import javax.inject.Inject;

import in.arpaul.advanceddagger.dependencyinjection.components.AppComponent;
import in.arpaul.advanceddagger.dependencyinjection.components.DaggerAppComponent;
import in.arpaul.advanceddagger.dependencyinjection.modules.ApplicationModule;
import in.arpaul.advanceddagger.dependencyinjection.modules.PrefModule;
import in.arpaul.advanceddagger.utils.MyLogger;

public class AppInstance extends Application {
    private String TAG = AppInstance.class.getSimpleName();

    @Inject
    protected AppPreference appPref;

    @Inject
    protected MyLogger mLogger;

    private AppComponent component;

    @Override
    public void onCreate() {
        component = DaggerAppComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .prefModule(new PrefModule(this))
                .build();
        component.inject(this);
        super.onCreate();
    }

    @UiThread
    public AppComponent getAppComponent() {
        return component;
    }
}
