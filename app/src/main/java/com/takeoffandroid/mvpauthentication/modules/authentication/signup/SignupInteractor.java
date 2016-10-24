package com.takeoffandroid.mvpauthentication.modules.authentication.signup;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface SignupInteractor {

    interface OnSignupFinishedListener {

        void onValidationSuccess(Authentication authentication);

        void onValidationError(String message);
    }

    void signup(Context context, Authentication authentication, OnSignupFinishedListener listener);

}