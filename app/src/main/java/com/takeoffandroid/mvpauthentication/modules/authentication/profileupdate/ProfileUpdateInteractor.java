package com.takeoffandroid.mvpauthentication.modules.authentication.profileupdate;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface ProfileUpdateInteractor {

    interface OnProfileUpdateFinishedListener {

        void onUpdateMobileSuccess(Authentication authentication);

        void onUpdateMobileFailure(String message);

    }

    void update(Context context, Authentication authentication, OnProfileUpdateFinishedListener listener);

}