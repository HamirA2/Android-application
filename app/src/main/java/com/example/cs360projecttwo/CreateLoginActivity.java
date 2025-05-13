package com.example.cs360projecttwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Calendar;

public class CreateLoginActivity extends AppCompatActivity {
    // Use create login view for creating a new log in
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);

        // Get editable text fields for all values
        TextView usernameText = findViewById(R.id.username_edit_text);
        TextView passwordText = findViewById(R.id.password_edit_text);
        TextView dailyText = findViewById(R.id.daily_edit_text);
        TextView goalText = findViewById(R.id.goal_edit_text);
        //DatePicker datePicker = findViewById(R.id.weight_date);

        Button createButton = findViewById(R.id.log_in_button);
        // Create on click listener for create login button
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When clicked, get string values of each input
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String dailyValue = dailyText.getText().toString();
                String goalValue = goalText.getText().toString();
                //String dateValue = datePicker.toString();

                // If any value is empty, inform that all fields were not filled in
                if (username.isEmpty() || password.isEmpty() || dailyValue.isEmpty() ||
                    goalValue.isEmpty()) {
                    Toast.makeText(CreateLoginActivity.this, "Not all fields filled in!",
                                    Toast.LENGTH_LONG).show();
                }
                // Otherwise, store each value into the database using the DB handler
                else {
                    DBHandler dbHandler = new DBHandler(CreateLoginActivity.this);

                    dbHandler.addWeight(dailyValue, goalValue, username, password);

                    Toast.makeText(CreateLoginActivity.this, "Data Added!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(CreateLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}