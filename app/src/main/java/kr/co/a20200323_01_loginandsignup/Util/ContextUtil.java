package kr.co.a20200323_01_loginandsignup.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

    private static final String prefName = "myPref";

//    항목명도 자동완성 지원 할수 있도록 미리 변수함
    private static final String EMAIL = "EMAIL";

//    해당 항목값을 저장<setter> / 조회 하는 메소드 두개<getter>

    public static void setEmail(Context context,String email){

//        메모장을 이용해서 값을 기록 => 메모장 파일부터 열어야함.
//        메모장은 Context를 이용해서 열아야함 -> Context 변수도 파라미터로 필요함

//        메모장을 열때 1) 어떤 메모장? 2)어떤 모드? - Context.MODE_PRIVATE
        SharedPreferences pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
//        열린 메모장에 항목/값 저장.
        pref.edit().putString(EMAIL,email).apply();

    }

}
