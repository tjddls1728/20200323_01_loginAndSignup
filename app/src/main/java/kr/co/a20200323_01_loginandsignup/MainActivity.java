package kr.co.a20200323_01_loginandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.co.a20200323_01_loginandsignup.databinding.ActivityMainBinding;

public class MainActivity extends baseActivity {

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
