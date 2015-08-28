package kr.ac.nexters.knock.tools;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by KimCP on 15. 8. 28..
 */
public class BackPressCloseHandler {
    private long backKeyPressedTime = 0;

    private Toast toast;


    private Activity activity;


    public BackPressCloseHandler(Activity context) {

        this.activity = context;

    }


    public void onBackPressed() {

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {

            backKeyPressedTime = System.currentTimeMillis();

            showGuide();

            return;

        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {

            activity.moveTaskToBack(true);
            activity.finish();
            android.os.Process.killProcess(android.os.Process.myPid());

            toast.cancel();

        }

    }


    private void showGuide() {

        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.",

                Toast.LENGTH_SHORT);

        toast.show();

    }

}