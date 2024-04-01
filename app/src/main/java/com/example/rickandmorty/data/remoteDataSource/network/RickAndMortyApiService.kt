package com.example.rickandmorty.data.remoteDataSource.network
import com.example.rickandmorty.data.dto.CharacterResponseDTO
import com.example.rickandmorty.util.Constants.END_POINT
import dagger.Component
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickAndMortyApiService {
        @GET("character/?")
        suspend fun getAllCharacters(@Query("page") page: String): Response<CharacterResponseDTO>

        @GET("$END_POINT/{id}")
        suspend fun getCharacterById(@Path("id") id: Int): Response<Character>

        // ... interfaces para otras entidades de la API ...
    }