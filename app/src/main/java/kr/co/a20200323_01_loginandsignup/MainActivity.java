package kr.co.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import kr.co.a20200323_01_loginandsignup.Util.ContextUtil;
import kr.co.a20200323_01_loginandsignup.databinding.ActivityMainBinding;

public class MainActivity extends baseActivity {

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setupEvents();
        setupValues();


    }

    @Override
    public void setupEvents() {

//        로그인 버튼을 누르면 = 입력되어있는 이메일을 저장
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputEmail = binding.emailEdt.getText().toString();

                ContextUtil.setEmail(mContext,inputEmail);

            }
        });

    }

    @Override
    public void setupValues() {

//        App을 키면, 저장된 이메일 값을 emailEdt에 입력.
        binding.emailEdt.setText(ContextUtil.getEmail(mContext));

    }
}
