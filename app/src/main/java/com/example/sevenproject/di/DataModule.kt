package com.example.sevenproject.di

import android.content.Context
import androidx.room.Room
import com.example.sevenproject.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun appDataBases(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "Note-db"
    ).build()


    @Provides
    @Singleton
    fun noteDao(appDataBase: AppDataBase) = appDataBase.getDao()

}