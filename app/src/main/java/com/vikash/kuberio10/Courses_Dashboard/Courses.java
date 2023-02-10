package com.vikash.kuberio10.Courses_Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vikash.kuberio10.SQLite_Database.MyDbHandler;
import com.vikash.kuberio10.R;
import com.vikash.kuberio10.User_Info;
import com.vikash.kuberio10.profile;

import java.util.ArrayList;

public class Courses extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button Continue;
        TextView username = findViewById(R.id.user_name);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if(user!=null){
            String id = user.getUid();
            DatabaseReference data = FirebaseDatabase.getInstance().getReference("Users_Data");
            data.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    User_Info user_info = snapshot.getValue(User_Info.class);
                    username.setText("Hello "+user_info.getUsername());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "We are getting some Error...Please wait!!", Toast.LENGTH_SHORT).show();

                }
            });
        }else{
            Toast.makeText(this, "Getting some error... Please wait", Toast.LENGTH_SHORT).show();
        }

//        String number = getIntent().getStringExtra("mobile_number");

//        MyDbHandler db = new MyDbHandler(getApplicationContext());
//        if (number!=null){
//            ArrayList<String> user_info = db.User_info(number);
//            String firstname= user_info.get(0);
//
//            user_name.setText("HELLO "+firstname);
//        }


        Continue=findViewById(R.id.Continue);

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Course_Video.class));

            }
        });
    }
}