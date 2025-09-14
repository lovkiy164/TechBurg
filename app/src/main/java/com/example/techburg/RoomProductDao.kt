package com.example.techburg

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomProductDao {
    @Query("SELECT * FROM roomproduct")
    fun getAll(): List<Roomproduct>

    @Query("SELECT * FROM roomproduct WHERE category LIKE :category")
    fun getByCategory(category: String): List<Roomproduct>

    @Query("SELECT * FROM roomproduct WHERE name LIKE :name LIMIT 1")
    fun getByName(name: String): Roomproduct

    @Insert
    fun insertAll(vararg sales: Roomproduct)

    @Delete
    fun delete(user: Roomproduct)
}