package com.example.cs360projecttwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
// Create view for logging a user in
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Declare variables to get values of edit text
        EditText usernameInput = findViewById(R.id.username_edit_text);
        EditText passwordInput = findViewById(R.id.password_edit_text);
        EditText dailyWeightInput = findViewById(R.id.daily_number);
        EditText goalWeightInput = findViewById(R.id.goal_number);
        Button loginButton = findViewById(R.id.log_in_button);

        // Declare database handler for reading and adding data
        DBHandler dbHandler = new DBHandler(LoginActivity.this);

        // read the values of the database using the database handler
        ArrayList<DBValues> weightDBValues = dbHandler.readWeight();

        // Set on click listener to the log in button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the array of database values is not empty check on input values
                if (!weightDBValues.isEmpty()) {
                    // Check to see if username and password match the account username and password
                    if (usernameInput.getText().toString().equals(weightDBValues.get(0).GetAccountName())
                        && passwordInput.getText().toString().equals(weightDBValues.get(0).GetAccountPassword())) {

                        // Add the new data into the database handler
                        dbHandler.addWeight(dailyWeightInput.getText().toString(),
                                            goalWeightInput.getText().toString(),
                                            usernameInput.getText().toString(),
                                            passwordInput.getText().toString());

                        // Show the data was added
                        Toast.makeText(LoginActivity.this, "Data Added!", Toast.LENGTH_LONG)
                                .show();

                        // Go back to the main activity
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                    // Display message when incorrect username or password is input
                    else {
                        Toast.makeText(LoginActivity.this,
                                      "Incorrect username or password, try again!",
                                       Toast.LENGTH_LONG).show();
                    }
                }
                // If the database is empty, show there is no data in the database
                else {
                    Toast.makeText(LoginActivity.this, "No Data found!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        Button createLogButton = findViewById(R.id.create_log_in_button);
        // Add on click listener to create login button moving user to create login screen
        createLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateLoginActivity.class);
                startActivity(intent);
            }
        });

    }
}