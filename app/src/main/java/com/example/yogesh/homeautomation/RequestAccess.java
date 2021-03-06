package com.example.yogesh.homeautomation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RequestAccess extends Activity {
    private static final String msg= "RequestActivity: ";
    public static String RUSERNAME =
            "com.example.yogesh.homeautomation.rUserName";
    public static String RPASSWORD =
            "com.example.yogesh.homeautomation.rPassword";
    public static String RCONFIRMPASS =
            "com.example.yogesh.homeautomation.rConfirmPass";
    public static String REMAIL =
            "com.example.yogesh.homeautomation.rEmail";

    // UI references
    private EditText layoutUser, layoutPass, layoutConfirmPass,layoutEmail;
    Button layoutSendButton, layoutCancelButton;
    UserDatabase dbInstance;
    VerifyAction verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_access);
        Log.d(msg,"Oncreate called");

        //UI interface
        layoutUser = (EditText) findViewById(R.id.UserID);
        layoutPass = (EditText) findViewById(R.id.PassID);
        layoutConfirmPass = (EditText) findViewById(R.id.ConfirmPassID);
        layoutEmail = (EditText) findViewById(R.id.EmailID);
        layoutSendButton = (Button) findViewById(R.id.SendID);
        layoutCancelButton = (Button) findViewById(R.id.CancelID);

        //getting object of user database for managing data using sql
        dbInstance = new UserDatabase(getApplicationContext());
        dbInstance= dbInstance.open();

        //getting object for verify and action class
        verify = new VerifyAction();

        layoutSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(msg,"send button clicked");
                // Code below gets the User name and Password
                String rUserName = layoutUser.getText().toString();
                String rPassword = layoutPass.getText().toString();
                String rConfirmPass = layoutConfirmPass.getText().toString();
                String rEmail = layoutEmail.getText().toString();

                //Checking request details of user
                if(verify.hasFourChar(rUserName)){
                    if(verify.hasEightChar(rPassword)){
                        if(rPassword.equals(rConfirmPass)){
                            if(verify.validEmail(rEmail)){
                                displayOnScreen("Request is Granddted");
                                dbInstance.setUserName(rUserName);
                                dbInstance.StoreValues(rUserName,rPassword,rEmail);
                                finish();
                            }
                            else
                            {
                                displayOnScreen("Enter valid Email");
                            }
                        }
                        else
                        {
                            displayOnScreen("Passwords should matched");
                        }
                    }
                    else
                    {
                        displayOnScreen("Enter at least Eight character in Password");
                    }
                }
                else
                {
                    displayOnScreen("Enter at least Four character in username");
                }
            }
        });//onLitsener


        layoutCancelButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View view)
                    {
                        Log.d(msg,"Cancel button called");
                        finish();

                     }
             }
        );//onLitsener

        // restore the input strings after a rotation
        if (savedInstanceState != null) {
            Log.d(msg, "Restoring input strings");
            layoutUser.setText(savedInstanceState.getString(RUSERNAME));
            layoutPass.setText(savedInstanceState.getString(RPASSWORD));
            layoutConfirmPass.setText(savedInstanceState.getString(RCONFIRMPASS));
            layoutEmail.setText(savedInstanceState.getString(REMAIL));

        }
        else {
            clear_input();
        }
        Log.d(msg, "OnCreate called is over");
    }//onCreated

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(RUSERNAME,
                layoutUser.getText().toString());
        savedInstanceState.putString(RPASSWORD,
                layoutPass.getText().toString());
        savedInstanceState.putString(RCONFIRMPASS,
                layoutConfirmPass.getText().toString());
        savedInstanceState.putString(REMAIL,
                layoutEmail.getText().toString());
        Log.d(msg, "Saving input strings");
    }
    private void clear_input() {
        layoutUser.setText("");
        layoutPass.setText("");
        layoutConfirmPass.setText("");
        layoutEmail.setText("");
    }

    //displaying any massage for long time
    public void displayOnScreen (String displayStringLong){
        Toast.makeText(RequestAccess.this, displayStringLong ,Toast.LENGTH_LONG).show();
    }//displyOnScreen

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbInstance.close();
        Log.d(msg, "The onDestroy() event called");
    }
}
