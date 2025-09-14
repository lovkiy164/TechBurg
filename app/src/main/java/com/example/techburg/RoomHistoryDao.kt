package com.example.techburg

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomHistoryDao {
    @Query("SELECT * FROM roomhistory")
    fun getAll(): List<Roomhistory>

    @Query("SELECT * FROM roomhistory WHERE userId LIKE :userId")
    fun getByUserId(userId: Int): List<Roomhistory>

    @Query("SELECT * FROM roomhistory WHERE request LIKE :request")
    fun getByQuery(request: String): Roomhistory

    @Insert
    fun insertAll(vararg history: Roomhistory)

    @Delete
    fun delete(history: Roomhistory)
}