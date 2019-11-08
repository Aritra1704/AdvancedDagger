package in.arpaul.advanceddagger.viewmodels;

import javax.inject.Inject;

import in.arpaul.advanceddagger.common.AppInstance;
import in.arpaul.advanceddagger.dependencyinjection.components.RetrofitComponent;
import in.arpaul.advanceddagger.dependencyinjection.modules.ControllerModule;
import in.arpaul.advanceddagger.dependencyinjection.modules.RetrofitModule;
import in.arpaul.advanceddagger.webservices.APICall;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseRepo {
    @Inject
    public APICall apiCall;

    public Disposable subscription;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    private RetrofitComponent injector;

    public BaseRepo() {
//        injector = ((AppInstance)getApplication()).getAppComponent().newRetrofitComponent(new RetrofitModule());
//        injector = DaggerRetrofitComponent
//                .builder()
//                .retrofitModule(new RetrofitModule())
//                .build();
        injector.inject(this);
    }
}
