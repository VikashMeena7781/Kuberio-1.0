package com.vikash.kuberio10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vikash.kuberio10.SQLite_Database.MyDbHandler;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView username,email,number;
        Button delete;

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        number=findViewById(R.id.phone_number);
        delete=findViewById(R.id.delete_account);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if(user!=null){
            String id = user.getUid();
            DatabaseReference data = FirebaseDatabase.getInstance().getReference("Users_Data");
            data.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    User_Info user_info = snapshot.getValue(User_Info.class);
                    username.setText(user_info.getUsername());
                    email.setText(user_info.getEmail());
                    number.setText(user_info.getNumber());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(profile.this, "We are getting some Error...Please wait!!", Toast.LENGTH_SHORT).show();

                }
            });
        }else{
            Toast.makeText(this, "Getting some error... Please wait", Toast.LENGTH_SHORT).show();
        }






        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this, "Oyee! Kha jaa rha hain ?", Toast.LENGTH_SHORT).show();
            }
        });


    }
}