package kr.ac.nexters.knock.tools;

import android.content.Context;
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

        custom_main_item.setImageResource(imageList.get(position).getMainItemID());

        /*
        if(layout == R.layout.item_backgroundgrid && position == 0 && PreferenceManager.getInstance().getBgSelect() == 0){
            ImageLoader.getInstance().displayImage("file:///storage/emulated/0//background.jpg", custom_main_item, MyApplication.getDisplayImageOptions());
        }

        sub_item.setImageResource(imageList.get(position).getSubItemID());
        */
        return convertView;
    }
}
