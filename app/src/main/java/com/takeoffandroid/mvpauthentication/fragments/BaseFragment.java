package com.takeoffandroid.mvpauthentication.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.takeoffandroid.mvpauthentication.utils.Utils;

/**
 * Created by Chandrasekar on 17-May-15.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getTypeface();
    }

    public Typeface getTypeface(){
        return Utils.getTypeface(getActivity());
    }


    @Override
    public void onClick(View v) {

    }
}
