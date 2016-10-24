package com.takeoffandroid.mvpauthentication.modules.authentication.login;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface LoginView {


    void onValidationSuccess(Authentication authentication);

    void onValidationError(String message);
}