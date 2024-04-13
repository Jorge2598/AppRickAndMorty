package com.example.rickandmorty.data.localDataSource.local

import com.example.rickandmorty.domain.model.Character


interface RickAndMortyLocalDataBaseService {

    suspend fun setAllCharactersByPage(page: String, characters: List<Character>)
    suspend fun getAllCharactersByPage(page: String):List<Character>
}