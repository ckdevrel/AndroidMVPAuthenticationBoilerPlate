package com.takeoffandroid.mvpauthentication.modules.authentication.profileupdate;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public interface ProfileUpdateView {

    void onUpdateMobileSuccess(Authentication authentication);

    void onUpdateMobileFailure(String message);

}