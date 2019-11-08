package in.arpaul.advanceddagger.dependencyinjection.components;

import dagger.Subcomponent;
import in.arpaul.advanceddagger.dependencyinjection.customannotations.RepositoryScope;
import in.arpaul.advanceddagger.dependencyinjection.modules.RetrofitModule;
import in.arpaul.advanceddagger.viewmodels.BaseRepo;
import in.arpaul.advanceddagger.webservices.APICall;

@RepositoryScope
@Subcomponent(modules = {RetrofitModule.class})
public interface RetrofitComponent {
    public void inject(BaseRepo baseRepo);

    public APICall getApiCalls();
}
