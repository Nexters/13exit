package kr.ac.nexters.knock.network;

import org.apache.http.Header;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

import kr.ac.nexters.knock.tools.PreferenceManager;

/**
 * Created by KimCP on 15. 8. 7..
 * Description : NetworkModel class purpose is connect with Node.js server.
 *               This is dependent Loopj android http async library.
 */
public class NetworkModel {
	
	private static String SERVER_URL = "http://52.69.130.243:8330/";
	
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

	public void addUser(String userName, String phone, String uid, String pushId, final OnNetworkResultListener<IsSuccess> listener){
		RequestParams params = new RequestParams();
		params.put("username",userName);
		params.put("phonenum",phone);
		params.put("uid",uid);
		params.put("puid",pushId);
		params.put("myphone", PreferenceManager.getInstance().getPhonenum());

		client.post(SERVER_URL+"adduser", params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String res = new String(responseBody);
				Gson gson = new Gson();
				IsSuccess result = new IsSuccess();
				result = gson.fromJson(res,IsSuccess.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}


	public void authSend(String pphone, final OnNetworkResultListener<IsSuccess> listener){
		String pid = PreferenceManager.getInstance().getUid();
		String name = PreferenceManager.getInstance().getUserName();
		String pushid = PreferenceManager.getInstance().getPushId();

		RequestParams params = new RequestParams();
		params.put("pphone",pphone);
		params.put("pid",pid);
		params.put("name",name);
		params.put("pushid",pushid);
		params.put("myphone", PreferenceManager.getInstance().getPhonenum());

		client.post(SERVER_URL + "sendauth", params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				String res = new String(responseBody);
				Gson gson = new Gson();
				IsSuccess result = new IsSuccess();
				result = gson.fromJson(res, IsSuccess.class);
				listener.onResult(result);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				listener.onFail(statusCode);
			}
		});
	}

	//A에게 B의 정보를 보내줘야한다.
	public void authAccept(final OnNetworkResultListener<IsSuccess> listener){
		//sender 는 puid, pid는 나의 uid, name과 pushid 다 내꺼
		RequestParams params = new RequestParams();
		params.put("sender",PreferenceManager.getInstance().getPuid()); //A의 UID
		params.put("pid", PreferenceManager.getInstance().getUid()); // B의 UID
		params.put("pushid", PreferenceManager.getInstance().getPushId()); // B의 UID
		params.put("name", PreferenceManager.getInstance().getUserName()); // B의 Name

		client.post(SERVER_URL + "authaccept", params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void knock(final OnNetworkResultListener<IsSuccess> listener){
		RequestParams params = new RequestParams();

		Log.i("(NM)knock to ", PreferenceManager.getInstance().getPpushId());

		//params.put("pushid", PreferenceManager.getInstance().getPpushId());

		String puid = PreferenceManager.getInstance().getPuid();
		params.put("puid", puid);

		client.post(SERVER_URL + "knock", params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}

	public void setImage(String file, final OnNetworkResultListener<IsSuccess> listener) {
		RequestParams params = new RequestParams();
		params.put("phone", PreferenceManager.getInstance().getPhonenum());
        params.put("name", PreferenceManager.getInstance().getUserName());
        params.put("pphone", PreferenceManager.getInstance().getPphoneNum());

        try {
			params.put("upfile", new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.i("TAAS", "ERROR");
		}

		client.post(SERVER_URL + "image", params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				Log.i("IMAGE", "Image upfile");
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

			}
		});
	}


    public void setImg(String img, final OnNetworkResultListener<IsSuccess> listener){
        RequestParams params = new RequestParams();
        params.put("img", img);
        params.put("phone", PreferenceManager.getInstance().getPhonenum());
        params.put("name", PreferenceManager.getInstance().getUserName());
        params.put("pphone", PreferenceManager.getInstance().getPphoneNum());

        client.post(SERVER_URL + "img", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void clear(final OnNetworkResultListener<IsSuccess> listener){
        RequestParams params = new RequestParams();
        params.put("myphone", PreferenceManager.getInstance().getPhonenum());
        params.put("pphone", PreferenceManager.getInstance().getPphoneNum());

        client.post(SERVER_URL + "clear", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //Preference.clear;

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
