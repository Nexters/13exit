package kr.ac.nexters.knock.menu;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.tools.GridImageAdapter;
import kr.ac.nexters.knock.tools.GridItem;
import kr.ac.nexters.knock.tools.PreferenceManager;

public class BackgroundActivity extends AppCompatActivity {

	File mSavedFile;
	public static final int REQUEST_CODE_CROP = 0;
	ImageLoader imageLoader;
	static ArrayList<GridItem> imageList = new ArrayList<>();
	GridImageAdapter adapter;
	static int lastSelect = PreferenceManager.getInstance().getBgSelect();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.backgroundlayout);

		if(imageList.isEmpty()) {
			putDataInList();
		}

		if(lastSelect != 0)
			imageList.get(lastSelect).setSubItemID(R.mipmap.imsi);

		//hide actionbar
		getSupportActionBar().hide();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
		ImageLoader.getInstance().init(config);

		imageLoader = ImageLoader.getInstance();

		LinearLayout btn_back = (LinearLayout) findViewById(R.id.title_backbtn);
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		final GridView grid_bgselect = (GridView) findViewById(R.id.bg_grid_imageSelect);
		adapter = new GridImageAdapter(this, imageList, R.layout.item_backgroundgrid);
		grid_bgselect.setAdapter(adapter);
		grid_bgselect.setOnItemClickListener(itemClickListener);
	}


	private void putDataInList(){
		imageList.add(new GridItem(0, R.mipmap.imsi2));
		imageList.add(new GridItem(R.mipmap.main_background, 0));
		imageList.add(new GridItem(R.mipmap.bg_backgroundpicture_area, 0));
		imageList.add(new GridItem(R.mipmap.bg_backgroundpicture_area, 0));
		imageList.add(new GridItem(R.mipmap.bg_backgroundpicture_area, 0));
		imageList.add(new GridItem(R.mipmap.bg_backgroundpicture_area, 0));
		imageList.add(new GridItem(R.mipmap.bg_backgroundpicture_area, 0));
		imageList.add(new GridItem(R.mipmap.bg_backgroundpicture_area, 0));
		imageList.add(new GridItem(R.mipmap.bg_backgroundpicture_area, 0));
		imageList.add(new GridItem(R.mipmap.bg_backgroundpicture_area, 0));
	}



	AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (position == 0) {
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				photoPickerIntent.setType("image/*");
				photoPickerIntent.putExtra("crop", "true");
				photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
				photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
				startActivityForResult(photoPickerIntent, REQUEST_CODE_CROP);
			} else if(position == lastSelect){
				return;
			} else {
				PreferenceManager.getInstance().setBgImg(String.valueOf(imageList.get(position).getMainItemID()));
				imageList.get(position).setSubItemID(R.mipmap.imsi);
				adapter.notifyDataSetChanged();
			}

			PreferenceManager.getInstance().setBgSelect(position);
			if (lastSelect != 0){
				imageList.get(lastSelect).setSubItemID(0);
			}
			lastSelect = position;
		}
	};

	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),"background.jpg");
		return Uri.fromFile(mSavedFile);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CROP && resultCode == Activity.RESULT_OK) {
			PreferenceManager.getInstance().setBgImg(getTempUri().toString());
			ImageLoader.getInstance().clearMemoryCache();
			ImageLoader.getInstance().clearDiskCache();
			adapter.notifyDataSetChanged();
		}
	}

}
