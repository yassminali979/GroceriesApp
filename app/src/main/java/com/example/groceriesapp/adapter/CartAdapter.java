package com.example.groceriesapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceriesapp.Fav.Fav;
import com.example.groceriesapp.R;
import com.example.groceriesapp.Cart.cart;
import com.example.groceriesapp.click.RemoveClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.DataHolder> {
    List<cart> list;
    RemoveClick onItemClick;
    public CartAdapter(RemoveClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.productName.setText(list.get(position).getTitle());
        holder.productPrice.setText("$ "+ String.valueOf(list.get(position).getPrice()));
        Picasso.get().load(list.get(position).getImage()).into(holder.image);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(Long.valueOf(list.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<cart>list)
    {
        this.list=list;
        notifyDataSetChanged();
    }

    public class DataHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView productName,productPrice;
        ImageView remove;
        public DataHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.CartImage);
            productName=itemView.findViewById(R.id.CartTitle);
            productPrice=itemView.findViewById(R.id.CartPrice);
            remove=itemView.findViewById(R.id.close);
        }
    }
}
