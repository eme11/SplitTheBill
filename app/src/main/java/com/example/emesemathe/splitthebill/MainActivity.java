package com.example.emesemathe.splitthebill;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  Toolbar toolbar_;
    private  DrawerLayout drawerLayout_;
    private  NavigationView navigationView_;
    private FragmentManager manager;

    private UtilitiesFragment supplies_;
    private UtilitiesMainFragment utilities_;
    private LogInFragment logIn_;
    private ApartmentFragment createApartment_;
    private AboutFragment about_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_ = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar_);

        drawerLayout_ = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout_, toolbar_, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout_.addDrawerListener(toggle);
        toggle.syncState();

        navigationView_ = (NavigationView) findViewById(R.id.nav_view);
        navigationView_.setNavigationItemSelectedListener(this);

        supplies_ = new UtilitiesFragment();
        logIn_ = new LogInFragment();
        createApartment_ = new ApartmentFragment();
        utilities_ = new UtilitiesMainFragment();
        about_ = new AboutFragment();

        setTitle("About");
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContent, about_ ).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_sign_in) {

            setTitle("Sign in or Sign Up");
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.flContent, logIn_ ).commit();

        } else if (id == R.id.nav_apartment) {
            setTitle("Apartment");

        } else if (id == R.id.nav_split_utilities) {
            setTitle("Split Utilities");
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.flContent, utilities_ ).commit();

        } else if (id == R.id.nav_split_household) {

            setTitle("Split household supplies");
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.flContent, supplies_ ).commit();

        } else if (id == R.id.nav_settings) {
            setTitle("User Settings");

        } else if (id == R.id.nav_about){
            setTitle("About the app");
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.flContent, about_ ).commit();

        } else if (id == R.id.nav_create_apartment) {
            setTitle("Create Apartment");
            manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.flContent, createApartment_ ).commit();

        } else if (id == R.id.nav_add_people) {
            setTitle("Add People");

        }

        item.setChecked(true);
        drawerLayout_.closeDrawers();
        return true;
    }
}
