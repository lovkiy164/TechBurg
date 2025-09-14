package com.example.techburg

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomProductRecDao {
    @Query("SELECT * FROM roomproductrec")
    fun getAll(): List<Roomproductrec>

    @Query("SELECT * FROM roomproductrec WHERE name LIKE :name")
    fun getByName(name: String): List<Roomproductrec>

    @Insert
    fun insertAll(vararg sales: Roomproductrec)

    @Delete
    fun delete(user: Roomproductrec)
}