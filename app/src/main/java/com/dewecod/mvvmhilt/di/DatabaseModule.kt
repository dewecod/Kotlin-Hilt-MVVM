package com.dewecod.mvvmhilt.di

import android.app.Application
import androidx.room.Room
import com.dewecod.mvvmhilt.db.AppDatabase
import com.dewecod.mvvmhilt.db.PokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "FavoriteDatabase")
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePokeDao(appDatabase: AppDatabase): PokeDao {
        return appDatabase.pokeDao()
    }

}