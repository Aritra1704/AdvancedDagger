package in.arpaul.advanceddagger.ui.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import in.arpaul.advanceddagger.BuildConfig;
import in.arpaul.advanceddagger.R;
import in.arpaul.advanceddagger.ui.screens.BaseActivity;
import in.arpaul.advanceddagger.ui.screens.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    private final String TAG = SplashActivity.class.getSimpleName();
    int timeout = 1500;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvText = findViewById(R.id.tvText);
    }

    private void moveToLogin() {
        if (!BuildConfig.DEBUG)
            timeout = 1500;

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }, timeout);

    }

    public void onClicked(View view) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Test1");
        list.add("Test2");
        list.add("Test3");

        showSingleClickListDialog("Test", list, false, (position) -> {
                    tvText.setText(list.get(position));
                });

//        showLoader("Test", "Loading", false)

//        showCustomDialog("Test","Test message","ok"," cancel","neutral","test",false)
    }
}
