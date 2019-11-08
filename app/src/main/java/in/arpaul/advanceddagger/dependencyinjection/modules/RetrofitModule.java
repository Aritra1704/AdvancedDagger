package in.arpaul.advanceddagger.dependencyinjection.modules;

import dagger.Module;
import dagger.Provides;
import in.arpaul.advanceddagger.dependencyinjection.customannotations.RepositoryScope;
import in.arpaul.advanceddagger.webservices.APICall;
import in.arpaul.advanceddagger.webservices.RetrofitService;
import retrofit2.Retrofit;

@Module
public class RetrofitModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit
     * @return
     */
    @Provides
    @RepositoryScope
    public APICall providePostApi(Retrofit retrofit) {
      return retrofit.create(APICall.class);
    }


    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @RepositoryScope
    public Retrofit provideRetrofitInterface() {
        return new RetrofitService().retrofit;
    }
}
