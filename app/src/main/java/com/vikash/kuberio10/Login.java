package com.vikash.kuberio10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    FirebaseAuth auth;
    EditText Input_email,Input_password;
    Button login;
    TextView create_account,forget_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Input_email=findViewById(R.id.email);
        Input_password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        create_account=findViewById(R.id.create_account);
        forget_password=findViewById(R.id.forget_password);

        auth=FirebaseAuth.getInstance();

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Input_email.getText().toString();
                if(email.isEmpty()){
                    Input_email.setError("Enter your registered email to reset password");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Input_email.setError("Please enter valid email");
                }else{
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "Please check your email", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Performlogin();
            }
        });



    }

    private void Performlogin() {
        String email = Input_email.getText().toString();
        String password = Input_password.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Input_email.setError("Enter correct email");
        }else if(password.isEmpty() || password.length()<6) {
            Input_password.setError("Enter Proper Password");
        }else{

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Intermediate.class);
                        startActivity(intent);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                    }else{
                        try {
                            throw task.getException();
                        }catch (FirebaseAuthInvalidUserException e){
                            Toast.makeText(getApplicationContext(), "Please register first.", Toast.LENGTH_SHORT).show();
                        }catch (FirebaseAuthInvalidCredentialsException e){
                            Toast.makeText(Login.this, "Password entered is wrong!!", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });

        }

    }


}