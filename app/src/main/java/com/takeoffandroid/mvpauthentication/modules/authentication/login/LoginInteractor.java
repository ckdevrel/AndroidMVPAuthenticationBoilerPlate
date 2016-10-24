package com.takeoffandroid.mvpauthentication.modules.authentication.login;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface LoginInteractor {

    interface OnLoginFinishedListener {

        void onValidationSuccess(Authentication authentication);

        void onValidationError(String message);

    }

    void login(Context context, Authentication authentication, OnLoginFinishedListener listener);

}