//
//
//package com.example.layouts;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//
//import com.example.layouts.databinding.ActivityMainBinding;
//import com.google.android.material.navigation.NavigationView;
//
//public class MainActivity extends AppCompatActivity {
//
//    private ActivityMainBinding binding;
//    private NavController navController;
//    private DrawerLayout drawerLayout;
//    private NavigationView navigationView;
//    private ActionBarDrawerToggle drawerToggle;
//    private AppBarConfiguration appBarConfiguration;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        EdgeToEdge.enable(this);
//
//        navController = Navigation.findNavController(this, R.id.fragmentContainerView3);
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
//
//        setSupportActionBar(binding.toolbar);  // Ensure this matches your setup
//
//        drawerLayout = binding.drawerLayout; // Ensure this matches your setup
//        navigationView = binding.navigationView; // Ensure this matches your setup
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        navController = Navigation.findNavController(this, R.id.fragmentContainerView3);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                int itemId = item.getItemId();
//                if (itemId == R.id.homeFragment2) {
//                    navController.navigate(R.id.homeFragment2);
//                    Toast.makeText(MainActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
//                } else if (itemId == R.id.navProfile) {
//                    navController.navigate(R.id.navProfile);
//                    Toast.makeText(MainActivity.this, "Profile Selected", Toast.LENGTH_SHORT).show();
//                } else if (itemId == R.id.navSettings) {
//                    navController.navigate(R.id.navSettings);
//                    Toast.makeText(MainActivity.this, "Settings Selected", Toast.LENGTH_SHORT).show();
//                } else if (itemId == R.id.navRider) {
//                    navController.navigate(R.id.navRider);
//                    Toast.makeText(MainActivity.this, "Rider Selected", Toast.LENGTH_SHORT).show();
//                } else if (itemId == R.id.navHelp) {
//                    navController.navigate(R.id.navHelp);
//                    Toast.makeText(MainActivity.this, "Help Selected", Toast.LENGTH_SHORT).show();
//                } else if (itemId == R.id.navAboutus) {
//                    navController.navigate(R.id.navAboutus);
//                    Toast.makeText(MainActivity.this, "About Us Selected", Toast.LENGTH_SHORT).show();
//                } else if (itemId == R.id.logout) {
//                    Toast.makeText(MainActivity.this, "Logout Selected", Toast.LENGTH_SHORT).show();
//                    // Handle logout action
//                }
//                drawerLayout.closeDrawers();
//                return true;
//            }
//        });
//
//    }
//}

package com.example.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.layouts.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        navController = Navigation.findNavController(this, R.id.fragmentContainerView3);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        setSupportActionBar(binding.toolbar);  // Ensure this matches your setup

        drawerLayout = binding.drawerLayout; // Ensure this matches your setup
        navigationView = binding.navigationView; // Ensure this matches your setup
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navController = Navigation.findNavController(this, R.id.fragmentContainerView3);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.homeFragment2) {
                    Toast.makeText(MainActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.navProfile) {
                    startActivity(new Intent(MainActivity.this, navProfile.class));
                } else if (itemId == R.id.navFeeds) {
                    startActivity(new Intent(MainActivity.this, navFeed.class));
                } else if (itemId == R.id.navRateus) {
                    startActivity(new Intent(MainActivity.this, navRate.class));
                } else if (itemId == R.id.navHelp) {
                    startActivity(new Intent(MainActivity.this, navHelp.class));
                } else if (itemId == R.id.navAboutus) {
                    startActivity(new Intent(MainActivity.this, navAboutus.class));
                } else if (itemId == R.id.logout) {
                    startActivity(new Intent(MainActivity.this, loginActivity.class));
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
