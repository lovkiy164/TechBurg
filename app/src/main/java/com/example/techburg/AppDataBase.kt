package com.example.techburg

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Roomuser::class, Roomsale::class, Roomproductsale::class, Roomproduct::class, Roomproductrec::class, Roomcart::class, Roomhistory::class, Roomviewed::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun userDao(): RoomUserDao
    abstract fun saleDao(): RoomSaleDao
    abstract fun productSaleDao(): RoomProductSaleDao
    abstract fun productDao(): RoomProductDao
    abstract fun productRecDao(): RoomProductRecDao
    abstract fun cartDao(): RoomCartDao
    abstract fun historyDao(): RoomHistoryDao
    abstract fun viewedDao(): RoomViewedDao
}