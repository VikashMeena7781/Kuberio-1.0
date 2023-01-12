package com.vikash.kuberio10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vikash.kuberio10.Dasboard.HomeFrag;
import com.vikash.kuberio10.Dasboard.MoreFrag;
import com.vikash.kuberio10.Dasboard.ProductsFrag;
import com.vikash.kuberio10.Dasboard.TrackerFrag;
import com.vikash.kuberio10.Dasboard.WealthyFrag;


public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        BottomNavigationView bnView = findViewById(R.id.bnView);

        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.home){
                    // load the fragment ....create a method
                    loadfrag(new HomeFrag());
                }else if(id==R.id.Products){
                    loadfrag(new ProductsFrag());

                }else if(id==R.id.wealthy){
                    loadfrag(new WealthyFrag());

                }else if (id==R.id.tracker) {
                    loadfrag(new TrackerFrag());
                }
                else {
                    loadfrag(new MoreFrag());

                }
                return true; // if return value is false then it will not show which item is selected
            }
        });
        // by default konsa fragment open ho
        bnView.setSelectedItemId(R.id.home);


    }
    public void loadfrag(Fragment fragment){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.Conatiner,fragment);
            ft.commit();
    }
    }
