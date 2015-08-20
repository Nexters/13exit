package kr.ac.nexters.knock.nexters.example.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.menu.BackgroundActivity;
import kr.ac.nexters.knock.menu.ProfileActivity;
import kr.ac.nexters.knock.menu.SettingActivity;

import kr.ac.nexters.knock.network.IsSuccess;
import kr.ac.nexters.knock.network.NetworkModel;
import kr.ac.nexters.knock.tools.MyApplication;
import kr.ac.nexters.knock.tools.PreferenceManager;
import kr.ac.nexters.knock.tools.RippleBackground;

public class MainActivity extends AppCompatActivity {

    ImageView myImage, partnerImage;
    String imagePath;
    LinearLayout background;
    ImageButton button_heart;
    NetworkModel instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //use ImageLoader
        //ImageLoader.getInstance().displayImage("URL",partnerImage, MyApplication.getDisplayImageOptions());

        imagePath = Environment.getDataDirectory().getAbsolutePath() + "/data/kr.ac.nexters.knock/files/";

        myImage = (ImageView) findViewById(R.id.main_myImg);
        partnerImage = (ImageView) findViewById(R.id.main_partnerImg);
        background = (LinearLayout) findViewById(R.id.main_background);
        button_heart = (ImageButton) findViewById(R.id.main_btn_heart);

        instance = NetworkModel.getInstance();

        Log.i("(MA)knock to ", PreferenceManager.getInstance().getPpushId());

        /*
        button_heart.setOnTouchListener(touchListener);
        button_heart.setClickable(true);
        button_heart.setOnDragListener(mDragListener);
        */

//        //layout wave animation test
//        animation = (LinearLayout) findViewById(R.id.animation);


        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.main_partnerRoom);
        final Handler handler=new Handler();
        button_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, PreferenceManager.getInstance().getPpushId(), Toast.LENGTH_SHORT).show();

                NetworkModel.getInstance().knock(new NetworkModel.OnNetworkResultListener<IsSuccess>() {
                    @Override
                    public void onResult(IsSuccess result) {

                    }

                    @Override
                    public void onFail(int code) {

                    }
                });

                // rippleBackground.startRippleAnimation();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rippleBackground.startRippleAnimation();
                    }
                }, 0);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


       ImageLoader.getInstance().displayImage("file:///storage/emulated/0//mine.jpg", myImage, MyApplication.getDisplayImageOptions());
//       ImageLoader.getInstance().displayImage("URL", partnerImage, MyApplication.getDisplayImageOptions());
    }

    // Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "프로필");
        menu.add(0, 2, 0, "배경화면");
        menu.add(0, 3, 0, "설정");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case 1:
                intent = new Intent(this, ProfileActivity.class);
                break;
            case 2:
                intent = new Intent(this, BackgroundActivity.class);
                break;
            case 3:
                intent = new Intent(this, SettingActivity.class);
                break;
        }
        startActivity(intent);
        return false;
    }
}