package kr.ac.nexters.knock.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class SettingActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settinglayout);

		//hide actionbar
		getSupportActionBar().hide();

		LinearLayout setPush = (LinearLayout)findViewById(R.id.setting_layout_push);
		LinearLayout withVib = (LinearLayout)findViewById(R.id.setting_layout_vib);
		LinearLayout appInfo = (LinearLayout)findViewById(R.id.setting_layout_appInfo);
		LinearLayout devInfo = (LinearLayout)findViewById(R.id.setting_layout_devInfo);
		LinearLayout delAccount = (LinearLayout)findViewById(R.id.setting_layout_delAccount);


		setPush.setOnClickListener(pushListener);
		withVib.setOnClickListener(vibListener);
		appInfo.setOnClickListener(appInfoListener);
		devInfo.setOnClickListener(devInfoListener);
		delAccount.setOnClickListener(delAccountListener);



		//전화번호 표시
		TextView myAccount = (TextView)findViewById(R.id.setting_tv_myAccount);
		myAccount.setText("my_account");

		TextView linkedAccount = (TextView)findViewById(R.id.setting_tv_linkedAccount);
		linkedAccount.setText("linked_account");

		LinearLayout btn_back = (LinearLayout) findViewById(R.id.title_backbtn);
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


	}


	//푸쉬 알림을 받을지 여부
	View.OnClickListener pushListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			ImageView btn_push = (ImageView)findViewById(R.id.setting_btn_push);

			if(PreferenceManager.getInstance().getPushUse()) {
				btn_push.setImageResource(R.mipmap.btn_no_check);
				PreferenceManager.getInstance().setPushUse(false);
			}else {
				btn_push.setImageResource(R.mipmap.btn_check);
				PreferenceManager.getInstance().setPushUse(true);
			}
		}
	};

	//사운드 알림 설정시 진동을 함께 울리기
	View.OnClickListener vibListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			ImageView btn_vib = (ImageView)findViewById(R.id.setting_btn_vib);

			if(PreferenceManager.getInstance().getWithVib()) {
				btn_vib.setImageResource(R.mipmap.btn_no_check);
				PreferenceManager.getInstance().setWithVib(false);
			}else {
				btn_vib.setImageResource(R.mipmap.btn_check);
				PreferenceManager.getInstance().setWithVib(true);
			}
		}
	};


	//어플리케이션 정보
	View.OnClickListener appInfoListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			Toast.makeText(SettingActivity.this, "appInfo", Toast.LENGTH_SHORT).show();
		}
	};

	//개발자 정보
	View.OnClickListener devInfoListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			Toast.makeText(SettingActivity.this, "devInfo", Toast.LENGTH_SHORT).show();
		}
	};


	//계정 삭제
	View.OnClickListener delAccountListener = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			Toast.makeText(SettingActivity.this, "delAccount", Toast.LENGTH_SHORT).show();
		}
	};
	

}
