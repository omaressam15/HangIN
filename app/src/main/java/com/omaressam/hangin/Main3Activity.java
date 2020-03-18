package com.omaressam.hangin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main3Activity extends AppCompatActivity
//        implements BottomNavigationView.OnNavigationItemSelectedListener
{
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initUI();
       // openFragment(new Home_Fragment());
    }

    void initUI() {
        bottomNavigationView = findViewById(R.id.bottomAppBar);
        NavController navController = Navigation.findNavController(this,R.id.main_frameLayout);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

//    void openFragment(Fragment fragment) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.main_frameLayout, fragment);
//        transaction.commit();
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_home:
//                openFragment(new Home_Fragment());
//                break;
//            case R.id.nav_booking:
//                openFragment(new fragment_bookings ());
//                break;
//            case R.id.nav_nearby:
//                openFragment(new fragment_nearby());
//                break;
//            case R.id.nav_more:
//                openFragment(new fragment_more());
//                break;
//        }
//
//        return true;
//    }
}
