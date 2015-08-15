package kr.ac.nexters.knock.nexters.example.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.menu.BackgroundActivity;
import kr.ac.nexters.knock.menu.ProfileActivity;
import kr.ac.nexters.knock.menu.SettingActivity;
import kr.ac.nexters.knock.network.IsSucceed;
import kr.ac.nexters.knock.network.NetworkModel;
import kr.ac.nexters.knock.tools.PreferenceManager;
import kr.ac.nexters.knock.tools.RippleBackground;

public class MainActivity extends AppCompatActivity {

    ImageView myImage, partnerImage;
    String picturePath;
    static String imagePath;
    LinearLayout background;
    Button button_heart;
    LinearLayout animation;
    NetworkModel instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //use ImageLoader
        //ImageLoader.getInstance().displayImage("URL",partnerImage, MyApplication.getDisplayImageOptions());

        imagePath = Environment.getDataDirectory().getAbsolutePath();
        imagePath += "/data/kr.ac.nexters.knock/files/";

        myImage = (ImageView) findViewById(R.id.myImg);
        partnerImage = (ImageView) findViewById(R.id.partnerImg);
        background = (LinearLayout) findViewById(R.id.LinearLayout1);
        button_heart = (Button) findViewById(R.id.button_heart);

        /*
        button_heart.setOnTouchListener(touchListener);
        button_heart.setClickable(true);
        button_heart.setOnDragListener(mDragListener);
        */

//        //layout wave animation test
//        animation = (LinearLayout) findViewById(R.id.animation);

        instance = NetworkModel.getInstance();

        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.partnerRoom);
        final Handler handler=new Handler();
        button_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "push", Toast.LENGTH_SHORT).show();

                // rippleBackground.startRippleAnimation();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rippleBackground.startRippleAnimation();
                    }
                }, 0);
            }
        });

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        beforeMain();

        /*
        File file = new File(imagePath, "mine.jpg");
        if (file.exists()) {
            Log.i("exist", "file");
            myImage.setImageBitmap(getRoundedShape(BitmapFactory
                    .decodeFile(imagePath + "mine.jpg")));
        }

        File bgfile = new File(imagePath, "backGround.jpg");
        if (bgfile.exists()) {
            Log.i("exist", "file");
            Bitmap bmp = BitmapFactory.decodeFile(imagePath + "backGround.jpg");
            Drawable draw = new BitmapDrawable(getResources(), bmp);
            background.setBackground(draw);
        }*/
    }

//    @Override
//    protected void onRestart() {
//        // TODO Auto-generated method stub
//        super.onRestart();
//        Bundle fakeBundle = null;
//        onCreate(fakeBundle);
//    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    protected void beforeMain() {
        /*
        SharedPreferences pref = getSharedPreferences("auth",
                Activity.MODE_PRIVATE);
        if (!pref.getBoolean("havePartner", false)) {
            startActivity(new Intent(this, AuthActivity.class));
            pref = null;
        }

        pref = getSharedPreferences("login", Activity.MODE_PRIVATE);
        if (!pref.getBoolean("autoLogin", false)) {
            startActivity(new Intent(this, SignInActivity.class));
        }*/

        if(PreferenceManager.getInstance().getFirst().equals("")){

            startActivity(new Intent(this, AuthActivity.class));
            startActivity(new Intent(this, SignInActivity.class));
            PreferenceManager.getInstance().setFirst("registered");
        }
    }


    /*
    View.OnTouchListener touchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            int action = event.getAction();
            Log.i("TEST", "action=" + action);
            if (action == MotionEvent.ACTION_DOWN) {
                v.startDrag(null, new View.DragShadowBuilder(v), v, 0);
                Log.i("TEST", "down childview");

            } else if (action == MotionEvent.ACTION_UP) {
                Log.i("TEST", "up childview");
            }
            return true;
        }
    };

    View.OnDragListener mDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            // TODO Auto-generated method stub

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_ENDED:
                    Toast.makeText(MainActivity.this, "Push", Toast.LENGTH_SHORT)
                            .show();
                    animation.setBackgroundColor(Color.BLUE);

            }
            return true;
        }
    };
*/

    public void pushHeart(View v) {
        Toast.makeText(this, "push", Toast.LENGTH_SHORT).show();

        String PPUSH_ID = null;
        String phone = null;

        NetworkModel.getInstance().sendGCM(PPUSH_ID, phone,
                new NetworkModel.OnNetworkResultListener<IsSucceed>() {

                    public void onResult(IsSucceed result) {
                        // TODO Auto-generated method stub
                    }

                    public void onFail(int code) {
                        // TODO Auto-generated method stub
                    }
                });

    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = scaleBitmapImage.getWidth();
        int targetHeight = scaleBitmapImage.getHeight();

        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap, new Rect(0, 0, sourceBitmap.getWidth(),
                sourceBitmap.getHeight()), new Rect(0, 0, targetWidth,
                targetHeight), null);
        return targetBitmap;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "프로필");
        menu.add(0, 2, 0, "배경화면");
        menu.add(0, 3, 0, "설정");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case 1:
                intent = new Intent(this, ProfileActivity.class);
                break;
            case 2:
                intent = new Intent(this, BackgroundActivity.class);
                break;
            case 3:
                intent = new Intent(this, SettingActivity.class);
                break;
        }
        startActivity(intent);
        return false;
    }
}