package com.example.sevenproject.di

import com.example.sevenproject.data.repo.NoteRepositoryImpl
import com.example.sevenproject.domain.repo.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun noteRepository(noteRepoImpl: NoteRepositoryImpl): NoteRepository


}