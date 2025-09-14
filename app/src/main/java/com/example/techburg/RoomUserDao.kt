package com.example.techburg

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomUserDao {
    @Query("SELECT * FROM roomuser")
    fun getAll(): List<Roomuser>

    @Query("SELECT * FROM roomuser WHERE id IN (:userIds)")
    fun loadAllByIDs(userIds: IntArray): List<Roomuser>

    @Query("SELECT * FROM roomuser WHERE login LIKE :login LIMIT 1")
    fun findByName(login: String): Roomuser

    @Query("SELECT * FROM roomuser WHERE id LIKE :id LIMIT 1")
    fun findById(id: Long): Roomuser

    @Query("SELECT * FROM roomuser WHERE login LIKE :login AND password LIKE :password LIMIT 1")
    fun checkUser(login: String,password: String): Roomuser

    @Query("UPDATE roomuser SET login = :newLogin, password = :newPassword WHERE id = :id")
    fun updateUser(newLogin: String, newPassword: String, id: Long): Int

    @Query("UPDATE roomuser SET history = :history WHERE id = :id")
    fun updateHistory(history: String, id: Long): Int

    @Query("UPDATE roomuser SET viewHistory = :viewHistory WHERE id = :id")
    fun updateViewHistory(viewHistory: String, id: Long): Int

    @Query("UPDATE roomuser SET number = :newNumber WHERE id = :id")
    fun updateNumber(newNumber: String, id: Long): Int

    @Query("UPDATE roomuser SET login = :newLogin WHERE id = :id")
    fun updateLogin(newLogin: String, id: Long): Int

    @Query("UPDATE roomuser SET password = :newPassword WHERE id = :id")
    fun updatePassword(newPassword: String, id: Long): Int

    @Insert
    fun insertAll(vararg users: Roomuser)

    @Delete
    fun delete(user: Roomuser)
}