package com.example.rickandmorty.data.api.network

import com.example.rickandmorty.data.dto.CharacterResponseDTO
import com.example.rickandmorty.data.remoteDataSource.network.RickAndMortyApiService
import com.example.rickandmorty.util.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class RickAndMortyApi @Inject constructor(
    private val baseUrl: String,
    private val gsonConverterFactory: GsonConverterFactory,
    private val retrofit: Retrofit
) {

    private val apiService = retrofit.create(RickAndMortyApiService::class.java)
    suspend fun getAllCharacters(page: String): Response<CharacterResponseDTO> {
        return apiService.getAllCharacters(page)
    }

    suspend fun getCharacterById(id: Int): Response<Character> {
        return apiService.getCharacterById(id)
    }
}