package com.example.rickandmorty.data

import com.example.rickandmorty.data.api.network.RickAndMortyApi
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.model.toDomain
import com.example.rickandmorty.util.Result

class CharacterRepositoryImp(private val api: RickAndMortyApi) : CharacterRepositoryInt {

    override suspend fun getAllCharacters(): Result<List<Character>> {
        return try {
            var response = api.getAllCharacters()
            if (response.isSuccessful) {
                val characterDTO = response.body()?.results
                val character = characterDTO?.map { it.toDomain() }
                Result.Success(character ?: emptyList())
            } else {
                Result.Error(Exception("Error al obtener personajes")) // Manejo de error más específico
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}