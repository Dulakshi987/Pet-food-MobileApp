package com.example.petfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Button ButtonPetFood, btnCart,btnOrder,ButtonContact,ButtonEduTips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButtonPetFood=(Button) findViewById(R.id.btnPetFood);
        btnCart=(Button) findViewById(R.id.btnCart);
        btnOrder=(Button) findViewById(R.id.btnOrder);
        ButtonContact=(Button) findViewById(R.id.btnContact);
        ButtonEduTips=(Button) findViewById(R.id.btnEduTips);

        ButtonPetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),
                        PetFoodActivity.class);
                startActivity(intent);
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
@Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),
                        CartActivity.class);
                startActivity(intent);
            }
            });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),
                        OrderActivity.class);
                startActivity(intent);
            }
        });


        ButtonEduTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),
                        NutritonsActivity.class);
                startActivity(intent);
            }
        });
        ButtonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),
                        ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}

