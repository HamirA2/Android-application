package com.example.cs360projecttwo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class ExerciseInformation extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_information);

        // Get each text view description of each exercise
        TextView pushDescription = findViewById(R.id.push_ups_description);
        TextView squatsDescription = findViewById(R.id.squats_description);
        TextView crunchesDescription = findViewById(R.id.crunches_description);
        TextView burpeesDescription = findViewById(R.id.burpees_description);
        TextView pullsDescription = findViewById(R.id.back_pulls_description);

        // Set on click listener for each exercise text description
        pushDescription.setOnClickListener(this);
        squatsDescription.setOnClickListener(this);
        crunchesDescription.setOnClickListener(this);
        burpeesDescription.setOnClickListener(this);
        pullsDescription.setOnClickListener(this);
    }

    // Listener used for each exercise description to expand text view
    @Override
    public void onClick(View v) {
        // Get the view as a text view
        TextView exerciseDescription = (TextView) v;

        // If the view has max lines of 3, set the max possible lines
        if (exerciseDescription.getMaxLines() == 3) {
            exerciseDescription.setMaxLines(Integer.MAX_VALUE);
        }
        // Otherwise, set the max lines of the text to 3
        else {
            exerciseDescription.setMaxLines(3);
        }
    }
}