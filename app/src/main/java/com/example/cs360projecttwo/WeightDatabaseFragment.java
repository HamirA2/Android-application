package com.example.cs360projecttwo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WeightDatabaseFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_weight_database, container, false);

        // Get the first account TextView
        TextView account = rootView.findViewById(R.id.account1);
        // Text elements for other types of data in grid
        TextView dailyWeight, goalWeight;
        TextView dateAdded;

        // Get the grid layout to use for populating its text views
        GridLayout gridChildren = rootView.findViewById(R.id.grid_layout);

        // Create a database handler for creating, reading, updating, and deleting stored data
        DBHandler dbHandler = new DBHandler(getContext());

        // Assign click listener to the plus floating action button
        FloatingActionButton fab = rootView.findViewById(R.id.plus_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // When clicked on, move to the log in screen
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        // Check to see if the account text view is empty
        if (account.getText().toString().isEmpty()) {
            // If empty, create an array to store values reading the weight DB values
            ArrayList<DBValues> weightDBValues = dbHandler.readWeight();

            // Used to populate the text view of each category using values from the database
            if (!weightDBValues.isEmpty()) {
                int j = 0;
                for (int i = 0; i < weightDBValues.size(); i++) {
                    // Index used to access empty TextViews
                    j += 5;

                    // Get each TextView child to populate with the database
                    dailyWeight = (TextView) gridChildren.getChildAt(j);
                    goalWeight = (TextView) gridChildren.getChildAt(j + 1);
                    dateAdded = (TextView) gridChildren.getChildAt(j + 2);
                    account = (TextView) gridChildren.getChildAt(j + 3);
                    gridChildren.getChildAt(j + 4).setEnabled(true);

                    // Set text of each TextView using the array with database values
                    dailyWeight.setText(weightDBValues.get(i).GetDailyWeight());
                    goalWeight.setText(weightDBValues.get(i).GetGoalWeight());
                    dateAdded.setText(weightDBValues.get(i).GetDateAdded());
                    account.setText(weightDBValues.get(i).GetAccountName());
                }

            }
        }

        TextView firstDailyWeight = rootView.findViewById(R.id.daily_weight1);
        TextView firstGoalWeight = rootView.findViewById(R.id.goal_weight1);
        TextView secondDailyWeight = rootView.findViewById(R.id.daily_weight2);
        TextView secondGoalWeight = rootView.findViewById(R.id.goal_weight2);

        // Used for setting the on click listener for each button
        for (int i = 0; i < gridChildren.getChildCount(); i++) {
            if (gridChildren.getChildAt(i) instanceof Button) {
                gridChildren.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Set the intent to go to the ChangeDataValues activity
                        Intent i = new Intent(getContext(), ChangeDataValuesActivity.class);

                        // If the id of the button is the first edit box, put the first values as extras
                        if (v.getId() == R.id.edit_box1) {
                            i.putExtra("dailyWeight", firstDailyWeight.getText().toString());
                            i.putExtra("goalWeight", firstGoalWeight.getText().toString());
                        }
                        else if (v.getId() == R.id.edit_box2) {
                            i.putExtra("dailyWeight", secondDailyWeight.getText().toString());
                            i.putExtra("goalWeight", secondGoalWeight.getText().toString());
                        }

                        // Start the next activity
                        startActivity(i);
                    }
                });
            }
        }


        return rootView;
    }

}