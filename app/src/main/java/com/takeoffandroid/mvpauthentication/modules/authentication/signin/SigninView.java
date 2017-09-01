package com.takeoffandroid.mvpauthentication.modules.authentication.signin;


public interface SigninView {

    void onValidationSuccess(SigninModel model);

    void onMobileNumberEmpty();

    void onPasswordEmpty();

    void onMobileNumberInvalid();

    void onPasswordInvalid();

}
