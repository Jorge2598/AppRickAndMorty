package com.example.rickandmorty.domain.useCase
import com.example.rickandmorty.data.api.network.RickAndMortyApi
import com.example.rickandmorty.data.dto.CharactersResponse

class UseCaseCharacters {
    suspend fun getAllCharacters(): List<CharactersResponse.Result> {
        val apiService = RickAndMortyApi()
        val response = apiService.getAllCharacters()

        if (response.isSuccessful) {
                return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Error al obtener los personajes")
        }
    }
}
