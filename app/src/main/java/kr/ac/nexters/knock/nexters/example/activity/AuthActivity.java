package kr.ac.nexters.knock.nexters.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.network.IsSuccess;
import kr.ac.nexters.knock.network.NetworkModel;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class AuthActivity extends ActionBarActivity {

    private Button btn_nextMove;
    private EditText et_inputPartnerNum, et_inputMyNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //hide actionbar
        getSupportActionBar().hide();

        setLayout();
    }

    public void setLayout(){
        //이거 예외처리 해야함. 문자 못받게 하고, 길이 체크하고
        et_inputMyNum = (EditText)findViewById(R.id.auth_tv_pnum_guid);
        et_inputMyNum.setText("010"+PreferenceManager.getInstance().getPhonenum());
        et_inputPartnerNum = (EditText)findViewById(R.id.auth_et_partner_number);
        et_inputPartnerNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    et_inputPartnerNum.setText("");
            }
        });
        btn_nextMove = (Button)findViewById(R.id.auth_btn_nextActivity);
        btn_nextMove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(AuthActivity.this, et_inputPartnerNum.getText().toString(), Toast.LENGTH_SHORT);

                String phone = et_inputPartnerNum.getText().toString();
                phone = phone.substring(phone.length()-8, phone.length());
                PreferenceManager.getInstance().setPphoneNum(phone);
                NetworkModel.getInstance().authSend(phone, new NetworkModel.OnNetworkResultListener<IsSuccess>() {
                    @Override
                    public void onResult(IsSuccess result) {
                        Toast.makeText(AuthActivity.this, "요청성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(int code) {
                        Toast.makeText(AuthActivity.this, "Server has problem", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
