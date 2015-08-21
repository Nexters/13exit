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
import android.widget.Button;

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

    private Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_req);

        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
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
