package com.takeoffandroid.mvpauthentication.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.takeoffandroid.mvpauthentication.R;
import com.takeoffandroid.mvpauthentication.database.DBAccess;
import com.takeoffandroid.mvpauthentication.general.Constants;
import com.takeoffandroid.mvpauthentication.models.Authentication;
import com.takeoffandroid.mvpauthentication.modules.authentication.profileupdate.ProfileUpdatePresenter;
import com.takeoffandroid.mvpauthentication.modules.authentication.profileupdate.ProfileUpdateView;
import com.takeoffandroid.mvpauthentication.modules.authentication.profileupdate.ProileUpdatePresenterImpl;
import com.takeoffandroid.mvpauthentication.utils.ActivityUtils;
import com.takeoffandroid.mvpauthentication.utils.GSONUtils;
import com.takeoffandroid.mvpauthentication.utils.SharedPrefsUtils;
import com.takeoffandroid.mvpauthentication.utils.Utils;

/**
 * Created by chandrasekar on 21/10/16.
 */

public class MainActivity extends BaseActivity implements ProfileUpdateView {

    private Toolbar toolbar;
    private TextView txtName;
    private TextView txtMobile;

    private Button btnUserType;

    private ProfileUpdatePresenter presenter;
    private Authentication mAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new ProileUpdatePresenterImpl(MainActivity.this,this);

        mAuthentication = ActivityUtils.getBundleParecelable(MainActivity.this, Constants.INTENT.KEY_SEND_AUTHENTICATION);

        /**
         *Inserting 'true' boolean for mainscreen traction. If the app is launched next time. MainActivity.java
         *will be called after SplashActivity.java.
         */
        SharedPrefsUtils.putBoolean(Constants.SHARED_PREFS.KEY_FLAG_MAINSCREEN, true, this);


        findViews();

        setSupportActionBar(toolbar);

        //Updating firstName, lastName and mobile text fields.
        updateFields(mAuthentication);

    }

    private void updateFields(Authentication authentication) {



        /**
         *Storing all the Authentication model pojo data into shared prefrences to populate the fields
         *when the app is launched by closing back or clearing from the recent task.
         */
        SharedPrefsUtils.putString(Constants.SHARED_PREFS.KEY_STORE_AUTHENTICATION_DATA, GSONUtils.createJSONStringFromPojo(this, authentication), this);

        if(authentication!=null) {
            String firstName = !TextUtils.isEmpty(authentication.getFirstName()) ? authentication.getFirstName() : "No name";

            String lastName = !TextUtils.isEmpty(authentication.getLastName()) ? authentication.getLastName() : "";

            String mobile = !TextUtils.isEmpty(authentication.getMobile()) ? authentication.getMobile() : "No Mobile Number Registered";

            txtName.setText(firstName + " "+ lastName);

            txtMobile.setText(mobile);
        }
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtName = (TextView) findViewById(R.id.txt_name);
        txtMobile = (TextView) findViewById(R.id.txt_mobile);

        btnUserType = (Button) findViewById(R.id.btn_user_type);


        txtName.setTypeface(getTypeface());

        txtMobile.setTypeface(getTypeface());

        btnUserType.setTypeface(getTypeface());

        btnUserType.setOnClickListener(this);

        txtMobile.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_logout, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:

                /**
                 *On logout clearing the flag for main screen traction by assigning 'false', so that
                 *during next time launch LoginActivity called.
                 */
                SharedPrefsUtils.putBoolean(Constants.SHARED_PREFS.KEY_FLAG_MAINSCREEN, false, MainActivity.this);

                ActivityUtils.launchActivity(MainActivity.this, SplashActivity.class, true);

                return true;


        }

        return super.onOptionsItemSelected(item); // important line
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_user_type:

                Utils.showToast(MainActivity.this, getString(R.string.toast_button_user_type));

                break;

            case R.id.txt_mobile:

                openEditMobileDialog();
                break;
        }
    }


    private void openEditMobileDialog(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View subView = inflater.inflate(R.layout.dialog_mobile_edit, null);

        final EditText edtMobile = (EditText)subView.findViewById(R.id.edt_mobile);

        edtMobile.setTypeface(Utils.getTypeface(MainActivity.this));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit your mobile number");
        builder.setView(subView);
        final AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                presenter.updateMobile(new Authentication(edtMobile.getText().toString()));

                alertDialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                alertDialog.dismiss();
            }
        });

        builder.show();
    }


    @Override
    public void onUpdateMobileSuccess(Authentication authentication) {

        /**
         * Fetching values from the database to update mobile field value.
         */
        Authentication authenticationDetails = DBAccess.init(this)
                .getAuthenticationDetails(DBAccess.KEY_EMAIL, mAuthentication.getEmail());

        /**
         * Updating new mobile number by replacing old mobile number with respect to
         * email id.
         */
        DBAccess.init(this).updateMobile(authenticationDetails.getEmail(),authenticationDetails.getMobile(),authentication.getMobile());

        /**
         * Once after successful updation. Fetching the values again from the database and updating
         * the userName, firstName, mobile text fields
         */
        updateFields(DBAccess.init(this).getAuthenticationDetails(DBAccess.KEY_EMAIL,mAuthentication.getEmail()));

        Utils.showSnackShort(btnUserType, getString(R.string.edit_mobile_number_success));

    }

    @Override
    public void onUpdateMobileFailure(String message) {

        Utils.showSnackShort(btnUserType, message);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
