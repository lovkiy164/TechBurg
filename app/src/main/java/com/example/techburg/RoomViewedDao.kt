package com.example.techburg

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomViewedDao {
    @Query("SELECT * FROM roomviewed")
    fun getAll(): List<Roomviewed>

    @Query("SELECT * FROM roomviewed WHERE userId LIKE :userId")
    fun getByUserId(userId: Int): List<Roomviewed>

    @Insert
    fun insertAll(vararg history: Roomviewed)

    @Delete
    fun delete(history: Roomviewed)
}