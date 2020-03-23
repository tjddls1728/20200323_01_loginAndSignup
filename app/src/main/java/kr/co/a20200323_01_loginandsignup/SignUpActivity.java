package kr.co.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.a20200323_01_loginandsignup.Util.ServerUtil;
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

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ID / 비번 / 이름 / 폰번을 따와서 => 서버로 전달
//                회원가입 API에 전달
                String inputId = binding.idEdt.getText().toString();
                String inputPw = binding.pwEdt.getText().toString();
                String inputName = binding.nameEdt.getText().toString();
                String inputPhone = binding.phoneEdt.getText().toString();

                ServerUtil.putRequestLogin(mContext, inputId, inputPw, inputName, inputPhone, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("회원가입",json.toString());

//                        응용문제 : 회원가입 성공? "이름"님 환영합니다! 토스트 출력
//                        실패? 서버에서 주는 message를 그대로 Toast로 출력력

                        try {
                            int code = json.getInt("code");

                            if (code == 200){

                                JSONObject data=json.getJSONObject("data");
                                JSONObject user=json.getJSONObject("user");


                                final String name = user.getString("name");


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, String.format("%s님 환영합니다!"), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                            else {
                                final String message = json.getString("message");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }                });

            }
        });



    }



    @Override
    public void setupValues() {

    }
}
