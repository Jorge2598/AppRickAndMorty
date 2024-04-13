package com.example.rickandmorty.data

import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.util.Result
import dagger.Component

interface CharacterRepositoryInt {
        suspend fun getAllCharacters(page:String): Result<List<Character>>


}