package com.example.petfood;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView etname,etprice,etdescription;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView =itemView.findViewById(R.id.imageView);
        etname = itemView.findViewById(R.id.etname);
        etprice = itemView.findViewById(R.id.etprice);
        etdescription= itemView.findViewById(R.id.etdescription);
    }
}
