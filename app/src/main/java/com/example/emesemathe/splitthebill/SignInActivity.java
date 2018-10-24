package com.example.emesemathe.splitthebill;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;import android.support.v7.widget.Toolbar;

public class SignInActivity extends AppCompatActivity{

    private Button buttonRegister;
    private Intent intentRegister;
    private  Toolbar toolbar_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        toolbar_ = (Toolbar) findViewById(R.id.toolbar_sign_in);
        setSupportActionBar(toolbar_);

        setTitle("Sign in ");
        toolbar_.setTitleTextColor(Color.WHITE);

        buttonRegister = (Button) findViewById(R.id.button_sign_up);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToRegisterActivity();
            }
        });

    }

    public void changeToRegisterActivity()
    {
        intentRegister = new Intent(this, RegisterActivity.class);
        startActivity(intentRegister);
    }
}
