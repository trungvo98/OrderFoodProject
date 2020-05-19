package com.example.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity  {
    DrawerLayout drawerLayout;
    NavigationView   navigationView;
    Toolbar toolBar;
    TextView tvUsername;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //addControls();

        //Intent intent = getIntent();
        //String username = intent.getStringExtra("T_Username");
        //tvUsername.setText(username);

    }

    /*@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
    /*private void addControls() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_main);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_main);
        toolBar = (Toolbar) findViewById(R.id.toolbar);

        // find textview in layout_header_navigation because activity_main not have
        // (support package is support for sdk < 18)
        // support not have getHeaderView in navigation view
        View view = navigationView.inflateHeaderView(R.layout.layout_header_navigation);
        tvUsername = (TextView) view.findViewById(R.id.tv_username_navigation);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // get default icon set
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        // set default is DiningTableFragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        DiningTableFragment tableFragment = new DiningTableFragment();
        transaction.replace(R.id.content,tableFragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_main:
                FragmentTransaction transactionMain = fragmentManager.beginTransaction();
                DiningTableFragment tableFragment = new DiningTableFragment();
                transactionMain.setCustomAnimations(R.anim.effect_activity_in,R.anim.effect_activity_out);
                transactionMain.replace(R.id.content,tableFragment);
                transactionMain.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.menu_menufood:
                FragmentTransaction transactionMenu = fragmentManager.beginTransaction();
                MenuFoodFragment menuFragment = new MenuFoodFragment();
                transactionMenu.setCustomAnimations(R.anim.effect_activity_in,R.anim.effect_activity_out);
                transactionMenu.replace(R.id.content,menuFragment);
                transactionMenu.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;
            case R.id.menu_employee:
                FragmentTransaction transactionEmploy = fragmentManager.beginTransaction();
                EmployeeFragment employeeFragment = new EmployeeFragment();
                transactionEmploy.setCustomAnimations(R.anim.effect_activity_in,R.anim.effect_activity_out);
                transactionEmploy.replace(R.id.content,employeeFragment);
                transactionEmploy.commit();
                item.setCheckable(true);
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }*/
}
