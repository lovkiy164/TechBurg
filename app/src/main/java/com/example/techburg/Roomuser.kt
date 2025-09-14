package com.example.techburg

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Roomuser(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "history")
    val history: String?,
    @ColumnInfo(name = "viewHistory")
    val viewHistory: String?,
    @ColumnInfo(name = "number")
    val number: String?
) {
}