package com.takeoffandroid.mvpauthentication.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;

import java.io.Serializable;

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
            putBundle(key,data,intent);
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
            putBundle(key,data,intent);
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


    public static <T> void putBundle(String key, T data, Intent intent){
        Bundle bundle = new Bundle();

        try {

            if (data instanceof Parcelable) {
                bundle.putParcelable(key, (Parcelable) data);
            } else if (data instanceof Serializable) {
                bundle.putSerializable(key, (Serializable) data);
            }
            intent.putExtras(bundle);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static <T> T getBundleParecelableExtra(Activity activity, String key){
        return activity.getIntent().getParcelableExtra(key);
    }

    public static <T> T getBundleSerializableExtra(Activity activity, String key){
        return (T)activity.getIntent().getSerializableExtra(key);
    }
}
