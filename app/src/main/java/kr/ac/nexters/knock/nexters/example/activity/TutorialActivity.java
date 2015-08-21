package kr.ac.nexters.knock.nexters.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class TutorialActivity extends AppCompatActivity {

    private static final float DRAG_THRESHOLD = 10.f;

    private ViewFlipper mViewFlipper;

    private static int check=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        //hide actionbar
        getSupportActionBar().hide();

        //skip
        findViewById(R.id.tut_01).setOnClickListener(mClickListener);
        findViewById(R.id.tut_02).setOnClickListener(mClickListener);
        findViewById(R.id.tut_03).setOnClickListener(mClickListener);
        findViewById(R.id.tut_04).setOnClickListener(mClickListener);
        findViewById(R.id.tut_05).setOnClickListener(mClickListener);
        findViewById(R.id.tut_06).setOnClickListener(mClickListener);

        mViewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);

    }

    //skip btn
    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(TutorialActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    };

    private Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromRight.setDuration(400);
        return inFromRight;
    }

    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        outtoLeft.setDuration(400);
        return outtoLeft;
    }

    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromLeft.setDuration(400);
        return inFromLeft;
    }

    public static Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );
        outtoRight.setDuration(400);
        return outtoRight;
    }

    private float mOldTouchX;

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mOldTouchX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();
                if (!isDragging(mOldTouchX, currentX)) return false;
                if (mOldTouchX < currentX)
                    goToPrevPage();
                else if (mOldTouchX > currentX)
                    goToNextPage();
                if(check==5){
                    Intent i = new Intent(TutorialActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                break;
        }
        return false;
    }

    private void goToNextPage() {
        mViewFlipper.setInAnimation(inFromRightAnimation());
        mViewFlipper.setOutAnimation(outToLeftAnimation());
        mViewFlipper.showPrevious();
        check++;
    }

    private void goToPrevPage() {
        mViewFlipper.setInAnimation(inFromLeftAnimation());
        mViewFlipper.setOutAnimation(outToRightAnimation());
        mViewFlipper.showNext();
        check--;
    }

    private boolean isDragging(float oldX, float currentX) {
        return Math.abs(oldX - currentX) > DRAG_THRESHOLD;
    }

    @Override
    protected void onStop() {
        super.onStop();
        PreferenceManager.getInstance().setFirst("regok");
    }
}
