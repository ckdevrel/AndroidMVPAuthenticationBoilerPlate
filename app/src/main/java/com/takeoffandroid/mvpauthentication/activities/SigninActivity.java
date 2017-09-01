package com.takeoffandroid.mvpauthentication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.takeoffandroid.mvpauthentication.R;
import com.takeoffandroid.mvpauthentication.modules.authentication.signin.SigninModel;
import com.takeoffandroid.mvpauthentication.modules.authentication.signin.SigninPresenter;
import com.takeoffandroid.mvpauthentication.modules.authentication.signin.SigninView;
import com.takeoffandroid.mvpauthentication.utils.Utils;


public class SigninActivity extends AppCompatActivity implements SigninView, View.OnClickListener {

    private EditText edtMobile;

    private EditText edtPass;
    private Button btnLogin;

    private SigninPresenter presenter;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signin);

        presenter = new SigninPresenter();
        presenter.attachView(this);


        initView();

        initToolbar();

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("TakeoffAndroid");
    }



    @Override
    public void onClick(View v) {

        String email = edtMobile.getText().toString();
        String password = edtPass.getText().toString();

        switch (v.getId()) {

            case R.id.btn_login:


                presenter.doSignin(new SigninModel(email, password));


                break;
        }
    }




    @Override
    public void onValidationSuccess(SigninModel model) {

        Utils.showSnackShort(btnLogin, "Validation Success");
    }

    @Override
    public void onMobileNumberEmpty() {

        Utils.showSnackShort(btnLogin, "Mobile Number should not be empty");

    }

    @Override
    public void onPasswordEmpty() {

        Utils.showSnackShort(btnLogin, "Password should not be empty");

    }

    @Override
    public void onMobileNumberInvalid() {

        Utils.showSnackShort(btnLogin, "Mobile Number is Invalid");

    }

    @Override
    public void onPasswordInvalid() {

        Utils.showSnackShort(btnLogin, "Password is Invalid");

    }

    private void initView() {

        edtMobile = (EditText) findViewById(R.id.edt_mobile);
        edtPass = (EditText) findViewById(R.id.edt_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }
}
