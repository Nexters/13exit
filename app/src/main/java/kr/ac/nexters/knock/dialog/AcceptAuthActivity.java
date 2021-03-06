package kr.ac.nexters.knock.dialog;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.nexters.example.activity.TutorialActivity;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class AcceptAuthActivity extends Activity {

    Button btn_accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        this.setFinishOnTouchOutside(false);

        setContentView(R.layout.activity_accept_auth);

        btn_accept = (Button)findViewById(R.id.auth_btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getInstance().setFirst("needTutorial");
                Intent in = new Intent(AcceptAuthActivity.this, TutorialActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}
