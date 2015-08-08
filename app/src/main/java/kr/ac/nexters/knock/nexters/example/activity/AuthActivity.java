package kr.ac.nexters.knock.nexters.example.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.ac.nexters.knock.R;

public class AuthActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.authlayout);
	}

	public void onClick(View v){
//		String pphone = null;
//		String UID = null;
//		
//		NetworkModel.getInstance().auth(pphone, UID, new OnNetworkResultListener<IsSucceed>() {
//
//			public void onResult(IsSucceed result) {
//				// TODO Auto-generated method stub
//				PlogIn();
//			}
//
//			public void onFail(int code) {
//				// TODO Auto-generated method stub
//
//			}
//		});
		PlogIn(); //?? ????? ??????? ?????? ?? ???? ????
	}

	//????? ??????????? ?????????? boolean?? ??????? ?????? ????
	public void PlogIn(){
		SharedPreferences pref = getSharedPreferences("auth", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("havePartner", true);
		editor.commit();
		finish();
	}

	@Override
	public void onBackPressed() {
	}
}
