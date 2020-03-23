package kr.co.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.a20200323_01_loginandsignup.Util.ContextUtil;
import kr.co.a20200323_01_loginandsignup.Util.ServerUtil;
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

//        체크박스에 체크가 될떄(변화가 있을때)마다
//        체크여부 저장.

        binding.idCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



//                ContextUtil을 이용해서, 체크 여부를 저장.

                ContextUtil.setIdCheck(mContext, isChecked);



            }

        });

//        로그인 버튼을 누르면 => 아이디 저장이 체크되어 있다면
//        =>입력되어있는 이메일을 저장
//        그렇지 않다면 => 이메일을 빈칸 "" 으로 저장


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                체크박스에 체크가 되어있나?
                if (binding.idCheckBox.isChecked()){

//                    체크가 되어있는 상황
                    String inputEmail = binding.emailEdt.getText().toString();

                    ContextUtil.setEmail(mContext,inputEmail);
                }
                else {
//                    체크가 안된 상황
                    ContextUtil.setEmail(mContext,"");
                }


                String inputEmail = binding.emailEdt.getText().toString();
                String inputpw= binding.pwEdt.getText().toString();

                ServerUtil.postRequestLogin(mContext, inputEmail, inputpw, new ServerUtil.JsonResponseHandler() {

                    @Override

                    public void onResponse(JSONObject json) {

//                        응답실험코드는 -> 비동기 처리가 반드시 필요함
//                        비동기 : 다른 할 일들을 하다가, 완료 되면 별도로 실행 해주자
//                        Okhttp : 비동기 처리를 자동으로 지원 -> 별도 쓰레드가 알아서 진행
//                        => 이 onResponse는 다른 쓰레드가 돌리고 있다.
//                        UI동작은 메인쓰레드가 전용으로 처리함.
//                        => 다른 쓰레드가

                        Log.d("JSON내용-메인에서", json.toString());

                        try {
                            final String message = json.getString("message");
                            Log.d("서버가주는 메시지",message);

                            int code = json.getInt("code");
                            Log.d("서버가 주는 코드",code+"");

                            if (code == 200){
//                       해당 기능이 성공적으로 동작
//                                로그인 성공

                                JSONObject data = json.getJSONObject("data");
                                JSONObject user = data.getJSONObject("user");
                                String token = data.getString("token");

//                                로그인한 사람의 이름을 토스트로.
                                final String name = user.getString("name");
                                final String phone = user.getString("phone");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, String.format("%s/%s",name,phone), Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }
                            else {
//                                뭔가 문제가 있었다.

//                                Toast를 띄우는데 앱이 죽는다! => UI쓰레드
//                                조치: UITHread 안에서 토스트를 띄우도록 처리

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });





                    }
        });

    }

    @Override
    public void setupValues() {

//        App을 키면, 저장된 이메일 값을 emailEdt에 입력.
        binding.emailEdt.setText(ContextUtil.getEmail(mContext));

        binding.idCheckBox.setChecked(ContextUtil.isIdCheck(mContext));

    }
}
