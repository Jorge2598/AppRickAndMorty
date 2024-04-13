package com.example.rickandmorty.util.di

import android.app.Application
import android.content.Context
import com.example.rickandmorty.data.CharacterRepositoryImp
import com.example.rickandmorty.data.CharacterRepositoryInt
import com.example.rickandmorty.data.api.network.RickAndMortyApi
import com.example.rickandmorty.data.localDataSource.local.RickAndMortyLocalDataBase
import com.example.rickandmorty.data.localDataSource.local.RickAndMortyLocalDataBaseService
import com.example.rickandmorty.domain.useCase.GetAllCharactersUseCase
import com.example.rickandmorty.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule() {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        urlBase: String,
        gson: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(gson)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyApi(
        urlBase: String,
        gson: GsonConverterFactory,
        retrofit: Retrofit
    ): RickAndMortyApi {
        return RickAndMortyApi(urlBase, gson, retrofit)

    }

    @Provides
    @Singleton
    fun provideCharacterRepository(rickAndMortyApi: RickAndMortyApi, rickAndMortyLocalDataBase: RickAndMortyLocalDataBaseService): CharacterRepositoryInt {
        return CharacterRepositoryImp(rickAndMortyApi, rickAndMortyLocalDataBase)
    }

    @Provides
    @Singleton
    fun provideGetAllCharactersUseCase(characterRepositoryInt: CharacterRepositoryInt): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(characterRepositoryInt)
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(context:Application): RickAndMortyLocalDataBaseService {
        return RickAndMortyLocalDataBase(context)
    }

}