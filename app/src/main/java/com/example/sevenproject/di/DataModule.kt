package com.example.sevenproject.di

import android.content.Context
import androidx.room.Room
import com.example.sevenproject.data.local.AppDataBase
import com.example.sevenproject.data.repo.NoteRepositoryImpl
import com.example.sevenproject.domain.usecase.CreateNoteUseCase
import com.example.sevenproject.domain.usecase.GetAllUseCase
import dagger.Component.Factory
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
    fun appDataBases(@ApplicationContext context: Context): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "Note-db"
    ).build()


    @Provides
    @Singleton
    fun noteDao(appDataBase: AppDataBase) = appDataBase.getDao()

    @Provides
    @Singleton
    fun getALlUSeCases(noteRepositoryImpl: NoteRepositoryImpl) = GetAllUseCase(noteRepositoryImpl)


    @Provides
    @Singleton
    fun insertUseCases(noteRepositoryImpl: NoteRepositoryImpl) = CreateNoteUseCase(noteRepositoryImpl)


}