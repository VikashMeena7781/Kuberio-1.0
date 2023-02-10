package com.vikash.kuberio10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user!=null){
                    Intent intent = new Intent(SplashActivity.this,Intermediate.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },5000);

//        MyDbHandler db = new MyDbHandler(getApplicationContext());
//        List<Data> allcontact = db.getAllContacts();
//
//        for(Data contact: allcontact){
//
//            Log.d("dbharry", "\nId: " + contact.getId() + "\n" +
//                    "FirstName: " + contact.getFirstname() + "\n"+
//                    "LastName: " + contact.getLastname() + "\n"+
//                    "Phone Number: " + contact.getPhoneNumber() + "\n" +
//                    "Email Id: " + contact.getEmailid() + "\n" );
//        }
//        Log.d("dbharry","Number of contacts in Database "+db.getCount());
////

    }
}