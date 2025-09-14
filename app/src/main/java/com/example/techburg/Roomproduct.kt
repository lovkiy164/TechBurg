package com.example.techburg

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Roomproduct(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "sale")
    val sale: Int,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "color")
    val color: String?,
    @ColumnInfo(name = "capacity")
    val capacity: String?
) {
}