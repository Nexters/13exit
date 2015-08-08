package kr.ac.nexters.knock.nexters.example.activity;

import java.io.File;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import kr.ac.nexters.knock.R;

//�α׾ƿ��� �����Ǿ��ֽ��ϴ�. �Ŀ� �α׾ƿ� ����� ���� �� ���ƿ�.
public class SettingActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settinglayout);
	}
	
	
	//�α���, ���� ���� ��� false�� �ٲٰ� ���� ����
	public void logOut(View v){
		SharedPreferences pref = getSharedPreferences("login", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("autoLogin", false);
		editor.commit();
		
		pref = getSharedPreferences("auth", Activity.MODE_PRIVATE);
		editor = pref.edit();
		editor.putBoolean("havePartner", false);
		editor.commit();
		
		
		String imagePath = Environment.getDataDirectory().getAbsolutePath() + "/data/nexters.example.testknock00/files/";
		
		File file = new File(imagePath, "mine.jpg");
		if (file.exists()) {
			Log.i("file", "delete");
			file.delete();
		}

		File bgfile = new File(imagePath, "backGround.jpg");
		if (bgfile.exists()) {
			Log.i("exist", "file");
			bgfile.delete();				
		}		
		
		this.finish();
	}

}
