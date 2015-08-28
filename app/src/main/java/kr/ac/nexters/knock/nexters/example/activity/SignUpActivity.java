package kr.ac.nexters.knock.nexters.example.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.UUID;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.gcm.QuickstartPreferences;
import kr.ac.nexters.knock.gcm.RegistrationIntentService;
import kr.ac.nexters.knock.network.IsSuccess;
import kr.ac.nexters.knock.network.NetworkModel;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class SignUpActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "SignUpActivity";

    private Context mContext;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private Button btn_moveNext;
    private EditText et_inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //hide actionbar
        getSupportActionBar().hide();

        mContext = getApplicationContext();
        registBroadcastReceiver();

        setLayout();
        loadPushToken();
    }

    private void setLayout(){
        et_inputName = (EditText)findViewById(R.id.signup_et_inputname);
        et_inputName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    et_inputName.setText("");
            }
        });
        et_inputName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_inputName.getWindowToken(), 0);
                return true;
            }
        });
        btn_moveNext = (Button)findViewById(R.id.signup_btn_nextActivity);
        btn_moveNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!PreferenceManager.getInstance().getPushId().equals("")) {
                    addUser(et_inputName.getText().toString());
                    PreferenceManager.getInstance().setUserName(et_inputName.getText().toString());
                }else {
                    Toast.makeText(mContext, "토큰이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadPushToken() {
        getInstanceIdToken();
    }

    /**
     * LocalBroadcast 리시버를 정의한다. 토큰을 획득하기 위한 READY, GENERATING, COMPLETE 액션에 따라 UI에 변화를 준다.
     */
    public void registBroadcastReceiver(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();


               if(action.equals(QuickstartPreferences.REGISTRATION_COMPLETE)){
                    String token = intent.getStringExtra("token");
                    Log.i("token", token);

                    //add push id to shared preference;
                    PreferenceManager.getInstance().setPushId(token);
                }

            }
        };
    }

    public void addUser(String userName) {
        Log.i(TAG,"add user click");
        //get phone number;
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNum = tm.getLine1Number();
        phoneNum = phoneNum.substring(phoneNum.length()-8, phoneNum.length());
        Log.i("phone", phoneNum);
        PreferenceManager.getInstance().setPhonenum(phoneNum);

        //get device Id (it's mean UID);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();

        PreferenceManager.getInstance().setUid(deviceId);

        //get Push id
        String token = PreferenceManager.getInstance().getPushId();

        NetworkModel.getInstance().addUser(userName, phoneNum, deviceId, token, new NetworkModel.OnNetworkResultListener<IsSuccess>() {

            @Override
            public void onResult(IsSuccess result) {
                Toast.makeText(mContext, result.getResult() + "입니다.", Toast.LENGTH_SHORT).show();
                if(result.getResult().equals("success")){
                    Intent in = new Intent(SignUpActivity.this, AuthActivity.class);
                    startActivity(in);
                    finish();
                    PreferenceManager.getInstance().setFirst("reg");
                }
            }

            @Override
            public void onFail(int code) {
                Toast.makeText(mContext,"Server has problem", Toast.LENGTH_SHORT).show();
            }
        });

    };

    /**
     * 앱이 실행되어 화면에 나타날때 LocalBoardcastManager에 액션을 정의하여 등록한다.
     */
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_READY));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_GENERATING));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    /**
     * 앱이 화면에서 사라지면 등록된 LocalBoardcast를 모두 삭제한다.
     */
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }


    /**
     * Google Play Service를 사용할 수 있는 환경이지를 체크한다.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Instance ID를 이용하여 디바이스 토큰을 가져오는 RegistrationIntentService를 실행한다.
     */
    public void getInstanceIdToken() {
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }
}
