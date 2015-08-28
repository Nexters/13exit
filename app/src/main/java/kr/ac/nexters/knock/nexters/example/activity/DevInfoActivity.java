package kr.ac.nexters.knock.nexters.example.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.menu.SettingActivity;

public class DevInfoActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_info);

        //hide actionbar
        getSupportActionBar().hide();

        LinearLayout devinfo = (LinearLayout) findViewById(R.id.dev_info);
        devinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(DevInfoActivity.this, SettingActivity.class);
//                startActivity(i);
                finish();
            }
        });
    }
}