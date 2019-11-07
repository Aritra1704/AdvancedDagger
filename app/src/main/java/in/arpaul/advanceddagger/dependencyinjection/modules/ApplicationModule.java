package in.arpaul.advanceddagger.dependencyinjection.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import in.arpaul.advanceddagger.dependencyinjection.customannotations.ApplicationScope;
import in.arpaul.advanceddagger.utils.MyLogger;

@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @ApplicationScope
    public Application getApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    public MyLogger getLogger() {
        return new MyLogger(true);
    }
}
