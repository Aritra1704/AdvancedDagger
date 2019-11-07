package in.arpaul.advanceddagger.dependencyinjection.modules;

import android.app.Application;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import in.arpaul.advanceddagger.common.AppPreference;
import in.arpaul.advanceddagger.dependencyinjection.customannotations.ApplicationScope;

@Module
public class PrefModule {
    private Application application;

    @Inject
    public PrefModule(Application application) {
        this.application = application;
    }

    @ApplicationScope
    @Provides
    public AppPreference provideAppPreference() {
        return new AppPreference(application);
    }
}
