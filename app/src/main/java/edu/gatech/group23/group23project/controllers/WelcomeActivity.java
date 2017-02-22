package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.group23.group23project.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        TextView instructionsText = (TextView) findViewById(R.id.welcomeInstructionsText);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.saveButton);
        welcomeText.setText("Welcome!");
        instructionsText.setText("Please sign in or register an account.");
        loginButton.setText("SIGN IN");
        registerButton.setText("REGISTER");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, RegisterActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        return;
    }
}