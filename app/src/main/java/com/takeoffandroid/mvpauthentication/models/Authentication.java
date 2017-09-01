package com.takeoffandroid.mvpauthentication.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chandrasekar on 21/10/16.
 */

public class Authentication implements Parcelable {


    private String id;

    private String firstName;

    private String lastName;

    private String mobile;


    private String email;


    private String password;

    private String userType;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Authentication() {
    }


    /**
     * Used for signup
     * @param firstName
     * @param lastName
     * @param mobile
     * @param email
     * @param password
     * @param userType
     */
    public Authentication(String firstName, String lastName, String mobile, String email, String password, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.userType = userType;

    }


    /**
     * Used for login
     *
     * @param email
     * @param password
     */
    public Authentication(String email, String password) {
        this.email = email;
        this.password = password;
    }


    /**
     * Used for update mobile
     *
     * @param mobile
     */
    public Authentication(String mobile) {
        this.mobile = mobile;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.mobile);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.userType);
    }

    protected Authentication(Parcel in) {
        this.id = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.mobile = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.userType = in.readString();
    }

    public static final Creator<Authentication> CREATOR = new Creator<Authentication>() {
        @Override
        public Authentication createFromParcel(Parcel source) {
            return new Authentication(source);
        }

        @Override
        public Authentication[] newArray(int size) {
            return new Authentication[size];
        }
    };
}
