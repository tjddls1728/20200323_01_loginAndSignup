package kr.co.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import kr.co.a20200323_01_loginandsignup.databinding.ActivitySignUpBinding;

public class SignUpActivity extends baseActivity {

    ActivitySignUpBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        setupEvents();
        setupValues();

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setupValues() {

    }
}
