package kr.ac.nexters.knock.nexters.example.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class TutorialActivity extends ActionBarActivity {

    private Button btn_moveNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        btn_moveNext = (Button)findViewById(R.id.tutorial_btn_nextActivity);
        btn_moveNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(TutorialActivity.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}
