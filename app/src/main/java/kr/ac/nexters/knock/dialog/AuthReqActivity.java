package kr.ac.nexters.knock.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.network.IsSuccess;
import kr.ac.nexters.knock.network.NetworkModel;
import kr.ac.nexters.knock.nexters.example.activity.AuthActivity;
import kr.ac.nexters.knock.nexters.example.activity.MainActivity;
import kr.ac.nexters.knock.nexters.example.activity.SignUpActivity;
import kr.ac.nexters.knock.nexters.example.activity.SplashActivity;
import kr.ac.nexters.knock.nexters.example.activity.TutorialActivity;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class AuthReqActivity extends Activity {

    private Button btn_accept;
    private TextView authreq_tv;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        setContentView(R.layout.activity_auth_req);

        this.setFinishOnTouchOutside(false);

        intent = this.getIntent();
        String partnerName = intent.getStringExtra("pname");
        PreferenceManager.getInstance().setPname(partnerName);

        authreq_tv = (TextView)findViewById(R.id.authReq_tv);
        SpannableString message = new SpannableString(partnerName + "님이\n연결을 요청하셨습니다.\n수락하시겠습니까?");
        message.setSpan(new ForegroundColorSpan(Color.rgb(255,138,101)), 0, partnerName.length(), 0);
        message.setSpan(new ForegroundColorSpan(Color.rgb(77,77,77)), partnerName.length()+1, message.length(), 0);

        authreq_tv.setText(message, TextView.BufferType.SPANNABLE);
        btn_accept = (Button)findViewById(R.id.authReq_btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(AuthReqActivity.this, TutorialActivity.class);
                startActivity(in);
                finish();

                PreferenceManager.getInstance().setFirst("needTutorial");

                NetworkModel.getInstance().authAccept(new NetworkModel.OnNetworkResultListener<IsSuccess>() {
                    @Override
                    public void onResult(IsSuccess result) {

                    }

                    @Override
                    public void onFail(int code) {

                    }
                });
            }
        });

    }
}
