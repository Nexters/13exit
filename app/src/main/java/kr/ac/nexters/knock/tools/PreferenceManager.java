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
    private static final String UID = "puid";
    private String uid = "";

    public void setUid(String uid){
        this.uid = uid;
        mEditor.putString(UID, uid);
        mEditor.commit();
    }

    public String getUid(){
        if(this.uid.equals("")){
            this.uid = mPrefs.getString(UID,"");
        }
        return uid;
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

    //my phone number
    private static final String PHONENUM = "phonenum";
    private String phonenum = "";

    public void setPhonenum(String phonenum){
        this.phonenum = phonenum;
        mEditor.putString(PHONENUM, phonenum);
        mEditor.commit();
    }

    public String getPhonenum(){
        if(this.phonenum.equals("")){
            this.phonenum = mPrefs.getString(PHONENUM, "");
        }
        return phonenum;
    }

    //push
    private static final String PUSH_USE = "push_use";
    private boolean push_use = true;

    public void setPushUse(boolean push_use){
        this.push_use = push_use;
        mEditor.putBoolean(PUSH_USE, push_use);
        mEditor.commit();
    }

    public boolean getPushUse(){
        if(this.push_use){
            this.push_use = mPrefs.getBoolean(PUSH_USE, true);
        }
        return  push_use;
    }





    //vib with push
    private static final String WITH_VIB = "with_vib";
    private boolean with_vib = true;

    public void setWithVib(boolean with_vib){
        this.with_vib = with_vib;
        mEditor.putBoolean(WITH_VIB, with_vib);
        mEditor.commit();
    }

    public boolean getWithVib(){
        if(this.with_vib){
            this.with_vib = mPrefs.getBoolean(WITH_VIB, true);
        }
        return  with_vib;
    }


    //myImg
    private static final String MYIMG = "myImg";
    private String myImg = "";

    public void setMyImg(String myImg){
        this.myImg = myImg;
        mEditor.putString(MYIMG, myImg);
        mEditor.commit();
    }

    public String getMyImg(){
        if(myImg.equals("")){
            myImg = mPrefs.getString(MYIMG, "");
        }
        return myImg;
    }


    //background img
    private static final String BGIMG = "bgImg";
    private String bgImg = "";

    public void setBgImg(String bgImg){
        this.bgImg = bgImg;
        mEditor.putString(BGIMG, bgImg);
        mEditor.commit();
    }

    public String getBgImg(){
        if(bgImg.equals("")){
            bgImg = mPrefs.getString(BGIMG, "");
        }
        return bgImg;
    }


    //pname
    private static final String PNAME = "pname";
    private String pname = "";

    public void setPname(String pname){
        this.pname = pname;
        mEditor.putString(PNAME, pname);
        mEditor.commit();
    }

    public String getPname(){
        if(pname.equals("")){
            pname = mPrefs.getString(PNAME, "");
        }
        return pname;
    }


    //background select
    private static final String BGSELECT = "bgSelect";
    private int bgSelect = 1;

    public void setBgSelect(int bgSelect){
        this.bgSelect = bgSelect;
        mEditor.putInt(BGSELECT, bgSelect);
        mEditor.commit();
    }

    public int getBgSelect(){
        if(bgSelect == 1){
            bgSelect = mPrefs.getInt(BGSELECT, 1);
        }
        return bgSelect;
    }


    //partner img
    private static final String PIMG = "pImg";
    private String pImg = "";

    public void setPimg(String pImg){
        this.pImg = pImg;
        mEditor.putString(PIMG, pImg);
        mEditor.commit();
    }

    public String getPimg(){
        if(pImg.equals("")){
            pImg = mPrefs.getString(PIMG, "");
        }
        return pImg;
    }

    //partner phone number
    private static final String PPHONENUM = "pphone";
    private String pphonenum = "";

    public void setPphoneNum(String pphonenum){
        this.pphonenum = pphonenum;
        mEditor.putString(PPHONENUM, pphonenum);
        mEditor.commit();
    }

    public String getPphoneNum(){
        if(pphonenum.equals("")){
            pphonenum = mPrefs.getString(PPHONENUM, "");
        }
        return pphonenum;
    }


    //clear
    public void clear(){
        mEditor.clear();
        mEditor.commit();
    }

}
