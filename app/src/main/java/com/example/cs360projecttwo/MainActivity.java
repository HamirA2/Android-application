package com.example.cs360projecttwo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check for permission to send sms messages, if no permission granted, request permission
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            Log.d("Weight Tracker", "Permission not granted");
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},
                    123);
        }
        // Otherwise permission is granted
        else {
            Log.d("Weight Tracker", "Permission granted");
        }


        // Set up menu bar to change between different pages
        BottomNavigationView menuBar = findViewById(R.id.bottom_nav);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        // Get navController and configure menu bar to build fragments within bottom menu bar
        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();

            AppBarConfiguration appBarConfig = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_process)
                    .build();

            // Set up this activity with the nav controller and menu bar for transitions
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
            NavigationUI.setupWithNavController(menuBar, navController);
        }
    }

    // Check the results of the permission check popup
    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) {
        // If permission was granted, log permission granted message
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Weight Tracker", "Permission granted");
            }
            // Otherwise, log permission not granted message
            else {
                Log.d("Weight Tracker", "Permission not granted");
            }
        }
    }
}