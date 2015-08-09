package kr.ac.nexters.knock.tools;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import kr.ac.nexters.knock.R;

/**
 * Created by KimCP on 15. 8. 9..
 */
public class MyApplication extends Application {

    private static Context sContext;
    private static DisplayImageOptions options = null;

    public void onCreate(){
        super.onCreate();
        sContext = this;
        this.initImageLoader(sContext);
    }

    public static Context getContext() {
        return sContext;
    }

    private void initImageLoader(Context context){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions getDisplayImageOptions(){
        if(options == null) {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .showImageForEmptyUri(R.mipmap.ic_launcher)
                    .showImageOnFail(R.mipmap.ic_launcher)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .build();
        }
        return options;
    }

    public void removeFromCache(String imageUri)
    {
        DiskCacheUtils.removeFromCache(imageUri, ImageLoader.getInstance().getDiskCache());
        MemoryCacheUtils.removeFromCache(imageUri, ImageLoader.getInstance().getMemoryCache());
    }
}
