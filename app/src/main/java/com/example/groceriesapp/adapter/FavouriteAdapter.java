package com.example.groceriesapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceriesapp.Fav.Fav;
import com.example.groceriesapp.R;
import com.example.groceriesapp.click.RemoveClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.DataHolder> {
    List<Fav> list;
    RemoveClick onItemClick;
    Context context;

    public FavouriteAdapter(RemoveClick onItemClick,Context context) {
        this.onItemClick = onItemClick;
        this.context=context;
    }
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(list.get(position).getImage()).into(holder.image);
        holder.productName.setText(list.get(position).getTitle());
        holder.productPrice.setText("$ "+ String.valueOf(list.get(position).getPrice()));
//        SharedPreferences sharedPreferences = this.context.getSharedPreferences("SharedPref",MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//        myEdit.putString("FavName", list.get(position).getTitle());
//        myEdit.putString("FavPrice", String.valueOf(list.get(position).getPrice()));
//        myEdit.putString("FavImage", list.get(position).getImage());
//        myEdit.commit();
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
    public void setList(List<Fav>list)
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
            image=itemView.findViewById(R.id.favImage);
            productName=itemView.findViewById(R.id.favName);
            productPrice=itemView.findViewById(R.id.favPrice);
            remove=itemView.findViewById(R.id.favRemove);
        }
    }

}
