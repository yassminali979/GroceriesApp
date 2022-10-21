package com.example.groceriesapp.adapter;
import static android.content.Context.MODE_PRIVATE;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groceriesapp.R;
import com.example.groceriesapp.click.onClick;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SeeAllCategoryAdapter extends RecyclerView.Adapter<SeeAllCategoryAdapter.DataHolder>{
    ArrayList<String> items;
    onClick onClickImage;
    Context context;
    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new SeeAllCategoryAdapter.DataHolder(view);
    }
    public SeeAllCategoryAdapter(onClick onClick,Context context) {
        this.onClickImage = onClick;
        this.context=context;
    }
    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cardLayout.setCardBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(),null));
        holder.title.setText(items.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImage.GetAllCategoryDetails();
                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("CategoryName", items.get(position));
                myEdit.commit();
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
    public void setItems(ArrayList<String> items)
    {
        this.items=items;
        notifyDataSetChanged();
    }
    public class DataHolder extends RecyclerView.ViewHolder{
        TextView title;
        CardView cardLayout;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            cardLayout = itemView.findViewById(R.id.cardLayoutCategory);
            title=itemView.findViewById(R.id.productTitleCategory);
        }
    }

}
