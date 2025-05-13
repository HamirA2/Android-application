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

public class FoodInformation extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);

        // Get each description
        TextView appleDescription = findViewById(R.id.apple_description);
        TextView bananaDescription = findViewById(R.id.banana_description);
        TextView grapeDescription = findViewById(R.id.grape_description);
        TextView yogurtDescription = findViewById(R.id.yogurt_description);
        TextView nutsDescription = findViewById(R.id.nuts_description);
        TextView peanutButterDescription = findViewById(R.id.peanut_butter_description);

        // Set on click listener to each description
        appleDescription.setOnClickListener(this);
        bananaDescription.setOnClickListener(this);
        grapeDescription.setOnClickListener(this);
        yogurtDescription.setOnClickListener(this);
        nutsDescription.setOnClickListener(this);
        peanutButterDescription.setOnClickListener(this);
    }

    // on click listener used for expanding or collapsing each description
    @Override
    public void onClick(View v) {
        // Get the view as a text view
        TextView description = (TextView) v;

        // If the max lines of the description is 3, expand the lines
        if (description.getMaxLines() == 3) {
            description.setMaxLines(Integer.MAX_VALUE);
        }
        // Otherwise, collapse the lines back to 3
        else {
            description.setMaxLines(3);
        }
    }
}