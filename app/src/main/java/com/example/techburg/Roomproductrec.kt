package com.example.techburg

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Roomproductrec(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "sale")
    val sale: Int,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "category")
    val category: String
) {
}