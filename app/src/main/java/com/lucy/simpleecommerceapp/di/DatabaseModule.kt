package com.lucy.simpleecommerceapp.di

import android.content.Context
import androidx.room.Room
import com.lucy.simpleecommerceapp.data.db.AppDatabase
import com.lucy.simpleecommerceapp.data.db.CartDao
import com.lucy.simpleecommerceapp.data.db.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCartDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "simple_ecommerce.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao {
        return db.productDao()
    }

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao {
        return db.cartDao()
    }
}