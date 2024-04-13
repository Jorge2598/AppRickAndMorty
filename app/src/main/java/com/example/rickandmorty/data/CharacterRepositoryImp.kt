package com.example.rickandmorty.data

import android.util.Log
import com.example.rickandmorty.data.api.network.RickAndMortyApi
import com.example.rickandmorty.data.localDataSource.local.RickAndMortyLocalDataBase
import com.example.rickandmorty.data.localDataSource.local.RickAndMortyLocalDataBaseService
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.model.toDomain
import com.example.rickandmorty.util.Result
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(private val api: RickAndMortyApi, private val dbLocal: RickAndMortyLocalDataBaseService) : CharacterRepositoryInt {
    val context = this
    override suspend fun getAllCharacters(page:String): Result<List<Character>> {
        return try {
            var localResult = dbLocal.getAllCharactersByPage(page)
            if (localResult.isEmpty()){
                var response = api.getAllCharacters(page)
                if (response.isSuccessful) {
                    val characterDTO = response.body()?.results
                    val character = characterDTO?.map { it.toDomain() }
                    dbLocal.setAllCharactersByPage(page, character!!)
                    Result.Success(character ?: emptyList())

                } else {
                    Result.Error(Exception("Error al obtener personajes")) // Manejo de error más específico
                }
            }else {
                return  Result.Success(localResult)

            }

        } catch (e: Exception) {
            Result.Error(e)
         }
    }
}