package com.example.petfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button ButtonCallRegister,ButtonCallLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonCallRegister=(Button) findViewById(R.id.btnCallRegister);
        ButtonCallLogin=(Button) findViewById(R.id.btnCallLogin);

        ButtonCallRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(intentRegister);
            }
        });

        ButtonCallLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(getApplicationContext(),
                       LoginActivity.class);
                startActivity(intentLogin);
            }
        });
    }
}