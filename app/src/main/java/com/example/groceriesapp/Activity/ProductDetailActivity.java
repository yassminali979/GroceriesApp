package com.example.groceriesapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.groceriesapp.Cart.CartDatabase;
import com.example.groceriesapp.Fav.Fav;
import com.example.groceriesapp.Fav.FavDatabase;
import com.example.groceriesapp.Product;
import com.example.groceriesapp.R;
import com.example.groceriesapp.ViewModel.ProductDetailViewModel;
import com.example.groceriesapp.Cart.cart;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {
    TextView description,nameProduct,numberOfProducts,price;
    ImageView FavAdd;
    ImageView image,increase,decrease,descriptionButtonShow,descriptionButtonDisappear,rateDetail;
    Button addBasket;
    int counter=1;
    ProductDetailViewModel productDetailViewModel;
    String Name,Image;
    Double Price;
    Boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        description=findViewById(R.id.description);
        nameProduct=findViewById(R.id.NameProduct);
        numberOfProducts=findViewById(R.id.numberOfItems);
        price=findViewById(R.id.price);
        image=findViewById(R.id.image);
        increase=findViewById(R.id.increase);
        decrease=findViewById(R.id.decrease);
        descriptionButtonShow=findViewById(R.id.descriptionButtonShow);
        descriptionButtonDisappear=findViewById(R.id.descriptionButtonDisappear);
        addBasket=findViewById(R.id.addBasket);
        rateDetail=findViewById(R.id.rateValue);
        FavAdd=findViewById(R.id.fav);
        Bundle bundle=getIntent().getExtras();
        String nameTv =bundle.getString("name");
        addBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart i=new cart(null,Name,Price,Image);
                try{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            CartDatabase.getInstance(ProductDetailActivity.this).Deo().insert(i);
                        }
                    }).start();
                    Toast.makeText(ProductDetailActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        FavDatabase.getInstance(this).Deo().getProduct(nameTv).observe(this, new Observer<Fav>() {
            @Override
            public void onChanged(Fav fav) {
                if(fav!=null&&fav.getTitle()!=null)
                {
                    FavAdd.setImageResource(R.drawable.ic_baseline_favorite);
                    flag=true;
                }else
                {
                    FavAdd.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    flag=false;
                }
            }
        });
        FavAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fav i = new Fav(null, Name, Price, Image);
                if (flag==true) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FavDatabase.getInstance(ProductDetailActivity.this).Deo().deleteProductWithName(nameTv);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ProductDetailActivity.this, "item removed from fav list", Toast.LENGTH_SHORT).show();
                                    FavAdd.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                                    flag=false;
                                }
                            });
                        }

                    }).start();
                }
                else
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                                FavDatabase.getInstance(ProductDetailActivity.this).Deo().insert(i);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ProductDetailActivity.this, "item added to fav list", Toast.LENGTH_SHORT).show();
                                        FavAdd.setImageResource(R.drawable.ic_baseline_favorite);
                                        flag=true;
                                    }
                                });
                        }
                    }).start();
                }
            }
        });
        productDetailViewModel =new ViewModelProvider(this).get(ProductDetailViewModel.class);
        if(bundle!=null) {
            int id = bundle.getInt("id");
            productDetailViewModel.getAllPostsDetailsFromRepo(id);
            productDetailViewModel.posts().observe(this, new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
                    if(!product.getDescription().isEmpty()&&!product.getTitle().isEmpty()&&!product.getPrice().equals("")&&!product.getImage().isEmpty()) {
                        description.setText(product.getDescription());
                        nameProduct.setText(product.getTitle());
                        Name=product.getTitle();
                        price.setText("$" + product.getPrice());
                        Price=product.getPrice();
                        Picasso.get().load(product.getImage()).into(image);
                        Image=product.getImage();
                        rateDetail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                  showSuccessDialog(product.getRating().getRate(),product.getRating().getCount());
                            }
                        });
                    }
                }
            });
        }
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                numberOfProducts.setText(String.valueOf(counter));
            }
        });
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                numberOfProducts.setText(String.valueOf(counter));
                if(counter<0)
                {
                    Toast.makeText(getApplicationContext(), "No,Less Than Zero", Toast.LENGTH_SHORT).show();
                }
            }
        });
        descriptionButtonDisappear.setVisibility(View.VISIBLE);
        descriptionButtonShow.setVisibility(View.GONE);
        descriptionButtonDisappear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setVisibility(View.GONE);
                descriptionButtonShow.setVisibility(View.VISIBLE);
                descriptionButtonDisappear.setVisibility(View.GONE);
            }
        });
        descriptionButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setVisibility(View.VISIBLE);
                descriptionButtonDisappear.setVisibility(View.VISIBLE);
                descriptionButtonShow.setVisibility(View.GONE);
            }
        });
    }
    private void showSuccessDialog(Double rate,int count){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(ProductDetailActivity.this, androidx.appcompat.R.style.AlertDialog_AppCompat);
        View view = LayoutInflater.from(ProductDetailActivity.this).inflate(
                R.layout.activity_success_dialoge,
                (ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);
        ((TextView) view.findViewById(R.id.dataRating))
                .setText("Rate:"+rate.toString()+"\n"+"Count:"+count);
        ((Button) view.findViewById(R.id.buttonAction))
                .setText("Back");
        final AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.buttonAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}