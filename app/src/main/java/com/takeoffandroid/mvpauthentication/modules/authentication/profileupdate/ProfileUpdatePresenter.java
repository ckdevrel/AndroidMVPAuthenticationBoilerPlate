package com.takeoffandroid.mvpauthentication.modules.authentication.profileupdate;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface ProfileUpdatePresenter {

    void updateMobile(Authentication authentication);

    void onDestroy();
}