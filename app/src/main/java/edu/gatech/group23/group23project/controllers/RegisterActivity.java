package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;

public class RegisterActivity extends AppCompatActivity {


    private EditText userText;
    private EditText passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userText = (EditText) findViewById(R.id.usernameBox);
        passText = (EditText) findViewById(R.id.passwordBox);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
    }

    private void attemptRegister() {
        userText.setError(null);
        passText.setError(null);

        if (userText.getText().length() < 1) {
            userText.setError("You must choose a username.");
        } else if (passText.getText().length() < 1) {
            passText.setError("You must choose a password.");
        } else {
            Model.registerUser(userText.getText().toString(), passText.getText().toString());
            Context context = RegisterActivity.this;
            Intent intent = new Intent(context, LoggedInActivity.class);
            context.startActivity(intent);
        }
    }
}
