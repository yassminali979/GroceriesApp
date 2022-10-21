package com.example.groceriesapp.Fav;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.groceriesapp.Fav.Fav;

import java.util.List;

@Dao
public interface FavDao {
    @Insert
    void insert(Fav fav);

    @Query("DELETE FROM Fav_Table WHERE id=:id")
    void deleteProductWithId(Long id);

    @Query("DELETE FROM Fav_Table WHERE title=:itemTitle")
    void deleteProductWithName(String itemTitle);

    @Query("SELECT * FROM Fav_Table WHERE title=:itemTitle")
    LiveData<Fav> getProduct(String itemTitle);

    @Query("SELECT * FROM fav_table")
    List<Fav> getFavProducts();

}
