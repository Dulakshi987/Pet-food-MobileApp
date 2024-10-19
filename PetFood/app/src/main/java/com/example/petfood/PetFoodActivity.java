package com.example.petfood;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class PetFoodActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petfood);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(R.drawable.food1,"Pedigree chunky chiken","1000.00","It is Pedigree brand & type is dry food."));
        items.add(new Item(R.drawable.food2,"Purine dog chown","3000.00","It is Pedigree brand & type is dry food."));
        items.add(new Item(R.drawable.food3,"Probiotics","1250.00","It is Pedigree brand & type is dry food."));
        items.add(new Item(R.drawable.food4,"Dog chow","2100.00","It is Pedigree brand & type is dry food."));
        items.add(new Item(R.drawable.food5,"Proactive dry food","800.00","It is Pedigree brand & type is dry food."));
        items.add(new Item(R.drawable.food6,"Wellness food","750.00","It is Pedigree brand & type is dry food."));
        items.add(new Item(R.drawable.food7,"Raw dehydrated","1000.00","It is Pedigree brand & type is dry food."));
        items.add(new Item(R.drawable.food8,"Applaws taste toppers","3000.00","It is Pedigree brand & type is dry food."));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapterActivity(getApplicationContext(),items));
    }

}
