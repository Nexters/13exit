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
import android.widget.TextView;
import android.widget.Toast;

import kr.ac.nexters.knock.R;


public class ProfileActivity extends AppCompatActivity {

	File mSavedFile;
	public static final int REQUEST_CODE_CROP = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilelayout);

		//hide actionbar
		getSupportActionBar().hide();

		//현재 preference에 유저이름 저장 안되어있음.
		TextView myName = (TextView)findViewById(R.id.profile_tv_myName);
//		myName.setText(PreferenceManager.getInstance().getUserName());

		TextView myPhone = (TextView)findViewById(R.id.profile_tv_myPhone);
		myPhone.setText("핸드폰번호");

		ImageButton nameModi = (ImageButton) findViewById(R.id.profile_btn_modify);
		ImageButton btn_back = (ImageButton)findViewById(R.id.title_backbtn);

		nameModi.setOnClickListener(new ImageButton.OnClickListener(){
			@Override
			public void onClick(View v) {
				Toast.makeText(ProfileActivity.this, "수정요청", Toast.LENGTH_SHORT).show();
			}
		});

		btn_back.setOnClickListener(new ImageButton.OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});



		GridView grid_bgselect = (GridView) findViewById(R.id.profile_grid_imageSelect);
		ImageAdapter adapter = new ImageAdapter(this);
		grid_bgselect.setAdapter(adapter);

		grid_bgselect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {
					Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					photoPickerIntent.setType("image/*");
					photoPickerIntent.putExtra("crop", "true");
					photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
					photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
					startActivityForResult(photoPickerIntent, REQUEST_CODE_CROP);
				} else
					Toast.makeText(ProfileActivity.this, position + "번째 선택", Toast.LENGTH_SHORT).show();
			}
		});
	}


	class ImageAdapter extends BaseAdapter {

		private Context context;
		ArrayList<Integer> imageList = new ArrayList<Integer>();

		public ImageAdapter(Context context) {
			this.context = context;

			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
			imageList.add(R.mipmap.bg_unselect_character);
		}


		@Override
		public int getCount() {
			return imageList.size();
		}

		@Override
		public Object getItem(int position) {
			return imageList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;

			if (convertView == null) {
				imageView = new ImageView(context);
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(imageList.get(position));

			return imageView;
		}
	}

	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),"mine.jpg");
		return Uri.fromFile(mSavedFile);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CROP && resultCode == Activity.RESULT_OK) {
		}
	}

}
