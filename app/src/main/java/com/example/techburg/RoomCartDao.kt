package com.example.techburg

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomCartDao {
    @Query("SELECT * FROM roomcart")
    fun getAll(): List<Roomcart>

    @Query("SELECT * FROM roomcart WHERE name LIKE :name")
    fun getByName(name: String): List<Roomcart>

    @Query("SELECT * FROM roomcart WHERE userId LIKE :userId")
    fun getByUserId(userId: Int): List<Roomcart>

    @Insert
    fun insertAll(vararg sales: Roomcart)

    @Delete
    fun delete(user: Roomcart)
}