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
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    EditText Input_username,Input_number,Input_email,Input_password,Input_confirmpassword;
    Button register;
    TextView already_account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Input_username=findViewById(R.id.user_name);
        Input_number=findViewById(R.id.number);
        Input_email=findViewById(R.id.email);
        Input_password=findViewById(R.id.password);
        Input_confirmpassword=findViewById(R.id.confirm_password);
        register=findViewById(R.id.register);
        already_account=findViewById(R.id.already_account);

        auth=FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perform_SignUp();
            }
        });

    }

    private void Perform_SignUp() {
        String username = Input_username.getText().toString();
        String number = Input_number.getText().toString();
        String password = Input_password.getText().toString();
        String confirm_password = Input_confirmpassword.getText().toString();
        String email = Input_email.getText().toString();

        if(username.isEmpty()){
            Input_username.setError("Fill Username");
        }else if(!Patterns.PHONE.matcher(number).matches()){
            Input_number.setError("Enter valid phone number");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Input_email.setError("Enter valid email");
        }else if(password.isEmpty() || password.length()<6){
            Input_password.setError("Password length must be at least 6");
        }else if(!confirm_password.equals(password)){
            Input_confirmpassword.setError("Password not matched");
        }else{

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users_Data");

                        User_Info user_info = new User_Info();
                        user_info.setEmail(email);
                        user_info.setUsername(username);
                        user_info.setNumber(number);
                        user_info.setStatus("offline");
                        user_info.setSearch(username.toLowerCase());

                        user=auth.getCurrentUser();
                        if(user!=null){
                            String id = user.getUid();
                            user_info.setId(id);
                            databaseReference.child(id).setValue(user_info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),Intermediate.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(Signup.this, "Registration Failed...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Signup.this, "Registration Failed...", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        try {
                            throw task.getException();
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            Input_email.setError("Your email is invalid or already in use. Kindly re-enter.");
                            Input_email.requestFocus();
                        }catch (FirebaseAuthUserCollisionException e){
                            Input_email.setError("User is already registered with this email. Use another email");
                            Input_email.requestFocus();
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });

        }

    }
}