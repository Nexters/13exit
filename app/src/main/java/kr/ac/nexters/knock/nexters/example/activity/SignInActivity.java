package kr.ac.nexters.knock.nexters.example.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.ac.nexters.knock.R;

public class SignInActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.signinlayout);
	}
	
	public void onClick(View v){
//		TelephonyManager telemamanger = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//		String phone = telemamanger.getSimSerialNumber();;
//		String name = null;
//		String UID = null; 
//		String PUID = null;
//		String IMG = null;
//		
//		NetworkModel.getInstance().addUser(phone, name, UID, PUID, IMG, new OnNetworkResultListener<IsSucceed>() {
//			
//			public void onResult(IsSucceed result) {
//				// TODO Auto-generated method stub
//				logIn();
//			}
//			
//			public void onFail(int code) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		logIn();
	}

	public void logIn(){
//		SharedPreferences pref = getSharedPreferences("login", Activity.MODE_PRIVATE);
//		SharedPreferences.Editor editor = pref.edit();
//		editor.putBoolean("autoLogin", true);
//		editor.commit();
		finish();
	}

	@Override
	public void onBackPressed() {
	}
}
