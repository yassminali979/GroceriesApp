package com.example.groceriesapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.R;
import com.example.groceriesapp.click.onClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SomeProductAdapter extends RecyclerView.Adapter<SomeProductAdapter.DataHolder>{
    ArrayList<Product> items;
    onClick onClickImage;
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.some_product,parent,false);
       return new DataHolder(view);
    }
   public SomeProductAdapter(onClick onClick) {
        this.onClickImage = onClick;
   }
    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(String.valueOf(items.get(position).getTitle()));
        holder.price.setText("$"+String.valueOf(items.get(position).getPrice()));
        Picasso.get().load(items.get(position).getImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage.GetAllProductDetails(items.get(position));
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setItems(ArrayList<Product> items)
    {
        this.items=items;
        notifyDataSetChanged();
    }
    public class DataHolder extends RecyclerView.ViewHolder{
        ImageView image,add;
        TextView name,price;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.ProductImage);
            add=itemView.findViewById(R.id.addProduct);
            name=itemView.findViewById(R.id.TitleProduct);
            price=itemView.findViewById(R.id.PriceProduct);

        }
    }

}
