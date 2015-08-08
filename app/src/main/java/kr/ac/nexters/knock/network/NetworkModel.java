package kr.ac.nexters.knock.network;

import org.apache.http.Header;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NetworkModel {
	
	public static String SERVER_URL = "http://52.69.130.243:8300/";
	
	AsyncHttpClient client;
	
	private static NetworkModel instance;
	
	public static NetworkModel getInstance(){
		if(instance == null){
			instance = new NetworkModel();
		}
		
		return instance;
	}
	
	private NetworkModel(){
		client = new AsyncHttpClient();
	}
	
	public interface OnNetworkResultListener<T>{
		public void onResult(T result);
		public void onFail(int code);
	}
	
	public void addUser(String phone, String name, String UID, String PUID, String IMG, final OnNetworkResultListener<IsSucceed> listener){
		String url = SERVER_URL + "addUser";
		RequestParams param = new RequestParams();
		param.put("phone", phone);
		param.put("name", name);
		param.put("UID", UID);
		param.put("PUID", PUID);
		param.put("IMG", IMG);
		
		client.get(url, param, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				//listener.onResult(result);
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void auth(String pphone, String UID, final OnNetworkResultListener<IsSucceed> listener){
		String url = SERVER_URL + "auth";
		RequestParams param = new RequestParams();
		param.put("pphone", pphone);
		param.put("UID", UID);
		
		client.get(url, param, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				//listener.onResult(result);
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
	public void sendGCM(String PPUSH_ID, String phone, final OnNetworkResultListener<IsSucceed> listener){
		String url = SERVER_URL + "sendGCM";
		RequestParams param = new RequestParams();
		param.put("PPUSH_ID", PPUSH_ID);
		param.put("phone", phone);
		
		client.get(url, param, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				//listener.onResult(result);
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}	
	
	public void testFunction(final String test, final OnNetworkResultListener<IsSucceed> listener){
		String url = SERVER_URL + "test";
		RequestParams param = new RequestParams();
		param.put("test", test);		
		
		client.get(url, param, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				//listener.onResult(result);
				
				Gson gson=new Gson();
				String result = new String(responseBody);
				Log.i("test",result);
				IsSucceed success = gson.fromJson(result, IsSucceed.class);
				listener.onResult(success);
				
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
