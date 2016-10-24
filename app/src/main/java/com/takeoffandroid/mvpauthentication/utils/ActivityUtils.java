package com.takeoffandroid.mvpauthentication.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by chandrasekar on 19/10/16.
 */

public class ActivityUtils {

    /**
     *
     * Method to launch next activity with bundle value data as model class (i.e startIntent)
     * @param context   - Context of the activity
     * @param activity  - Activity class that should be launched
     * @param closeCurrentActivity - Pass 'true' to close current activity
     */
    public static <T>void launchActivity(Activity context, Class<? extends Activity> activity, boolean closeCurrentActivity, String key, T data) {
        Intent intent = new Intent(context, activity);

        if (data != null) {
            putBundleParcelable(key,data,intent);
        }
        context.startActivity(intent);
        if (closeCurrentActivity) {
            context.finish();
        }
    }


    /**
     *
     * Method to launch next activity with bundle value data as model class (i.e startIntent)
     * @param context   - Context of the activity
     * @param activity  - Activity class that should be launched
     * @param closeCurrentActivity - Pass 'true' to close current activity
     * @param bundle    - Pass bundle from one class to another
     */
    public static void launchActivityFromFragment(FragmentActivity context, Class<? extends Activity> activity, boolean closeCurrentActivity, Bundle bundle) {
        Intent intent = new Intent(context, activity);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (closeCurrentActivity) {
            context.finish();
        }
    }


    /**
     *
     * Method to launch next activity with bundle value data as map (i.e startIntent)
     * @param context   - Context of the activity
     * @param activity  - Activity class that should be launched
     * @param closeCurrentActivity - Pass 'true' to close current activity
     */
    public static <T>void launchActivityFromFragment(FragmentActivity context, Class<? extends Activity> activity, boolean closeCurrentActivity,String key, T data) {
        Intent intent = new Intent(context, activity);

        if (data != null) {
            putBundleParcelable(key,data,intent);
        }
        context.startActivity(intent);
        if (closeCurrentActivity) {
            context.finish();
        }
    }


    /**
     * Method to launch next activity (i.e startIntent)
     * @param context   - Context of the activity
     * @param activity  - Activity class that should be launched
     * @param closeCurrentActivity - Pass 'true' to close current activity
     */
    public static void launchActivity(Activity context, Class<? extends Activity> activity, boolean closeCurrentActivity) {
        ActivityUtils.launchActivity(context, activity, closeCurrentActivity,null, null);
    }


    /**
     * Method to launch next activity (i.e startIntent)
     * @param context   - Context of the activity
     * @param activity  - Activity class that should be launched
     * @param closeCurrentActivity - Pass 'true' to close current activity
     */
    public static void launchActivityFromFragment(FragmentActivity context, Class<? extends Activity> activity, boolean closeCurrentActivity) {
        ActivityUtils.launchActivity(context, activity, closeCurrentActivity, null,null);
    }


    public static <T> void putBundleParcelable(String key, T data, Intent intent){
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, (Parcelable) data);
        intent.putExtras(bundle);

    }

    public static <T> T getBundleParecelable(Activity activity, String key){
        return activity.getIntent().getParcelableExtra(key);
    }
}
