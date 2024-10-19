package com.example.petfood;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    TextView txtcart;
    EditText etname, etfoodname, etquantity, ettotal, etcart_id, etproduct_id;
    Button btncart, btnview, btndelete;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        txtcart = findViewById(R.id.txtcart);
        etname = findViewById(R.id.etname);
        etfoodname = findViewById(R.id.etfoodname);
        etquantity = findViewById(R.id.etquantity);
        ettotal = findViewById(R.id.ettotal);
        etcart_id = findViewById(R.id.etcart_id);
        etproduct_id = findViewById(R.id.etproduct_id);// For delete operation

        btncart = findViewById(R.id.btncart);
        btnview = findViewById(R.id.btnview);
        btndelete = findViewById(R.id.btndelete);


        dbHelper = new DBHelper(this);

        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCartDetails();
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLatestCartDetails();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCartItem();
            }
        });


    }

    private void addCartDetails() {
        String name = etname.getText().toString();
        String foodName = etfoodname.getText().toString();
        int quantity = Integer.parseInt(etquantity.getText().toString());
        double total = Double.parseDouble(ettotal.getText().toString());

        boolean isInserted = dbHelper.insertCartDetails(name, foodName, quantity, total);

        if (isInserted) {
            Toast.makeText(this, "Cart details added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add cart details", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewLatestCartDetails() {
        Cursor cursor = dbHelper.getLatestCartEntry();

        if (cursor.getCount() == 0) {
            txtcart.setText("No items in the cart.");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (cursor.moveToNext()) {
            stringBuilder.append("Cart ID: ").append(cursor.getInt(0)).append("\n");
            stringBuilder.append("Name: ").append(cursor.getString(1)).append("\n");
            stringBuilder.append("Food Name: ").append(cursor.getString(2)).append("\n");
            stringBuilder.append("Quantity: ").append(cursor.getInt(3)).append("\n");
            stringBuilder.append("Total: ").append(cursor.getDouble(4)).append("\n\n");
        }

        txtcart.setText(stringBuilder.toString());
    }

    private void deleteCartItem() {
        int cartId = Integer.parseInt(etcart_id.getText().toString());

        boolean isDeleted = dbHelper.deleteCartItem(cartId);

        if (isDeleted) {
            Toast.makeText(this, "Cart item deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete cart item", Toast.LENGTH_SHORT).show();
        }
    }
}
