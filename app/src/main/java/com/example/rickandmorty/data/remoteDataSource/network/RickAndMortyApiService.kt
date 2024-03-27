package com.example.rickandmorty.data.remoteDataSource.network
import com.example.rickandmorty.data.dto.CharacterResponseDTO
import com.example.rickandmorty.util.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApiService {
        @GET("$END_POINT"+"2")
        suspend fun getAllCharacters(): Response<CharacterResponseDTO>

        @GET("$END_POINT/{id}")
        suspend fun getCharacterById(@Path("id") id: Int): Response<Character>

        // ... interfaces para otras entidades de la API ...
    }