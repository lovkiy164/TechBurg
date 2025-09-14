package com.example.techburg

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomProductSaleDao {
    @Query("SELECT * FROM roomproductsale")
    fun getAll(): List<Roomproductsale>

    @Query("SELECT * FROM roomproductsale WHERE category LIKE :category")
    fun getByCategory(category: String): List<Roomproductsale>

    @Insert
    fun insertAll(vararg sales: Roomproductsale)

    @Delete
    fun delete(user: Roomproductsale)
}