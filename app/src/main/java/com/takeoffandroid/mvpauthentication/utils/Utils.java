package com.takeoffandroid.mvpauthentication.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.takeoffandroid.mvpauthentication.R;

/**
 * Created by Chandru-MacBook on 5/22/15.
 */
public class Utils {


    /**
     * Method for the typeface used in the app.
     * @param context
     * @return
     */
    public static Typeface getTypeface(Context context) {

        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/font_regular.otf");
        return tf;

    }

    public static void setTextAppearance(Context context, TextView textView, int resId) {

        if (Build.VERSION.SDK_INT < 23) {

            textView.setTextAppearance(context, resId);

        } else {

            textView.setTextAppearance(resId);
        }
    }


    public static int getColor(Context context,int color ){

        if(Build.VERSION.SDK_INT >= 23){
            return context.getColor(color);
        }

        return context.getResources().getColor(color);
    }

    /**
     * Method to display Snacbar with short duration
     * @param view - View type (Eg: TextView, Button, EditText)
     * @param text - The text that should be shown on the Snackbar
     */
    public static void showSnackShort(View view, String text){
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }


    /**
     * Method to show toast message
     * @param context
     * @param message
     */
    public static void showToast(Context context,String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
