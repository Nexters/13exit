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
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import kr.ac.nexters.knock.R;

public class BackgroundActivity extends AppCompatActivity {

	File mSavedFile;
	public static final int REQUEST_CODE_CROP = 0;
	ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.backgroundlayout);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
		ImageLoader.getInstance().init(config);

		imageLoader = ImageLoader.getInstance();

		GridView grid_bgselect = (GridView) findViewById(R.id.bg_grid_imageSelect);
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
					Toast.makeText(BackgroundActivity.this, position + "번째 선택", Toast.LENGTH_SHORT).show();
			}
		});
	}

	class ImageAdapter extends BaseAdapter {

		private Context context;
		ArrayList<Integer> imageList = new ArrayList<Integer>();

		public ImageAdapter(Context context) {
			this.context = context;

			imageList.add(R.mipmap.btn_backgroundpicture_plus);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
			imageList.add(R.mipmap.bg_backgroundpicture_area);
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
				imageView.setLayoutParams(new GridView.LayoutParams(357, 516));
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(imageList.get(position));

			return imageView;
		}
	}


	private Uri getTempUri() {
		mSavedFile = new File(Environment.getExternalStorageDirectory(),"background.jpg");
		return Uri.fromFile(mSavedFile);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CROP && resultCode == Activity.RESULT_OK) {
		}
	}



	/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CROP && resultCode == Activity.RESULT_OK && data != null) {
			Uri imageUri = data.getData();
			String[] filePathColumn = {MediaStore.Images.Media.DATA};

			Cursor cursor = getContentResolver().query(imageUri,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			Toast.makeText(this, "path : " + imageUri.fromFile(new File(picturePath)), Toast.LENGTH_LONG).show();
		}
	}
	*/
}


	/*
	public void onClick(View v){
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		startActivityForResult(intent, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1 && resultCode == RESULT_OK
				&& null != data) {
			Uri imageUri = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(imageUri,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			picturePath = cursor.getString(columnIndex);
			cursor.close();
						
			BitmapFactory.Options options = new BitmapFactory.Options();
			Bitmap file = BitmapFactory.decodeFile(picturePath);			
			
			
            try {
                ExifInterface exif = new ExifInterface(picturePath);
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                Log.d("EXIF", "Exif: " + orientation);
                Matrix matrix = new Matrix();
                if (orientation == 6) {
                    matrix.postRotate(90);
                }
                else if (orientation == 3) {
                    matrix.postRotate(180);
                }
                else if (orientation == 8) {
                    matrix.postRotate(270);
                }
                file = Bitmap.createBitmap(file, 0, 0, file.getWidth(), file.getHeight(), matrix, true); // rotating bitmap
            }
            catch (Exception e) {
            	e.getMessage();
            }	
			
			
			if (file.getByteCount() > 20000000){
				options.inSampleSize = 4;		
				file = BitmapFactory.decodeFile(picturePath, options);
				file = Bitmap.createScaledBitmap(file, file.getWidth()/4, file.getHeight()/4, true);
			}
			saveBitmaptoJpeg(file, picturePath, "backGround");
		}
	}

	public static void saveBitmaptoJpeg(Bitmap bitmap, String folder,
			String name) {
		String file_name = name + ".jpg";
		String string_path = "";

		File file_path;
		
		try {
			file_path = new File(string_path);
			if (!file_path.isDirectory()) {
				file_path.mkdirs();
			}
			FileOutputStream out = new FileOutputStream(string_path + file_name);

			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.close();
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", e.getMessage());
		} catch (IOException e) {
			Log.e("IOException", e.getMessage());
		}
	}*/
	

	
	

