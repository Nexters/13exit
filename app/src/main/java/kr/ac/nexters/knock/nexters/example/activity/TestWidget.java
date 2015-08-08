package kr.ac.nexters.knock.nexters.example.activity;

import kr.ac.nexters.knock.R;
import kr.ac.nexters.knock.network.IsSucceed;
import kr.ac.nexters.knock.network.NetworkModel;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;


//����. �̿ϼ�.
public class TestWidget extends AppWidgetProvider{
	
	final static String ACTION_DISPLAY_FULLTIME = "NalYoil.DisplayFullTime";	
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, 
			int[] appWidgetIds) {
		for (int i = 0;i<appWidgetIds.length;i++) {
			RemoteViews remote = new RemoteViews(context.getPackageName(), 
					R.layout.testlayout);
		
			Intent intent = new Intent(context, TestWidget.class);
			intent.setAction(ACTION_DISPLAY_FULLTIME);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0); 
			remote.setOnClickPendingIntent(R.id.button1, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetIds[i], remote);
		}
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		String action = intent.getAction();
		if (action!=null && action.equals(ACTION_DISPLAY_FULLTIME)){
			Toast.makeText(context, "Push", Toast.LENGTH_SHORT).show();
			
			String PPUSH_ID = null;
			String phone = null;
			
			NetworkModel.getInstance().sendGCM(PPUSH_ID, phone, new NetworkModel.OnNetworkResultListener<IsSucceed>() {

				public void onResult(IsSucceed result) {
					// TODO Auto-generated method stub
				}

				public void onFail(int code) {
					// TODO Auto-generated method stub
				}
			});
		}
	}
	
	

}
