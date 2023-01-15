package com.vikash.kuberio10.Courses_Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vikash.kuberio10.Database.MyDbHandler;
import com.vikash.kuberio10.R;

import java.util.ArrayList;

public class Courses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Button Continue;
        TextView user_name = findViewById(R.id.user_name);
        String number = getIntent().getStringExtra("mobile_number");

        MyDbHandler db = new MyDbHandler(getApplicationContext());
        if (number!=null){
            ArrayList<String> user_info = db.User_info(number);
            String firstname= user_info.get(0);

            user_name.setText("HELLO "+firstname);
        }


        Continue=findViewById(R.id.Continue);

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Course_Video.class));

            }
        });
    }
}