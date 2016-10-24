package com.takeoffandroid.mvpauthentication.modules.authentication.profileupdate;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.models.Authentication;

public class ProileUpdatePresenterImpl implements ProfileUpdatePresenter, ProfileUpdateInteractor.OnProfileUpdateFinishedListener {

    private final Context mContext;
    private ProfileUpdateView profileUpdateView;
    private ProfileUpdateInteractor profileUpdateInteractor;

    public ProileUpdatePresenterImpl(Context context, ProfileUpdateView profileUpdateView) {
        this.mContext = context;
        this.profileUpdateView = profileUpdateView;
        this.profileUpdateInteractor = new ProfileUpdateInteractorImpl();
    }


    @Override
    public void updateMobile(Authentication authentication) {
        profileUpdateInteractor.update(mContext,authentication,this);
    }

    @Override public void onDestroy() {
        profileUpdateView = null;
    }


    @Override
    public void onUpdateMobileSuccess(Authentication authentication) {
        if (profileUpdateView != null) {
            profileUpdateView.onUpdateMobileSuccess(authentication);
        }
    }

    @Override
    public void onUpdateMobileFailure(String message) {

        if (profileUpdateView != null) {
            profileUpdateView.onUpdateMobileFailure(message);
        }
    }
}