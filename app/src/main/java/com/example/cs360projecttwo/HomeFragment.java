package com.example.cs360projecttwo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Get card view to allow action when clicking on either one
        CardView foodInfo = rootView.findViewById(R.id.first_card);
        CardView homeExercise = rootView.findViewById(R.id.second_card);

        // Onclick listener to switch to FoodInformation activity when clicking on its card
        foodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FoodInformation.class);
                startActivity(intent);
            }
        });

        // Onclick listener to switch to ExerciseInformation activity when clicking on its card
        homeExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ExerciseInformation.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}