package com.vikash.kuberio10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.vikash.kuberio10.Database.Data;
import com.vikash.kuberio10.Database.MyDbHandler;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("user_data",MODE_PRIVATE);
                Boolean Is_login = pref.getBoolean("flag",false);
                String number = pref.getString("number",null);

                if(Is_login){
                    Intent intent = new Intent(SplashActivity.this,Intermediate.class);
                    intent.putExtra("mobile_number",number);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },5000);

        MyDbHandler db = new MyDbHandler(getApplicationContext());
        List<Data> allcontact = db.getAllContacts();

        for(Data contact: allcontact){

            Log.d("dbharry", "\nId: " + contact.getId() + "\n" +
                    "FirstName: " + contact.getFirstname() + "\n"+
                    "LastName: " + contact.getLastname() + "\n"+
                    "Phone Number: " + contact.getPhoneNumber() + "\n" +
                    "Email Id: " + contact.getEmailid() + "\n" );
        }
        Log.d("dbharry","Number of contacts in Database "+db.getCount());
////

    }
}