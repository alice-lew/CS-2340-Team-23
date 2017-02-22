package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;

/**
 * Created by Asher on 2/12/2017.
 */

public class LoggedInActivity extends AppCompatActivity {

    private Model modelInstance = Model.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);


        //button to cancel logging in and return to the welcome screen
        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        Button profileEditButton = (Button) findViewById(R.id.profileEditButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelInstance.setCurrentUser(null);
                Context context = view.getContext();
                Intent intent = new Intent(context, WelcomeActivity.class);
                context.startActivity(intent);
            }
        });

        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, EditProfileActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Context context = LoggedInActivity.this;
        Intent intent = new Intent(context, WelcomeActivity.class);
        context.startActivity(intent);
        return;
    }
}
