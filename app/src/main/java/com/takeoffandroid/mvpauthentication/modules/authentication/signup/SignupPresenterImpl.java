package com.takeoffandroid.mvpauthentication.modules.authentication.signup;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public class SignupPresenterImpl implements SignupPresenter, SignupInteractor.OnSignupFinishedListener {

    private final Context mContext;
    private SignupView signupView;
    private SignupInteractor signupInteractor;

    public SignupPresenterImpl(Context context,SignupView signupView) {
        this.mContext = context;
        this.signupView = signupView;
        this.signupInteractor = new SignupInteractorImpl();
    }


    @Override
    public void validateCredentials(Authentication authentication) {
        signupInteractor.signup(mContext,authentication,this);
    }

    @Override public void onDestroy() {
        signupView = null;
    }




    @Override
    public void onValidationSuccess(Authentication authentication) {

        if (signupView != null) {
            signupView.onValidationSuccess(authentication);
        }
    }

    @Override
    public void onValidationError(String message) {

        if (signupView != null) {
            signupView.onValidationError(message);
        }
    }
}