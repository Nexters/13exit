package kr.ac.nexters.knock.widget;

import kr.ac.nexters.knock.R;

import kr.ac.nexters.knock.network.IsSuccess;
import kr.ac.nexters.knock.network.NetworkModel;
import kr.ac.nexters.knock.tools.PreferenceManager;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class Widget extends AppWidgetProvider{

	final static String ACTION_DISPLAY_FULLTIME = "DisplayFullTime";

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, 
			int[] appWidgetIds) {
		for (int i = 0;i<appWidgetIds.length;i++) {
			RemoteViews remote = new RemoteViews(context.getPackageName(), 
					R.layout.widget);
		
			Intent intent = new Intent(context, Widget.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0); 
			remote.setOnClickPendingIntent(R.id.widget_btn, pendingIntent);
			remote.setTextViewText(R.id.widget_pName, "파트너이름");
			appWidgetManager.updateAppWidget(appWidgetIds[i], remote);
		}
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
			//여기부터 위젯 클릭시 동작.

			Toast.makeText(context, "Push", Toast.LENGTH_SHORT).show();

			NetworkModel.getInstance().knock(new NetworkModel.OnNetworkResultListener<IsSuccess>() {
				@Override
				public void onResult(IsSuccess result) {

				}

				@Override
				public void onFail(int code) {

				}
			});


	}
	
	

}
