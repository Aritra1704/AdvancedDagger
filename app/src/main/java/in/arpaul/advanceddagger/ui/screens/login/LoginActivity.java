package in.arpaul.advanceddagger.ui.screens.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import javax.inject.Inject;

import in.arpaul.advanceddagger.R;
import in.arpaul.advanceddagger.databinding.ActivityLoginBinding;
import in.arpaul.advanceddagger.ui.screens.BaseActivity;
import in.arpaul.advanceddagger.viewmodels.BaseViewModelFactory;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;
    private LoginVM loginVM;
    @Inject
    BaseViewModelFactory baseViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
//        setContentView(R.layout.activity_login);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
//        loginVM = ViewModelProviders.of(this@LoginActivity, BaseViewModelFactory()).get(LoginVM::class.java)
        loginVM = ViewModelProviders.of(this, baseViewModelFactory).get(LoginVM.class);
        binding.setVm(loginVM);
    }
}
