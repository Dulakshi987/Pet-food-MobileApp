package com.example.petfood;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    TextView txtorder;
    EditText etname, etaddress, etfoodname, etquantity, ettotal, etorder_id,etproduct_id, etpaymentmethod; // Corrected spelling of 'paymentmethod'
    Button btnbuy, btnview, btndelete;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize UI components
        txtorder = findViewById(R.id.txtorder);
        etname = findViewById(R.id.etname);
        etaddress = findViewById(R.id.etaddress);
        etfoodname = findViewById(R.id.etfoodname);
        etquantity = findViewById(R.id.etquantity);
        ettotal = findViewById(R.id.ettotal);
        etorder_id = findViewById(R.id.etorder_id);
        etproduct_id = findViewById(R.id.etproduct_id);
        etpaymentmethod = findViewById(R.id.etpayementmethod);

        btnbuy = findViewById(R.id.btnbuy);
        btnview = findViewById(R.id.btnview);
        btndelete = findViewById(R.id.btndelete);

        dbHelper = new DBHelper(this);

        btnbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrderDetails();
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewLatestOrderDetails();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOrderItem();
            }
        });
    }

    private void addOrderDetails() {
        String name = etname.getText().toString();
        String address = etaddress.getText().toString();
        String foodName = etfoodname.getText().toString();
        int quantity = Integer.parseInt(etquantity.getText().toString());
        double total = Double.parseDouble(ettotal.getText().toString());
        String paymentMethod = etpaymentmethod.getText().toString();

        boolean isInserted = dbHelper.insertOrderDetails(name, address, foodName, quantity, total, paymentMethod);

        if (isInserted) {
            Toast.makeText(this, "Order details added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add order details", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewLatestOrderDetails() {
        Cursor cursor = dbHelper.getLatestOrderEntry();

        if (cursor.getCount() == 0) {
            txtorder.setText("No orders in the system.");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (cursor.moveToNext()) {
            stringBuilder.append("Order ID: ").append(cursor.getInt(0)).append("\n");
            stringBuilder.append("Name: ").append(cursor.getString(1)).append("\n");
            stringBuilder.append("Food Name: ").append(cursor.getString(2)).append("\n");
            stringBuilder.append("Quantity: ").append(cursor.getInt(3)).append("\n");
            stringBuilder.append("Total: ").append(cursor.getDouble(4)).append("\n");
            stringBuilder.append("Address: ").append(cursor.getString(5)).append("\n");
            stringBuilder.append("Payment Method: ").append(cursor.getString(6)).append("\n\n");
        }

        txtorder.setText(stringBuilder.toString());
    }

    private void deleteOrderItem() {
        int orderId = Integer.parseInt(etorder_id.getText().toString());

        boolean isDeleted = dbHelper.deleteOrderItem(orderId);

        if (isDeleted) {
            Toast.makeText(this, "Order item deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete order item", Toast.LENGTH_SHORT).show();
        }
    }
}
