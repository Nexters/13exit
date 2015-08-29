package kr.ac.nexters.knock.tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.ResourceBundle;

import kr.ac.nexters.knock.R;

/**
 * Created by Min on 2015-08-21.
 */
public class GridImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<GridItem> imageList;
    private int layout;
    private LayoutInflater inflater;

    public GridImageAdapter(Context context, ArrayList<GridItem> imageList, int layout) {
        this.context = context;
        this.imageList = imageList;
        this.layout = layout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        ImageView custom_main_item = (ImageView) convertView.findViewById(R.id.custom_main_item);
        ImageView sub_item = (ImageView) convertView.findViewById(R.id.sub_item);


        if(layout == R.layout.item_backgroundgrid){
            if(position == 0 && PreferenceManager.getInstance().getBgSelect() == 0) {
                ImageLoader.getInstance().displayImage("file:///storage/emulated/0//background.jpg", custom_main_item, MyApplication.getDisplayImageOptions());
            }else {
                custom_main_item.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), imageList.get(position).getMainItemID(), 119, 172));
            }
        }else{
            custom_main_item.setImageResource(imageList.get(position).getMainItemID());
        }

        sub_item.setImageResource(imageList.get(position).getSubItemID());

        return convertView;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }
        return inSampleSize;
    }
}
