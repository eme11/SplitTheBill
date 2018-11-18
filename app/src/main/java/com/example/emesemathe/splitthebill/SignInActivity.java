package com.example.emesemathe.splitthebill;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class SignInActivity extends AppCompatActivity{

    private Button buttonRegister_;
    private Button buttonSingIn_;
    private Button forgotPassword_;
    private  Toolbar toolbar_;
    private FirebaseAuth mAuth;

    private EditText user_, password_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        toolbar_ = (Toolbar) findViewById(R.id.toolbar_sign_in);
        setSupportActionBar(toolbar_);
        setTitle("Sign in ");
        toolbar_.setTitleTextColor(Color.WHITE);

        mAuth = FirebaseAuth.getInstance();

        user_ = (EditText) findViewById(R.id.email_sign_in);
        password_ = (EditText) findViewById(R.id.password_sign_in);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null && user.isEmailVerified())
            changeToMenuAcitvity();

        buttonRegister_ = (Button) findViewById(R.id.button_sign_up);
        buttonRegister_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToRegisterActivity();
            }
        });

        buttonSingIn_ = (Button) findViewById(R.id.button_sign_in);
        buttonSingIn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogIn();
            }
        });

        forgotPassword_ = (Button) findViewById(R.id.button_forgot_password);
        forgotPassword_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

    }

    private void userLogIn()
    {
        String user = user_.getText().toString().trim();
        String pass = password_.getText().toString().trim();

        if(user.isEmpty()){
            user_.setError("Email is required!");
            user_.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(user).matches())
        {
            user_.setError("Invalid email!");
            user_.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            password_.setError("Password is required!");
            password_.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user.isEmailVerified())
                        changeToMenuAcitvity();
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Email Not verified",
                                Toast.LENGTH_SHORT).show();
                        sendVerification(user);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void sendVerification(FirebaseUser user)
    {
        Task<Void> verification_email_sent_to_ = user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task_) {
                if (task_.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Verification email sent ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Failed to send verification email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changeToRegisterActivity()
    {
        Intent intentRegister = new Intent(this, RegisterActivity.class);
        startActivity(intentRegister);
    }

    private void changeToMenuAcitvity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void resetPassword() {
        String emailAddress = user_.getText().toString().trim();

        if (!emailAddress.isEmpty()) {

            mAuth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Password reset email sent ",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "An error occurred, try again ",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else{

            Toast.makeText(getApplicationContext(), "Enter your email first",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
