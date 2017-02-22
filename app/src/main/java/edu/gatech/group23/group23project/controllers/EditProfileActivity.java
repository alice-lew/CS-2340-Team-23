package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.User;

/**
 * The page that lets a user edit their profile
 *
 * Created by Noah Blume on 2/19/2017.
 */
public class EditProfileActivity extends AppCompatActivity {

    //input fields for the edit profile page
    private EditText emailText;
    private EditText addressText;
    private EditText titleText;

    //gets the instance of the model
    private Model modelInstance = Model.getInstance();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        User curUser = modelInstance.getCurrentUser();

        emailText = (EditText) findViewById(R.id.emailBox);
        addressText = (EditText) findViewById(R.id.addressBox);
        titleText = (EditText) findViewById(R.id.titleBox);

        emailText.setText(curUser.getEmail());
        addressText.setText(curUser.getHome());
        titleText.setText(curUser.getTitle());

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button registerButton = (Button) findViewById(R.id.saveButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptEditProfile();
            }
        });
    }

    /**
     * Checks the input the user entered in the edit profile page, and attempts to edit the user profile
     * Will alert the user if they failed to properly enter input to one of the input fields
     */
    private void attemptEditProfile() {
        emailText.setError(null);
        addressText.setError(null);
        titleText.setError(null);

        if (emailText.getText().length() < 1) {
            emailText.setError("You must enter your email.");
        } else if (addressText.getText().length() < 1) {
            addressText.setError("You must enter your address.");
        } else if (titleText.getText().length() < 1) {
            titleText.setError("You must enter your title.");
        } else if (!emailText.getText().toString().contains("@")) {
            emailText.setError("You must enter a valid email address.");
        }else {
            User curUser = modelInstance.getCurrentUser();
            curUser.setEmail(emailText.getText().toString());
            curUser.setHome(addressText.getText().toString());
            curUser.setTitle(titleText.getText().toString());

            Context context = EditProfileActivity.this;
            Intent intent = new Intent(context, LoggedInActivity.class);
            context.startActivity(intent);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //makes the hardware back button return to the logged in screen
        Context context = EditProfileActivity.this;
        Intent intent = new Intent(context, LoggedInActivity.class);
        context.startActivity(intent);
        return;
    }
}
