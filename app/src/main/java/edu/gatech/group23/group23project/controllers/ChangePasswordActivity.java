package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;

/**
 * Created by Soham on 4/24/2017.
 */

public class ChangePasswordActivity {

    private EditText newPass;
    private EditText confirmPass;

    private final Model modelInstance = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPass = (EditText) findViewById(R.id.newPasswordBox);
        confirmPass = (EditText) findViewById(R.id.confPasswordBox);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button changeButton = (Button) findViewById(R.id.saveButton);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptChangePass();
            }
        });
    }

    /**
     * Checks the input the user entered in the change password activity
     * page meets the standard, and changes the password
     * Will alert the user if they failed to properly enter input to one of the input fields
     */
    private void attemptChangePass() {
        newPass.setError(null);
        confirmPass.setError(null);

        String testNP = newPass.getText().toString();
        String testConf = confirmPass.getText().toString();

        if (newPass.getText().length() < 1) {
            newPass.setError("You must enter a new password");
        } else if (confirmPass.getText().length() < 1) {
            confirmPass.setError("You must confirm password");
        } else if (newPass.getText().length()
                != confirmPass.getText().length()) {
            confirmPass.setError("Confirmed password must" +
                    "be of same length as new password");
        } else {
            boolean flag = true;
            for (int i = 0; i < newPass.getText().length(); i++) {
                if ((testNP.charAt(i)) != (testConf.charAt(i))) {
                    flag = false;
                }
            }
            if (flag) {
                modelInstance.setNewPass(newPass.getText().toString());

                Context context = ChangePasswordActivity.this;
                Intent intent = new Intent(context, LoggedInActivity.class);
                finish();
                context.startActivity(intent);
            }
        }
    }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onBackPressed() {
            //makes the hardware back button return to the logged in screen
            Context context = ChangePasswordActivity.this;
            Intent intent = new Intent(context, LoggedInActivity.class);
            finish();
            context.startActivity(intent);
        }

    }

}
