package in.arpaul.advanceddagger.ui.screens.login;

import androidx.lifecycle.MutableLiveData;

import in.arpaul.advanceddagger.models.ApiResponse;
import in.arpaul.advanceddagger.models.requests.LoginRequest;
import in.arpaul.advanceddagger.models.responses.UserResponse;

public interface LoginRepo {
    public void performLogin(LoginRequest value, MutableLiveData<ApiResponse<UserResponse>> usersResponse);
}
