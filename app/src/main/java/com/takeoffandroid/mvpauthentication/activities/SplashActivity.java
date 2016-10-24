package com.takeoffandroid.mvpauthentication.activities;

import android.os.Bundle;
import android.os.Handler;

import com.takeoffandroid.mvpauthentication.R;
import com.takeoffandroid.mvpauthentication.general.Constants;
import com.takeoffandroid.mvpauthentication.models.Authentication;
import com.takeoffandroid.mvpauthentication.utils.ActivityUtils;
import com.takeoffandroid.mvpauthentication.utils.GSONUtils;
import com.takeoffandroid.mvpauthentication.utils.ScreenUtils;
import com.takeoffandroid.mvpauthentication.utils.SharedPrefsUtils;


public class SplashActivity extends BaseActivity {

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // This method will be executed once the timer is over
        // Start your app main activity
        handler = new Handler();

        runnable = new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                /**
                 * Checking whether MainActivity.java is already launched.
                 */
                if(ScreenUtils.isMainScreenActivated(SplashActivity.this)) {

                    //Fetching the Authentication model object values from shared preference to populate
                    //the fields in MainActivity.java
                    String authenticationJSONString = SharedPrefsUtils.getString(Constants.SHARED_PREFS.KEY_STORE_AUTHENTICATION_DATA, "", SplashActivity.this);

                    Authentication authentication = GSONUtils.createPojoFromString(authenticationJSONString,Authentication.class);

                    ActivityUtils.launchActivity(SplashActivity.this, MainActivity.class, true, Constants.INTENT.KEY_SEND_AUTHENTICATION,authentication);
                }else{
                    ActivityUtils.launchActivity(SplashActivity.this, AuthenticationActivity.class, true);

                }

            }
        };

        handler.postDelayed(runnable, Constants.SPLASH_TIME_OUT);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

        handler.removeCallbacks(runnable);
    }
}
