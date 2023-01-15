package com.vikash.kuberio10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.vikash.kuberio10.Database.MyDbHandler;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    Button signup , Continue;
    EditText mobilenumber;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobilenumber=findViewById(R.id.number_login);
        signup=findViewById(R.id.signup_1);
        Continue=findViewById(R.id.Continue);
        progressBar=findViewById(R.id.probar2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
                finish();
            }
        });
        requestPermissions();
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mobilenumber.getText().toString().trim().isEmpty()){
                    if ((mobilenumber.getText().toString().trim()).length()==10)
                    {
                        /** Checking customer existance */
                        MyDbHandler db = new MyDbHandler(getApplicationContext());
                        String number = mobilenumber.getText().toString();

                        if(number!=null){
                            if(db.check_number(number)){
                                progressBar.setVisibility(View.VISIBLE);
                                Continue.setVisibility(View.INVISIBLE);

                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        "+91" + mobilenumber.getText().toString(),
                                        60,
                                        TimeUnit.SECONDS,
                                        Login.this,
                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                                progressBar.setVisibility(View.GONE);
                                                Continue.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                                progressBar.setVisibility(View.GONE);
                                                Continue.setVisibility(View.VISIBLE);
                                                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                                progressBar.setVisibility(View.GONE);
                                                Continue.setVisibility(View.VISIBLE);

                                                SharedPreferences pref = getSharedPreferences("user_data",MODE_PRIVATE);
                                                SharedPreferences.Editor editor = pref.edit();
                                                editor.putBoolean("flag",true);
                                                editor.putString("number",mobilenumber.getText().toString());
                                                editor.apply();

                                                Intent intent=new Intent(Login.this,Otp_Verification.class);
                                                intent.putExtra("mobile_number",mobilenumber.getText().toString());
                                                intent.putExtra("backendotp",backendotp);
                                                startActivity(intent);

                                            }
                                        }

                                );


                            }else{
                                Toast.makeText(Login.this, "Please Sign Up first!", Toast.LENGTH_SHORT).show();
                            }

                        }


                    }else {
                        Toast.makeText(Login.this, "Please enter correct Number", Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    Toast.makeText(Login.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }

                        }


        });



    }
    private void requestPermissions(){
        if(ContextCompat.checkSelfPermission(Login.this, Manifest.permission.RECEIVE_SMS)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Login.this,new String[]{
                    Manifest.permission.RECEIVE_SMS
            },100);

        }
    }
}