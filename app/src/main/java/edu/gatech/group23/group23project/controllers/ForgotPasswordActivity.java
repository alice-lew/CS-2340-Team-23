package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.User;

/**
 * Created by gautum on 4/22/17.
 */

public class ForgotPasswordActivity extends AppCompatActivity {


    //input methods
    private EditText passwordText;
    private EditText username;

    private verificationTask mAuthTask = null;
    //get instance
    private  Model modelInstance = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        passwordText = (EditText) findViewById(R.id.passwordBox);
        username = (EditText) findViewById(R.id.email);


        Button cancelButton = (Button) findViewById(R.id.cancelLoginButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = ForgotPasswordActivity.this;
                Intent intent = new Intent(context, LoginActivity.class);
                finish();
                context.startActivity(intent);
            }
        });

        Button registerButton = (Button) findViewById(R.id.saveButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify();
            }
        });

    }

    private void verify() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        passwordText.setError(null);
        username.setError(null);

        // Store values at the time of the login attempt.
        String email = username.getText().toString();
        String password = passwordText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            passwordText.setError("pass cant be null.");
            focusView = passwordText;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            username.setError(getString(R.string.error_field_required));
            focusView = username;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            mAuthTask = new verificationTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    public class verificationTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassRecovery;

        verificationTask(String email, String password) {
            mEmail = email;
            mPassRecovery = password;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (User aUser : modelInstance.getUserSet()) {
                String[] pieces = aUser.getEmailRecovery().split(" ");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    if(pieces[1].equals(mPassRecovery)) {
                        modelInstance = Model.getInstance();
                        modelInstance.setCurrentUser(aUser);
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;


            if (success) {
                Context context = ForgotPasswordActivity.this;
                Intent intent = new Intent(context, EditProfileActivity.class);
                context.startActivity(intent);
            } else {
                passwordText.setError(getString(R.string.error_incorrect_password));
                passwordText.requestFocus();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onCancelled() {
            mAuthTask = null;

        }
    }

    public void onBackPressed() {
        //makes the hardware back button return the user to the welcome page
        Context context = ForgotPasswordActivity.this;
        Intent intent = new Intent(context, WelcomeActivity.class);
        finish();
        context.startActivity(intent);
    }
}
