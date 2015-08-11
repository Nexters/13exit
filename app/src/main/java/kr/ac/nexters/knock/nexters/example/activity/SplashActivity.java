package kr.ac.nexters.knock.nexters.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import kr.ac.nexters.knock.R;

//???¡À???
public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
             
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                
             Intent intent = new Intent(SplashActivity.this, MainActivity.class);
             startActivity(intent);finish();
            }
        };
        
        handler.sendEmptyMessageDelayed(0, 3000);
	}
	
}
