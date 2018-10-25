package com.example.emesemathe.splitthebill;

import android.content.Intent;
import android.graphics.Color;
import android.os.PatternMatcher;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText user_, password_;
    private View view;
    private Button register_,signIn_;
    private Toolbar toolbar_;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar_ = (Toolbar) findViewById(R.id.toolbar_sign_up);
        setSupportActionBar(toolbar_);
        setTitle("Sign Up ");
        toolbar_.setTitleTextColor(Color.WHITE);

        user_ = (EditText) findViewById(R.id.email_sign_up);
        password_ = (EditText) findViewById(R.id.password_sign_up);

        mAuth = FirebaseAuth.getInstance();

        signIn_ = (Button) findViewById(R.id.button_sing_in_in_sign_up);
        register_ = (Button) findViewById(R.id.button_sing_up_in_sign_up);

        register_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        signIn_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBackToSignIn();
            }
        });

    }

    private void registerUser()
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

        if(pass.length() < 8)
        {
            password_.setError("The minimum length for password is 8 characters!");
            password_.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "User registered successfully",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser u = mAuth.getCurrentUser();
                            sendVerification(u);
                            getBackToSignIn();
                        }
                        else
                        {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), "You are already registered",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Some error occurred",
                                        Toast.LENGTH_SHORT).show();
                            }
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

    public void getBackToSignIn()
    {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}
