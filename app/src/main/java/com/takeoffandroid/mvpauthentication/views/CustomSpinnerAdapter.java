package com.takeoffandroid.mvpauthentication.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.takeoffandroid.mvpauthentication.utils.Utils;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private Context mContext;


    public CustomSpinnerAdapter(Context context, int resource, List<String> items) {

        super(context, resource, items);
        mContext = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);

        view.setTypeface(Utils.getTypeface(mContext));

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);

        view.setTypeface(Utils.getTypeface(mContext));


        return view;
    }


    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}