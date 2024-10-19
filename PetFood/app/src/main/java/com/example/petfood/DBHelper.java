package com.example.petfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName = "Petfood.db";
    private static final int DBVersion = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create users table
        sqLiteDatabase.execSQL("CREATE TABLE users(" +
                "username TEXT PRIMARY KEY, " +
                "password TEXT)");

        // Create cart table
        sqLiteDatabase.execSQL("CREATE TABLE cart(" +
                "cart_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "food_name TEXT, " +
                "quantity INTEGER, " +
                "total REAL)");

        // Create order table
        sqLiteDatabase.execSQL("CREATE TABLE `order`(" +
                "order_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "food_name TEXT, " +
                "quantity INTEGER, " +
                "total REAL, " +
                "address TEXT, " +
                "payment_method TEXT)");

        // Create products table
        sqLiteDatabase.execSQL("CREATE TABLE products(" +
                "product_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "image INTEGER, " +
                "name TEXT, " +
                "price TEXT, " +
                "description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cart");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS `order`");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS products");
        onCreate(sqLiteDatabase);
    }


    public boolean insertUser(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        myDB.close();
        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        myDB.close();
        return exists;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
        boolean valid = cursor.getCount() > 0;
        cursor.close();
        myDB.close();
        return valid;
    }


    public boolean insertCartDetails(String name, String foodName, int quantity, double total) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("food_name", foodName);
        contentValues.put("quantity", quantity);
        contentValues.put("total", total);
        long result = myDB.insert("cart", null, contentValues);
        myDB.close();
        return result != -1;
    }

    public Cursor viewCart() {
        SQLiteDatabase myDB = this.getReadableDatabase();
        return myDB.rawQuery("SELECT * FROM cart", null);
    }

    public boolean deleteCartItem(int cartId) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        int result = myDB.delete("cart", "cart_id = ?", new String[]{String.valueOf(cartId)});
        myDB.close();
        return result != 0;
    }

    public Cursor getLatestCartEntry() {
        SQLiteDatabase myDB = this.getReadableDatabase();
        return myDB.rawQuery("SELECT * FROM cart ORDER BY cart_id DESC LIMIT 1", null);
    }


    public boolean insertOrderDetails(String name, String address, String foodName, int quantity, double total, String paymentMethod) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("food_name", foodName);
        contentValues.put("quantity", quantity);
        contentValues.put("total", total);
        contentValues.put("payment_method", paymentMethod);
        long result = myDB.insert("`order`", null, contentValues);
        myDB.close();
        return result != -1;
    }

    public Cursor getLatestOrderEntry() {
        SQLiteDatabase myDB = this.getReadableDatabase();
        return myDB.rawQuery("SELECT * FROM `order` ORDER BY order_id DESC LIMIT 1", null);
    }

    public Cursor viewOrders() {
        SQLiteDatabase myDB = this.getReadableDatabase();
        return myDB.rawQuery("SELECT * FROM `order`", null);
    }

    public boolean deleteOrderItem(int orderId) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        int result = myDB.delete("`order`", "order_id = ?", new String[]{String.valueOf(orderId)});
        myDB.close();
        return result != 0;
    }

    // Product-related methods
    public void addProduct(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("image", item.getImage());
        values.put("name", item.getName());
        values.put("price", item.getPrice());
        values.put("description", item.getDescription());

        long result = db.insert("products", null, values);
        db.close();

        Log.d("DBHelper", "Inserted product: " + item.getName() + " with result: " + result);
    }

    public List<Item> getAllProducts() {
        List<Item> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM products", null);

        if (cursor.moveToFirst()) {
            do {
                int imageIndex = cursor.getColumnIndex("image");
                int nameIndex = cursor.getColumnIndex("name");
                int priceIndex = cursor.getColumnIndex("price");
                int descriptionIndex = cursor.getColumnIndex("description");

                if (imageIndex != -1 && nameIndex != -1 && priceIndex != -1 && descriptionIndex != -1) {
                    int image = cursor.getInt(imageIndex);
                    String name = cursor.getString(nameIndex);
                    String price = cursor.getString(priceIndex);
                    String description = cursor.getString(descriptionIndex);

                    Item item = new Item(image, name, price, description);
                    products.add(item);
                } else {
                    Log.e("DBHelper", "Error retrieving product data from database.");
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }
}
