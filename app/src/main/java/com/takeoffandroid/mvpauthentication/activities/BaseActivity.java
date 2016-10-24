package com.takeoffandroid.mvpauthentication.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.takeoffandroid.mvpauthentication.utils.Utils;

/**
 * Created by Chandrasekar on 17-May-15.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {


    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getTypeface();
    }

    public Typeface getTypeface(){
        return Utils.getTypeface(this);
    }


    @Override
    public void onClick(View v) {

    }
}
