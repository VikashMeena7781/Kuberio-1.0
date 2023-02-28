package com.vikash.kuberio10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vikash.kuberio10.Dashboard_2.Chat_Fragment.ChatFragment;
import com.vikash.kuberio10.Dashboard_2.HomeFrag;
import com.vikash.kuberio10.Dashboard_2.MoreFrag;
import com.vikash.kuberio10.Dashboard_2.ProductsFrag;
import com.vikash.kuberio10.Dashboard_2.TrackerFrag;
import com.vikash.kuberio10.Dashboard_2.ChatFrag;


public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

                }else if(id==R.id.chat){
                    loadfrag(new ChatFrag());
//                    Fragment chatFragment = new ChatFragment();
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.Conatiner, chatFragment);
//                    transaction.addToBackStack(null); // Add the fragment to the back stack
//                    transaction.commit();
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
//    public void loadfrag(Fragment fragment){
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        Fragment existingFragment = fm.findFragmentById(R.id.Conatiner);
//        if (existingFragment == null) {
//            ft.add(R.id.Conatiner, fragment);
//        } else {
//            ft.show(fragment);
//        }
//        ft.commit();
//    }

}
