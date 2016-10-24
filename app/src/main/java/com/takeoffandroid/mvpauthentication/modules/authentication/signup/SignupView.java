package com.takeoffandroid.mvpauthentication.modules.authentication.signup;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface SignupView {

     void onValidationSuccess(Authentication authentication);

     void onValidationError(String message);
}