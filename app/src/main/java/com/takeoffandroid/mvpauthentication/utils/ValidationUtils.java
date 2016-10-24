package com.takeoffandroid.mvpauthentication.utils;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.regex.Pattern;

public class ValidationUtils {

    //Regex for login and signup password validation
    private static final String PASSWORD_REGEX = "[a-zA-Z0-9 ]*";

    //Regex for login and signup malaysian phone number validation
    private static final String MALAYSIA_PHONE_NUMBER_REGEX = "^6?01\\d{8}$";


    /**
     * Method to check whether the string is empty or not.
     * @param string
     * @return boolean true if string is empty
     */
    public static boolean isNullOrEmpty(String string){
            return TextUtils.isEmpty(string);
    }


    /**
     * Method to check whether the String[] element is empty or not.
     * @param strings
     * @return boolean true if any of the String[] element is empty
     *
     */
    public static boolean isNullOrEmpty(String[] strings){
        for(String string : strings){
            return TextUtils.isEmpty(string);
        }
        return false;

    }


    /**
     * Method to check valid malaysia mobile number
     * @param mobile - Malaysian mobile number (Eg: 60123456788 or 0112345678)
     * @return boolean true if mobile number is valid
     */
    public static boolean isValidMobile(@NonNull String mobile) {
            return !TextUtils.isEmpty(mobile) && Pattern.compile(MALAYSIA_PHONE_NUMBER_REGEX).matcher(mobile.trim()).matches();
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
