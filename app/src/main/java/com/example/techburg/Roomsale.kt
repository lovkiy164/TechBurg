package com.example.techburg

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Roomsale(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "sale")
    val sale: Int?,
    @ColumnInfo(name = "image")
    val image: Int
) : java.io.Serializable {
}