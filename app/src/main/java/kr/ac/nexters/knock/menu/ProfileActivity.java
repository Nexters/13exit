package kr.ac.nexters.knock.menu;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.tools.GridImageAdapter;
import kr.ac.nexters.knock.tools.GridItem;
import kr.ac.nexters.knock.tools.ImageViewRounded;
import kr.ac.nexters.knock.tools.MyApplication;
import kr.ac.nexters.knock.tools.PreferenceManager;


public class ProfileActivity extends AppCompatActivity {

	File mSavedFile;
	public static final int REQUEST_CODE_CROP = 0;
	ImageViewRounded myImg;
	ArrayList<GridItem> imageList = new ArrayList<>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilelayout);
		putDataInList();

		//hide actionbar
		getSupportActionBar().hide();


		myImg = (ImageViewRounded)findViewById(R.id.profile_iv_preview);

		//현재 preference에 유저이름 저장 안되어있음.
		TextView myName = (TextView)findViewById(R.id.profile_tv_myName);
		myName.setText(PreferenceManager.getInstance().getUserName());

		TextView myPhone = (TextView)findViewById(R.id.profile_tv_myPhone);
		myPhone.setText("010"+PreferenceManager.getInstance().getPhonenum());

		ImageButton nameModi = (ImageButton) findViewById(R.id.profile_btn_modify);
		LinearLayout btn_back = (LinearLayout) findViewById(R.id.title_backbtn);

		nameModi.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ProfileActivity.this, "수정요청", Toast.LENGTH_SHORT).show();
			}
		});

		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


		GridView grid_profileSelect = (GridView) findViewById(R.id.profile_grid_imageSelect);
		GridImageAdapter adapter = new GridImageAdapter(this, imageList, R.layout.item_profilegrid);
		grid_profileSelect.setAdapter(adapter);
		grid_profileSelect.setOnItemClickListener(itemClickListener);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if(PreferenceManager.getInstance().getMyImg().isEmpty()){

		}
		else if(PreferenceManager.getInstance().getMyImg().length() < 16){
			//length말고 다른 방법 찾기.
			myImg.setImageResource(Integer.parseInt(PreferenceManager.getInstance().getMyImg()));
		}
		else
			ImageLoader.getInstance().displayImage("file:///storage/emulated/0//mine.jpg", myImg, MyApplication.getDisplayImageOptions());
	}

	private void putDataInList(){
		imageList.add(new GridItem(R.mipmap.btn_backgroundpicture_plus, -1));
		imageList.add(new GridItem(R.mipmap.bear, -1));
		imageList.add(new GridItem(R.mipmap.bunny, -1));
		imageList.add(new GridItem(R.mipmap.doggy, -1));
		imageList.add(new GridItem(R.mipmap.fox, -1));
		imageList.add(new GridItem(R.mipmap.kitty, -1));
		imageList.add(new GridItem(R.mipmap.monkey, -1));
		imageList.add(new GridItem(R.mipmap.robin, -1));
		imageList.add(new GridItem(R.mipmap.sloth, -1));
	}

	AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (position == 0) {

				//이렇게 하면 안될둡....
				ImageLoader.getInstance().clearMemoryCache();
				ImageLoader.getInstance().clearDiskCache();

				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				photoPickerIntent.setType("image/*");
				photoPickerIntent.putExtra("crop", "true");
				photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
				photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
				startActivityForResult(photoPickerIntent, REQUEST_CODE_CROP);
			} else{
				myImg.setImageResource(imageList.get(position).getMainItemID());
				PreferenceManager.getInstance().setMyImg(String.valueOf(imageList.get(position).getMainItemID()));
			}
		}
	};





	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),"mine.jpg");
		return Uri.fromFile(mSavedFile);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CROP && resultCode == Activity.RESULT_OK) {
			ImageLoader.getInstance().displayImage("file:///storage/emulated/0//mine.jpg", myImg, MyApplication.getDisplayImageOptions());
			PreferenceManager.getInstance().setMyImg(getTempUri().toString());
		}
	}
}
