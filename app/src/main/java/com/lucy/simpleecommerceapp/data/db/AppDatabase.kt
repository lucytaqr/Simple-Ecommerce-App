package com.lucy.simpleecommerceapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CartItem::class, ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun productDao(): ProductDao
}