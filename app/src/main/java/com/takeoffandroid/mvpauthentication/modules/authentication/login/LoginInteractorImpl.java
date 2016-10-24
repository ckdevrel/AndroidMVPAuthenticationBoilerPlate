package com.takeoffandroid.mvpauthentication.modules.authentication.login;

import android.content.Context;

import com.takeoffandroid.mvpauthentication.R;
import com.takeoffandroid.mvpauthentication.database.DBAccess;
import com.takeoffandroid.mvpauthentication.models.Authentication;
import com.takeoffandroid.mvpauthentication.utils.ValidationUtils;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(Context context, Authentication authentication, final OnLoginFinishedListener listener) {

        // Creating a handler to delay the answer a couple of seconds

                String email = authentication.getEmail();
                String password = authentication.getPassword();

                boolean error = false;

                if (ValidationUtils.isNullOrEmpty(new String[]{email, password})){
                    listener.onValidationError(context.getString(R.string.wrong_form_data));
                    error = true;
                    return;
                }

                if (!ValidationUtils.isValidEmail(email)){
                    listener.onValidationError(context.getString(R.string.wrong_email));
                    error = true;
                    return;
                }
                if (!ValidationUtils.isValidPassword(password)){
                    listener.onValidationError(context.getString(R.string.wrong_password));
                    error = true;
                    return;
                }


                if(!DBAccess.init(context).isVerifyUserByEmailAndPassword(email,password)){
                    listener.onValidationError(context.getString(R.string.email_password_combination_wrong));
                    error = true;
                    return;
                }

                if (!error){
                    listener.onValidationSuccess(authentication);
                    return;
                }

    }
}
