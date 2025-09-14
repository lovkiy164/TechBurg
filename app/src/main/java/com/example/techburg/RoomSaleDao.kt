package com.example.techburg

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomSaleDao {
    @Query("SELECT * FROM roomsale")
    fun getAll(): List<Roomsale>

    @Insert
    fun insertAll(vararg sales: Roomsale)

    @Delete
    fun delete(user: Roomsale)
}