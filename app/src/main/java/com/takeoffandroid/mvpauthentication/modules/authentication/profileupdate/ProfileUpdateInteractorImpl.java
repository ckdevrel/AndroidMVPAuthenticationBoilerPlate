package com.takeoffandroid.mvpauthentication.modules.authentication.profileupdate;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.R;
import com.takeoffandroid.mvpauthentication.database.DBAccess;
import com.takeoffandroid.mvpauthentication.models.Authentication;
import com.takeoffandroid.mvpauthentication.utils.ValidationUtils;

public class ProfileUpdateInteractorImpl implements ProfileUpdateInteractor {


    @Override
    public void update(Context context, Authentication authentication, OnProfileUpdateFinishedListener listener) {


        String mobile = authentication.getMobile();

        boolean error = false;

        if (ValidationUtils.isNullOrEmpty(mobile)){
            listener.onUpdateMobileFailure(context.getString(R.string.wrong_form_data));
            error = true;
            return;
        }


        if (!ValidationUtils.isValidMobile(mobile)){
            listener.onUpdateMobileFailure(context.getString(R.string.wrong_mobile));
            error = true;
            return;
        }

        if(DBAccess.init(context).isMobileAlreadyExists(mobile)){
            listener.onUpdateMobileFailure(context.getString(R.string.mobile_already_exists));
            error = true;
            return;
        }

        if (!error){

            listener.onUpdateMobileSuccess(authentication);
            return;
        }
    }
}
