package com.example.groceriesapp.Cart;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    void insert(cart cart);
    @Query("DELETE FROM cart_Table WHERE id=:id")
    void deleteProduct(Long id);

    @Query("SELECT * FROM cart_Table")
    List<cart> getAllProducts();

}
