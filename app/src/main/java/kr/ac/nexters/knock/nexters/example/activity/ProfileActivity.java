package kr.ac.nexters.knock.nexters.example.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kr.ac.nexters.knock.R;

//�����ʻ��� �����ϴ� ��Ƽ��Ƽ. ������ �� �� Ŭ��->Android tools -> Clear Lint Makers �ʼ�!!
public class ProfileActivity extends AppCompatActivity {

	Button btn;
	String picturePath;
	static String imagePath;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilelayout);
		
		btn = (Button) findViewById(R.id.button1);
		
		imagePath = Environment.getDataDirectory().getAbsolutePath();
		imagePath += "/data/nexters.example.testknock00/files/";
	}
	
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
			
			saveBitmaptoJpeg(file, picturePath, "mine");
		}
	}
	
	public static void saveBitmaptoJpeg(Bitmap bitmap, String folder,
			String name) {
		String file_name = name + ".jpg";
		String string_path = imagePath;

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
	}

}
