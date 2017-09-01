package com.takeoffandroid.mvpauthentication.modules.authentication.signin;

public class SigninModel {

    private String mobile;
    private String password;


    public SigninModel(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public SigninModel() {

    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
