package com.takeoffandroid.mvpauthentication.modules.authentication.signin;

import com.takeoffandroid.mvpauthentication.utils.ValidationUtils;


public class SigninPresenter  {


    private SigninView signinView;


    public SigninPresenter() {
    }


    public void attachView(SigninView signinView) {
        this.signinView = signinView;
    }

    public void detachView() {
        signinView = null;
    }


    public void doSignin(SigninModel signinModel){

        String mobile = signinModel.getMobile();
        String password = signinModel.getPassword();

        boolean error = false;

        if (!ValidationUtils.isValidString(mobile)){
            signinView.onMobileNumberEmpty();
            error = true;
            return;
        }


        if (!ValidationUtils.isValidString(password)){
            signinView.onPasswordEmpty();
            error = true;
            return;
        }

        if (!ValidationUtils.isValidPassword(password)){
            signinView.onPasswordInvalid();
            error = true;
            return;
        }



        if (!error){
            signinView.onValidationSuccess(signinModel);
            return;
        }


    }




}
