package com.vikash.kuberio10;

import static com.google.firebase.auth.PhoneAuthProvider.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.vikash.kuberio10.Database.Data;
import com.vikash.kuberio10.Database.MyDbHandler;

import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {

    EditText number,firstname,lastname;
    Button get_otp;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        number=findViewById(R.id.number);
        get_otp=findViewById(R.id.getotp);
        progressBar=findViewById(R.id.probar1);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);

        requestPermissions();

        get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!number.getText().toString().trim().isEmpty()){
                    if ((number.getText().toString().trim()).length()==10)
                    {
                        if(!(firstname.getText().toString().length()==0) && !(lastname.getText().toString().length()==0)){

                            progressBar.setVisibility(View.VISIBLE);
                            get_otp.setVisibility(View.INVISIBLE);

                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    "+91" + number.getText().toString(),
                                    60,
                                    TimeUnit.SECONDS,
                                    Signup.this,
                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                            progressBar.setVisibility(View.GONE);
                                            get_otp.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {

                                            progressBar.setVisibility(View.GONE);
                                            get_otp.setVisibility(View.VISIBLE);
                                            Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                            progressBar.setVisibility(View.GONE);
                                            get_otp.setVisibility(View.VISIBLE);

                                            SharedPreferences pref = getSharedPreferences("user_data",MODE_PRIVATE);
                                            SharedPreferences.Editor editor = pref.edit();
                                            editor.putBoolean("flag",true);
                                            editor.putString("number",number.getText().toString());
                                            editor.apply();



                                            /** Adding customers to data */
                                            /** To set user_name... fetch it's info from database... sending data will not work */


                                            Intent intent=new Intent(Signup.this,Otp_Verification.class);

                                            intent.putExtra("mobile_number",number.getText().toString());
                                            intent.putExtra("backendotp",backendotp);
//                                            intent.putExtra("firstName",firstname.getText().toString());
//                                            intent.putExtra("lastName",lastname.getText().toString());
                                            startActivity(intent);

                                            MyDbHandler db = new MyDbHandler(Signup.this);
                                            Data data = new Data();
                                            data.setFirstname(firstname.getText().toString());
                                            data.setLastname(lastname.getText().toString());
                                            data.setPhoneNumber(number.getText().toString());
                                            db.addContact(data);


                                            /** Sending Data to profile */
                                            // Can't send like this.. one time two call for startActvity();
//                                            Intent intent_profile = new Intent(getApplicationContext(),profile.class);
//                                            intent.putExtra("firstName",firstname.getText().toString());
//                                            intent.putExtra("lastName",lastname.getText().toString());
//                                            intent_profile.putExtra("mobile_number",number.getText().toString());
//                                            startActivity(intent_profile);

                                            /** Sending data to dashboard */
//                                            Intent intent_dashboard = new Intent(getApplicationContext(),Dashboard.class);
//                                            intent_dashboard.putExtra("firstName",firstname.getText().toString());
//                                            intent_dashboard.putExtra("lastName",lastname.getText().toString());
//                                            startActivity(intent_dashboard);



                                        }

                                    }

                            );

                        }else{
                            Toast.makeText(Signup.this, "Please fill all required field", Toast.LENGTH_SHORT).show();

                        }



                    }else {
                        Toast.makeText(Signup.this, "Please enter correct Number", Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    Toast.makeText(Signup.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }
    private void requestPermissions(){
        if(ContextCompat.checkSelfPermission(Signup.this, Manifest.permission.RECEIVE_SMS)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Signup.this,new String[]{
                    Manifest.permission.RECEIVE_SMS
            },100);

        }
    }
}