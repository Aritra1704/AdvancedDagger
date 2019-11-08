package in.arpaul.advanceddagger.ui.screens.login;

import androidx.lifecycle.MutableLiveData;

import in.arpaul.advanceddagger.models.ApiResponse;
import in.arpaul.advanceddagger.models.requests.LoginRequest;
import in.arpaul.advanceddagger.models.responses.UserResponse;
import in.arpaul.advanceddagger.viewmodels.BaseRepo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginRepoImpl extends BaseRepo implements LoginRepo {
    @Override
    public void performLogin(LoginRequest value, MutableLiveData<ApiResponse<UserResponse>> usersResponse) {
        subscription = apiCall.performLogin("", value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable ->
                            usersResponse.setValue(ApiResponse.createLoadingResponse()))
                .subscribe(userResponseApiResponse -> {
                            usersResponse.setValue(userResponseApiResponse);
                        }, throwable -> {
                            usersResponse.setValue(ApiResponse.create(throwable));
                        });
        compositeDisposable.add(subscription);
    }
}
