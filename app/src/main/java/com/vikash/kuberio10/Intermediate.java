package com.vikash.kuberio10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vikash.kuberio10.Courses_Dashboard.Courses;

public class Intermediate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button course,explore_more;
        course = findViewById(R.id.course);
        explore_more=findViewById(R.id.explore_more);

        explore_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                startActivity(intent);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Kuberio");
                databaseReference.setValue("Kuberio, All Rigths Reserved");
                Intent intent = new Intent(getApplicationContext(), Courses.class);
                startActivity(intent);
            }
        });


    }
}