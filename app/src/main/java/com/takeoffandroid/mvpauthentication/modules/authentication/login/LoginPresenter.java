package com.takeoffandroid.mvpauthentication.modules.authentication.login;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface LoginPresenter {

    void validateCredentials( Authentication authentication);

    void onDestroy();
}