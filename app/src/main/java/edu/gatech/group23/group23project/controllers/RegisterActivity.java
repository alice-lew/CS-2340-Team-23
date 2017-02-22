package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText userText;
    private EditText passText;
    private EditText emailText;
    private EditText addressText;
    private EditText titleText;
    private Spinner userTypeSpinner;

    private Model modelInstance = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameText = (EditText) findViewById(R.id.nameBox);
        userText = (EditText) findViewById(R.id.usernameBox);
        passText = (EditText) findViewById(R.id.passwordBox);
        emailText = (EditText) findViewById(R.id.emailBox);
        addressText = (EditText) findViewById(R.id.addressBox);
        titleText = (EditText) findViewById(R.id.titleBox);
        userTypeSpinner = (Spinner) findViewById(R.id.userTypeSpinner);

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
                attemptRegister();
            }
        });

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.legalUserTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);
    }

    private void attemptRegister() {
        userText.setError(null);
        passText.setError(null);
        emailText.setError(null);
        addressText.setError(null);
        titleText.setError(null);

        if (nameText.getText().length() < 1) {
            nameText.setError("You must enter your name.");
        } else if (userText.getText().length() < 1) {
            userText.setError("You must choose a username");
        } else if (passText.getText().length() < 1) {
            passText.setError("You must choose a password.");
        } else if (emailText.getText().length() < 1) {
            emailText.setError("You must enter your email.");
        } else if (addressText.getText().length() < 1) {
            addressText.setError("You must enter your address.");
        } else if (titleText.getText().length() < 1) {
            titleText.setError("You must enter your title.");
        } else if (!emailText.getText().toString().contains("@")) {
            emailText.setError("You must enter a valid email address.");
        }else {
            User newUser = modelInstance.registerUser(nameText.getText().toString(), userText.getText().toString(),
                    passText.getText().toString(), emailText.getText().toString(), addressText.getText().toString(),
                    titleText.getText().toString(), User.getTypeFromString((String) userTypeSpinner.getSelectedItem()));
            modelInstance.setCurrentUser(newUser);
            Context context = RegisterActivity.this;
            Intent intent = new Intent(context, LoggedInActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Context context = RegisterActivity.this;
        Intent intent = new Intent(context, WelcomeActivity.class);
        context.startActivity(intent);
        return;
    }
}