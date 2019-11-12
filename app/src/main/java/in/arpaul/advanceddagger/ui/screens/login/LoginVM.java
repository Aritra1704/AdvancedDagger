package in.arpaul.advanceddagger.ui.screens.login;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import in.arpaul.advanceddagger.R;
import in.arpaul.advanceddagger.models.ApiResponse;
import in.arpaul.advanceddagger.models.requests.LoginRequest;
import in.arpaul.advanceddagger.models.responses.UserResponse;
import in.arpaul.advanceddagger.utils.ResourceString;
import in.arpaul.advanceddagger.utils.SingleLiveEvent;
import in.arpaul.advanceddagger.viewmodels.BaseVM;

public class LoginVM extends BaseVM {
    public MutableLiveData<Integer> showLoader = new MutableLiveData<>();
    public LiveData<UserResponse> usersLD;
    public MutableLiveData<ApiResponse<UserResponse>> usersResponse = new MutableLiveData<ApiResponse<UserResponse>>();
    public MutableLiveData<LoginRequest> userRequest = new MutableLiveData<LoginRequest>();
    public SingleLiveEvent loadData = new SingleLiveEvent();
    public SingleLiveEvent<ResourceString> toastMessage = new SingleLiveEvent<ResourceString>();
    public ObservableField<TextWatcher> userIdWatcher = new ObservableField<TextWatcher>();
    public ObservableField<TextWatcher> passwordWatcher = new ObservableField<TextWatcher>();

    public LoginVM() {
        LoginRepoImpl loginRepo = new LoginRepoImpl();// = baseRepo
        userRequest = new MutableLiveData();
        loginRepo.performLogin(userRequest.getValue(), usersResponse);
        usersLD = Transformations.switchMap(loadData, new Function<ApiResponse<UserResponse>, LiveData<UserResponse>>() {
            @Override
            public LiveData<UserResponse> apply(ApiResponse<UserResponse> input) {
                MutableLiveData<UserResponse> result = new MutableLiveData<UserResponse>();
                if(input instanceof ApiResponse.ApiIsLoading) {
                    showLoader.setValue(View.VISIBLE);
                } else if(input instanceof ApiResponse.ApiSuccessResponse) {
                    result.setValue(((ApiResponse.ApiSuccessResponse<UserResponse>) input).getBody());
                    showLoader.setValue(View.GONE);
                } else if(input instanceof ApiResponse.ApiErrorResponse) {
//                    result.setValue(((ApiResponse.ApiErrorResponse<UserResponse>) input).getError());
                    showLoader.setValue(View.GONE);
                } else {
                    showLoader.setValue(View.GONE);
                }
                return result;
            }
        });
        userIdWatcher.set(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                LoginRequest user = userRequest.getValue();
                user.setUserId(editable.toString());
                userRequest.setValue(user);
            }
        });
        passwordWatcher.set(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                LoginRequest user = userRequest.getValue();
                user.setPassword(editable.toString());
                userRequest.setValue(user);
            }
        });
    }

//    @Override
//    public void performLogin(View view) {
//        if(validateUserId())
//            loadData.call();
//    }

    public void performLogin() {
        if(validateUserId())
            loadData.call();
    }

    private boolean validateUserId() {
        LoginRequest user = userRequest.getValue();
        boolean result = true;
        if(TextUtils.isEmpty(user.getUserId())) {
            toastMessage.setValue(new ResourceString.IdResourceString(R.string.err_userid_empty));
            result = false;
        } else if(TextUtils.isEmpty(user.getPassword())) {
            toastMessage.setValue(new ResourceString.IdResourceString(R.string.err_password_empty));
            result = false;
        }
        return result;
    }

    public void getUserId() {

    }
}
