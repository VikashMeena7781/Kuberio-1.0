package com.vikash.kuberio10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vikash.kuberio10.Courses_Dashboard.Courses;
import com.vikash.kuberio10.Database.MyDbHandler;

import java.util.ArrayList;

public class Intermediate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        Button course,explore_more;
        course = findViewById(R.id.course);
        explore_more=findViewById(R.id.explore_more);

        explore_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=getIntent().getStringExtra("mobile_number");
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                intent.putExtra("mobile_number",number);
                startActivity(intent);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=getIntent().getStringExtra("mobile_number");
                Intent intent = new Intent(getApplicationContext(), Courses.class);
                intent.putExtra("mobile_number",number);
                startActivity(intent);
            }
        });


    }
}