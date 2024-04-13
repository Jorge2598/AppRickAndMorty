/*package com.example.rickandmorty.util.di

import com.example.rickandmorty.data.CharacterRepositoryImp
import com.example.rickandmorty.data.CharacterRepositoryInt
import com.example.rickandmorty.data.api.network.RickAndMortyApi
import com.example.rickandmorty.domain.useCase.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(ApplicationModule::class)
class MainActivityModule {
    @Provides
    @Singleton
    fun provideCharacterRepository(rickAndMortyApi: RickAndMortyApi): CharacterRepositoryInt {
        return CharacterRepositoryImp(rickAndMortyApi)
    }

    @Provides
    @Singleton
    fun provideGetAllCharactersUseCase(characterRepositoryInt: CharacterRepositoryInt): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(characterRepositoryInt)
    }
} */