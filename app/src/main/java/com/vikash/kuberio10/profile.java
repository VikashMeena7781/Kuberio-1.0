package com.vikash.kuberio10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vikash.kuberio10.Database.MyDbHandler;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView firstname,lastname,number;
        Button delete;


        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        number=findViewById(R.id.phone_number);
        delete=findViewById(R.id.delete_account);

        MyDbHandler db = new MyDbHandler(profile.this);
        ArrayList<String> user_info = db.User_info(getIntent().getStringExtra("mobile_number"));


        firstname.setText(user_info.get(0));
        lastname.setText(user_info.get(1));
        number.setText(getIntent().getStringExtra("mobile_number"));

//        Log.d("dbharry",x);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this, "Oyee! Kha jaa rha hain ?", Toast.LENGTH_SHORT).show();
            }
        });


    }
}