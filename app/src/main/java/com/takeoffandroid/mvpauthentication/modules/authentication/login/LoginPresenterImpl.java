package com.takeoffandroid.mvpauthentication.modules.authentication.login;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private Context mContext;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(Context context,LoginView loginView) {

        this.mContext = context;
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override public void validateCredentials(Authentication authentication) {

        loginInteractor.login(mContext,authentication, this);
    }

    @Override public void onDestroy() {
        loginView = null;
    }




    @Override
    public void onValidationSuccess(Authentication authentication) {
        if (loginView != null) {
            loginView.onValidationSuccess(authentication);
        }
    }

    @Override
    public void onValidationError(String message) {

        if (loginView != null) {
            loginView.onValidationError(message);
        }
    }
}