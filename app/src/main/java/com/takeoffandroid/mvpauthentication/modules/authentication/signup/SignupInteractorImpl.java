package com.takeoffandroid.mvpauthentication.modules.authentication.signup;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.R;
import com.takeoffandroid.mvpauthentication.database.DBAccess;
import com.takeoffandroid.mvpauthentication.models.Authentication;
import com.takeoffandroid.mvpauthentication.utils.ValidationUtils;

public class SignupInteractorImpl implements SignupInteractor {


    @Override
    public void signup(Context context, Authentication authentication, OnSignupFinishedListener listener) {

        String email = authentication.getEmail();

        String password = authentication.getPassword();

        String mobile = authentication.getMobile();

        String userType = authentication.getUserType();

        boolean error = false;

        if (ValidationUtils.isNullOrEmpty(new String[]{email, password})){
            listener.onValidationError(context.getResources().getString(R.string.wrong_form_data));
            error = true;
            return;
        }


        if (!ValidationUtils.isValidMobile(mobile)){
            listener.onValidationError(context.getResources().getString(R.string.wrong_mobile));
            error = true;
            return;
        }

        if (!ValidationUtils.isValidEmail(email)){
            listener.onValidationError(context.getResources().getString(R.string.wrong_email));
            error = true;
            return;
        }

        if(DBAccess.init(context).isEmailAlreadyExists(email)){
            listener.onValidationError(context.getResources().getString(R.string.email_already_exists));
            error = true;
            return;
        }

        if (!ValidationUtils.isValidPassword(password)){
            listener.onValidationError(context.getResources().getString(R.string.wrong_password));
            error = true;
            return;
        }

        if(ValidationUtils.isNullOrEmpty(userType)){
            listener.onValidationError(context.getResources().getString(R.string.wrong_user_type));
            error = true;
            return;
        }


        if (!error){
            listener.onValidationSuccess(authentication);
            return;
        }
    }
}
