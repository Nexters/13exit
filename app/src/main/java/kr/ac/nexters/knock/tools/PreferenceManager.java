package kr.ac.nexters.knock.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by KimCP on 15. 8. 9..
 * This class is for use sharedPreferences to easy on evenywhere.
 * Implement singleton pattern. If you want add value.
 * You can modify sample code.
 *
 * Sampe code. (You will change all key(KEY,Key,key) to value name use before)
 * private static final String KEY = "key";
 * private String key = "";
 *
 * public void setKey(String key){
 *      this.key = key;
 *      mEditor.putString(KEY,key);
 *      mEditor.commit();
 * }
 *
 * public void getKey(){
 *     if(key.equals("")){
 *         key = mPrefs.getString("KEY", "");
 *     }
 *     return key;
 * }
 */
public class PreferenceManager {
    private static final String PREF_NAME = "my_prefs.xml";

    private static PreferenceManager instance;

    public static PreferenceManager getInstance() {
        if(instance == null){
            instance = new PreferenceManager();
        }
        return instance;
    }

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private PreferenceManager(){
        mPrefs = MyApplication.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
    }

    //first login check
    private static final String FIRST = "first";
    private String first = "";

    public void setFirst(String first){
        this.first = first;
        mEditor.putString(FIRST, first);
        mEditor.commit();
    }

    public String getFirst(){
        if(this.first.equals("")){
            first = mPrefs.getString(FIRST, "");
        }
        return first;
    }

    //user pid;
    private static final String PID = "pid";
    private String pid = "";

    public void setPid(String pid){
        this.pid = pid;
        mEditor.putString(PID, pid);
        mEditor.commit();
    }

    public String getPid(){
        if(this.pid.equals("")){
            this.pid = mPrefs.getString(PID,"");
        }
        return pid;
    }

    //user push id;
    private static final String PUSH_ID = "pushId";
    private String pushid = "";

    public void setPushId(String pushid){
        this.pushid = pushid;
        mEditor.putString(PUSH_ID, pushid);
        mEditor.commit();
    }

    public String getPushId(){
        if(pushid.equals("")){
            pushid = mPrefs.getString(PUSH_ID, "");
        }
        return pushid;
    }

    //partner push id;
    private static final String PPUSH_ID = "ppushId";
    private String ppushid = "";

    public void setPpushId(String ppushid){
        this.ppushid = pushid;
        mEditor.putString(PPUSH_ID, ppushid);
        mEditor.commit();
    }

    public String getPpushId(){
        if(ppushid.equals("")){
            ppushid = mPrefs.getString(PPUSH_ID, "");
        }
        return ppushid;
    }

    //user name
    private static final String USER_NAME = "userName";
    private String username = "";

    public void setUserName(String username){
        this.username = username;
        mEditor.putString(USER_NAME, username);
        mEditor.commit();
    }

    public String getUserName(){
        if(this.username.equals("")){
            this.username = mPrefs.getString(USER_NAME, "");
        }
        return username;
    }


    //auth check (return PUID)
    private static final String PUID = "puid";
    private String puid = "";

    public void setPuid(String puid){
        this.puid = puid;
        mEditor.putString(PUID, puid);
        mEditor.commit();
    }

    public String getPuid(){
        if(this.puid.equals("")){
            this.puid = mPrefs.getString(PUID, "");
        }
        return puid;
    }
}
