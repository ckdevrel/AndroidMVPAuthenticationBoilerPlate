package com.takeoffandroid.mvpauthentication.modules.authentication.signup;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface SignupPresenter {

    void validateCredentials(Authentication authentication);

    void onDestroy();
}