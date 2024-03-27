package com.example.rickandmorty.domain.useCase
import com.example.rickandmorty.data.CharacterRepositoryInt
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.util.Result

class GetAllCharactersUseCase(private val Character: CharacterRepositoryInt){
    suspend operator fun invoke():Result<List<Character>>{
        return Character.getAllCharacters()
    }
}