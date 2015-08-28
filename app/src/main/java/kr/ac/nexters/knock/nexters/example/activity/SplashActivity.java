package kr.ac.nexters.knock.nexters.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
             
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                //new user
                if(PreferenceManager.getInstance().getFirst().equals("")){
                    Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }
                //remain user (doesn't reg)
                else if(PreferenceManager.getInstance().getFirst().equals("reg")){
                    Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                    startActivity(intent);
               }
                //remain user (doesn't reg)
                else if(PreferenceManager.getInstance().getFirst().equals("needTutorial")){
                    Intent intent = new Intent(SplashActivity.this, TutorialActivity.class);
                    startActivity(intent);
                }
                //remain user
                else if(PreferenceManager.getInstance().getFirst().equals("regok")) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        };
        
        handler.sendEmptyMessageDelayed(0, 2000);
	}
	
}
