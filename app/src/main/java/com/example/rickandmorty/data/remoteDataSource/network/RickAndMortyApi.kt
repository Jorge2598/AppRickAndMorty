package com.example.rickandmorty.data.api.network
import com.example.rickandmorty.data.dto.CharacterResponseDTO
import com.example.rickandmorty.data.remoteDataSource.network.RickAndMortyApiService
import com.example.rickandmorty.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RickAndMortyApi(
    private val baseUrl: String = BASE_URL,
    private val okHttpClient: OkHttpClient = OkHttpClient(),
    private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create()
) {

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    private val apiService = retrofit.create(RickAndMortyApiService::class.java)
    suspend fun getAllCharacters(): Response<CharacterResponseDTO> {
        return apiService.getAllCharacters()
    }

    suspend fun getCharacterById(id: Int): Response<Character> {
        return apiService.getCharacterById(id)
    }
}