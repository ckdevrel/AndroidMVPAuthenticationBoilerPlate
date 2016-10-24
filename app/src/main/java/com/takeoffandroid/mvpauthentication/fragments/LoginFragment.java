package com.takeoffandroid.mvpauthentication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.takeoffandroid.mvpauthentication.R;
import com.takeoffandroid.mvpauthentication.activities.MainActivity;
import com.takeoffandroid.mvpauthentication.database.DBAccess;
import com.takeoffandroid.mvpauthentication.general.Constants;
import com.takeoffandroid.mvpauthentication.models.Authentication;
import com.takeoffandroid.mvpauthentication.modules.authentication.login.LoginPresenter;
import com.takeoffandroid.mvpauthentication.modules.authentication.login.LoginPresenterImpl;
import com.takeoffandroid.mvpauthentication.modules.authentication.login.LoginView;
import com.takeoffandroid.mvpauthentication.utils.ActivityUtils;
import com.takeoffandroid.mvpauthentication.utils.Utils;


public class LoginFragment extends BaseFragment implements LoginView {

    private EditText edtEmail;

    private EditText edtPass;
    private Button btnLogin;

    private LoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginPresenterImpl(getActivity(),this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {


        edtEmail = (EditText)view.findViewById(R.id.edt_email);
        edtPass = (EditText)view.findViewById( R.id.edt_pass );
        btnLogin = (Button)view.findViewById( R.id.btn_login );

        edtEmail.setTypeface(getTypeface());

        edtPass.setTypeface(getTypeface());
        btnLogin.setTypeface(getTypeface());

        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();

        switch (v.getId()){

            case R.id.btn_login:


                presenter.validateCredentials(new Authentication(email,password));


                break;
        }
    }




    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onValidationSuccess(Authentication authentication) {


        /**
         * Fetching values from the database when the validation is success.
         */
        Authentication authenticationDetails = DBAccess.init(getActivity())
                .getAuthenticationDetails(DBAccess.KEY_EMAIL, authentication.getEmail());


        ActivityUtils.launchActivityFromFragment(getActivity(), MainActivity.class, true, Constants.INTENT.KEY_SEND_AUTHENTICATION,authenticationDetails);


    }

    @Override
    public void onValidationError(String message) {

        Utils.showSnackShort(btnLogin, message);
    }
}
