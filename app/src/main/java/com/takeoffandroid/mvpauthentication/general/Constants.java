package com.takeoffandroid.mvpauthentication.general;

/**
 * Created by chandrasekar on 19/10/16.
 */

public class Constants {


    public static final int SPLASH_TIME_OUT = 5000;

    public interface SHARED_PREFS{

        String KEY_STORE_AUTHENTICATION_DATA = "shared_prefs_store_authentication";

        String KEY_FLAG_MAINSCREEN = "screen_main";


    }

    public interface SIGNUP_FRAGMENT_STATE{

        String KEY_USER_TYPE = "signup_fragment_state_user_type";
    }

    public interface INTENT{

        String KEY_SEND_AUTHENTICATION = "intent_send_authentication";
    }
}
