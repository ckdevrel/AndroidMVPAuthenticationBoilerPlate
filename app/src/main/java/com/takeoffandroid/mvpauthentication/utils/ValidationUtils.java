package com.takeoffandroid.mvpauthentication.utils;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.regex.Pattern;

public class ValidationUtils {

    //Regex for login and signup password validation
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9 ]*";



    /**
     * Method to check whether the string is empty or not.
     * @param string
     * @return boolean true if string is empty
     */
    public static boolean isValidString(String string){
            return !TextUtils.isEmpty(string);
    }


    /**
     * Method to check whether the String[] element is empty or not.
     * @param strings
     * @return boolean true if any of the String[] element is empty
     *
     */
    public static boolean isValidString(String[] strings){
        for(String string : strings){
            return isValidString(string);
        }
        return false;

    }


    /**
     * Method to check valid indian mobile number
     * @param mobile
     * @return boolean true if mobile number is valid
     */
    public static boolean isValidMobile(@NonNull String mobile) {

        if(!TextUtils.isEmpty(mobile) && mobile.length() == 10){
            if( mobile.startsWith("9") || mobile.startsWith("8") || mobile.startsWith("7")){
                return true;
            }
        }

        return false;
    }

    /**
     * Method to check valid password
     * @param password - Password should be atleast 8 characters with one special character (Eg: abcdefgh@)
     * @return boolean true if password is valid
     */
    public static boolean isValidPassword(@NonNull String password){
            return !TextUtils.isEmpty(password) && password.length()>=8 && !(Pattern.compile(PASSWORD_REGEX).matcher(password).matches());
    }


    /**
     * Mehtod to cheeck valid email
     * @param email - Email id of the user
     * @return boolean true if email is valid
     */
    public static boolean isValidEmail(@NonNull String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
