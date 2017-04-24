package edu.gatech.group23.group23project.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Set;

import edu.gatech.group23.group23project.R;
import edu.gatech.group23.group23project.model.Model;
import edu.gatech.group23.group23project.model.ModelInterface;
import edu.gatech.group23.group23project.model.User;
import edu.gatech.group23.group23project.model.UserInterface;
import edu.gatech.group23.group23project.model.UserType;

public class BanActivity extends AppCompatActivity {
    private ModelInterface modelInstance = Model.getInstance();
    private EditText banInput;
    private EditText unbanInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);

        banInput = (EditText) findViewById(R.id.BanInput);
        unbanInput = (EditText) findViewById(R.id.UnbanInput);
        Button banButton = (Button) findViewById(R.id.BanButton);
        Button unbanButton = (Button) findViewById(R.id.UnbanButton);

        unbanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toUnban = unbanInput.getText().toString();
                Iterable<UserInterface> userSet = modelInstance.getUserSet();
                for (UserInterface u: userSet) {
                    if (u.getUsername().equals(toUnban) && u.getIsBanned()) {
                        u.unban();
                        u.resetLoginAttempts();
                        modelInstance.addSecurityLog(modelInstance.getCurrentUser().getUsername(), new Date(), "unbanned " + toUnban);
                        Toast.makeText(BanActivity.this, "User was unbanned.",
                                Toast.LENGTH_SHORT).show();
                        modelInstance.saveModel(modelInstance, BanActivity.this);
                        return;
                    }
                }
                Toast.makeText(BanActivity.this, "Failed to unban user.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        banButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toBan = banInput.getText().toString();
                Iterable<UserInterface> userSet = modelInstance.getUserSet();
                for (UserInterface u: userSet) {
                    if (u.getUsername().equals(toBan) && !u.getIsBanned() && u.getUserType() != UserType.ADMIN.ADMIN) {
                        u.ban();
                        modelInstance.addSecurityLog(modelInstance.getCurrentUser().getUsername(), new Date(), "banned " + toBan);
                        Toast.makeText(BanActivity.this, "User was banned.",
                                Toast.LENGTH_SHORT).show();
                        modelInstance.saveModel(modelInstance, BanActivity.this);
                        return;
                    }
                }
                Toast.makeText(BanActivity.this, "Failed to ban user.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        modelInstance.addSecurityLog(modelInstance.getCurrentUser().getUsername(), new Date(), "logged out");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        //changes the hardware back button functionality to return the user to the welcome page
        modelInstance.saveModel(modelInstance, this);
        Context context = BanActivity.this;
        Intent intent = new Intent(context, LoggedInActivity.class);
        context.startActivity(intent);
    }
}
