package kr.ac.nexters.knock.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
        setContentView(R.layout.activity_auth_req);

        intent = this.getIntent();
        PreferenceManager.getInstance().setPname(intent.getStringExtra("pname"));

        authreq_tv = (TextView)findViewById(R.id.authReq_tv);
        authreq_tv.setText(PreferenceManager.getInstance().getPname() + "님이 연결을 요청하셨습니다.\n수락하시겠습니까?");

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
