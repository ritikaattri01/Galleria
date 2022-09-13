package com.app.imagespagination.di

import android.app.Application
import androidx.room.Room
import com.app.imagespagination.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDataBase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME).build()
    }
}