package com.takeoffandroid.mvpauthentication.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.takeoffandroid.mvpauthentication.R;
import com.takeoffandroid.mvpauthentication.activities.MainActivity;
import com.takeoffandroid.mvpauthentication.database.DBAccess;
import com.takeoffandroid.mvpauthentication.general.Constants;
import com.takeoffandroid.mvpauthentication.models.Authentication;
import com.takeoffandroid.mvpauthentication.modules.authentication.signup.SignupPresenter;
import com.takeoffandroid.mvpauthentication.modules.authentication.signup.SignupPresenterImpl;
import com.takeoffandroid.mvpauthentication.modules.authentication.signup.SignupView;
import com.takeoffandroid.mvpauthentication.utils.ActivityUtils;
import com.takeoffandroid.mvpauthentication.utils.Utils;
import com.takeoffandroid.mvpauthentication.views.CustomSpinnerAdapter;

import java.util.Arrays;


public class SignupFragment extends BaseFragment implements SignupView {

    private EditText edtMobile;

    private EditText edtEmail;
    private EditText edtFirstName;
    private EditText edtLastName;

    private EditText edtPass;
    private Button btnSignup;
    private Spinner spinnerUserType;

    private SignupPresenter presenter;
    private String mStrUserType;

    private int mUsertTypePosition = 4;

    private CustomSpinnerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new SignupPresenterImpl(getActivity(),this);

    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        findViews(view);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String[] spinnerValues =  getResources().getStringArray(R.array.signup_user_types);

        adapter = new CustomSpinnerAdapter(
                getActivity(),
                R.layout.view_spinner_textview,
                Arrays.asList(spinnerValues));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserType.setAdapter(adapter);

        spinnerUserType.setSelection(adapter.getCount());
        spinnerUserType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {

                        if(spinnerValues.length-1 != position ) {

                            mUsertTypePosition = position;
                            mStrUserType = parent.getItemAtPosition(position).toString();

                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        spinnerUserType.setSelection(mUsertTypePosition);


    }


    private void findViews(View view) {
        edtFirstName = (EditText) view.findViewById(R.id.edt_first_name);
        edtLastName = (EditText) view.findViewById(R.id.edt_last_name);
        edtMobile = (EditText) view.findViewById(R.id.edt_mobile);

        edtEmail = (EditText) view.findViewById(R.id.edt_email);
        edtPass = (EditText) view.findViewById(R.id.edt_pass);
        btnSignup = (Button) view.findViewById(R.id.btn_signup);

        spinnerUserType = (Spinner) view.findViewById(R.id.spinner_user_type);


        edtMobile.setTypeface(getTypeface());

        edtFirstName.setTypeface(getTypeface());
        edtLastName.setTypeface(getTypeface());

        edtEmail.setTypeface(getTypeface());
        edtPass.setTypeface(getTypeface());
        btnSignup.setTypeface(getTypeface());

        btnSignup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String firstName = edtFirstName.getText().toString();
        String lastName = edtLastName.getText().toString();

        String mobile = edtMobile.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();

        switch (v.getId()) {

            case R.id.btn_signup:

                presenter.validateCredentials(new Authentication(firstName,lastName,mobile,email, password,mStrUserType));

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
         * Inserting all the signup user fields into database.
         */
        DBAccess.init(getActivity()).insertUserMasterObject(authentication);

        /**
         * After successful insertiion, fetching the values again from the db.
         */
        Authentication authenticationDetails = DBAccess.init(getActivity())
                .getAuthenticationDetails(DBAccess.KEY_EMAIL, authentication.getEmail());


        ActivityUtils.launchActivityFromFragment(getActivity(), MainActivity.class, true, Constants.INTENT.KEY_SEND_AUTHENTICATION,authenticationDetails);
    }

    @Override
    public void onValidationError(String message) {


        Utils.showSnackShort(btnSignup, message);
    }
}
