package com.example.groceriesapp.adapter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceriesapp.Product;
import com.example.groceriesapp.R;
import com.example.groceriesapp.click.onClick;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.DataHolder>{
    ArrayList<Product> items;
    onClick onClickImage;
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_items,parent,false);
       return new DataHolder(view);
    }
   public ExploreAdapter(onClick onClick) {
        this.onClickImage = onClick;
   }
    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(String.valueOf(items.get(position).getTitle()));
        Picasso.get().load(items.get(position).getImage()).into(holder.image);
//        holder.card.setBackgroundTintList(ColorStateList.valueOf(holder.itemView.getResources().getColor(getRandomColor(),null)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage.GetAllProductDetails(items.get(position));
            }
        });
    }
    private int getRandomColor()
    {
        List<Integer> colorCode=new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        Random random=new Random();
        int number=random.nextInt(colorCode.size());
        return colorCode.get(number);
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
        ImageView image;
        TextView title;
        CardView card;
        public DataHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.userImage);
            title=itemView.findViewById(R.id.userTitle);
            card=itemView.findViewById(R.id.cardview);
        }
    }

}
