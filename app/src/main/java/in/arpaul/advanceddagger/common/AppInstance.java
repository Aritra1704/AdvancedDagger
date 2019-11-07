package in.arpaul.advanceddagger.common;

import android.app.Application;

import androidx.annotation.UiThread;

import javax.inject.Inject;

import in.arpaul.advanceddagger.dependencyinjection.components.AppComponent;
import in.arpaul.advanceddagger.utils.MyLogger;

public class AppInstance extends Application {
    private String TAG = AppInstance.class.getSimpleName();

    @Inject
    protected AppPreference appPref;

    @Inject
    protected MyLogger mLogger;

    private AppComponent component;
//    :  by lazy {
//        DaggerAppComponent
//                .builder()
//                .applicationModule(ApplicationModule(this))
//                .prefModule(PrefModule(this))
//                .build()
//    }


    @Override
    public void onCreate() {
        component.inject(this);
        super.onCreate();
    }

    @UiThread
    public AppComponent getAppComponent() {
        return component;
    }
}
