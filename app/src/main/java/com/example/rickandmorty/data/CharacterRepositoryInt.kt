package com.example.rickandmorty.data

import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.util.Result

interface CharacterRepositoryInt {
        suspend fun getAllCharacters(): Result<List<Character>>

}