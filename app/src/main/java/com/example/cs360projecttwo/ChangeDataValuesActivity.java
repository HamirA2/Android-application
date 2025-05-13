package com.example.cs360projecttwo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

// Activity used to edit daily and goal weight values
public class ChangeDataValuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data_values);

        DBHandler dbHandler = new DBHandler(ChangeDataValuesActivity.this);

        // Variable used to get the confirm and delete buttons
        Button confirmButton = findViewById(R.id.confirm_values);
        Button deleteButton = findViewById(R.id.delete_button);

        // Get the edit text with string values passed as extras
        EditText editDaily = findViewById(R.id.edit_daily_weight);
        EditText editGoal = findViewById(R.id.edit_goal_weight);
        String dailyWeight = getIntent().getStringExtra("dailyWeight");
        String goalWeight = getIntent().getStringExtra("goalWeight");

        // Set the value of each editable box with current values from weight database
        editDaily.setText(dailyWeight);
        editGoal.setText(goalWeight);

        // Set click listener to the confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set the intent to go back to the MainActivity
                Intent intent = new Intent(ChangeDataValuesActivity.this, MainActivity.class);

                // Go to the next activity when finished
                startActivity(intent);
            }
        });

        // Click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store boolean value of the deleteWeight method
                boolean hasDeleted = dbHandler.deleteWeight(dailyWeight);

                if (hasDeleted) {
                    Toast.makeText(ChangeDataValuesActivity.this, "Data Deleted!",
                                   Toast.LENGTH_LONG).show();
                }

                // After deletion, go to MainActivity
                Intent intent = new Intent(ChangeDataValuesActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}