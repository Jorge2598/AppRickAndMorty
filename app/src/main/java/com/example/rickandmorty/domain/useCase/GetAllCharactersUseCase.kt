package com.example.rickandmorty.domain.useCase
import com.example.rickandmorty.data.CharacterRepositoryInt
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.util.Result
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val Character: CharacterRepositoryInt){
    suspend operator fun invoke(page: String):Result<List<Character>>{
        return Character.getAllCharacters(page)
    }
}