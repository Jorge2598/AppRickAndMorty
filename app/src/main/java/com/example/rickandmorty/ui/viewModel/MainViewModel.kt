package com.example.rickandmorty.ui.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.dto.CharactersResponse
import com.example.rickandmorty.domain.model.Characters
import com.example.rickandmorty.domain.model.toDomain
import com.example.rickandmorty.domain.useCase.UseCaseCharacters
import com.example.rickandmorty.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _charactersLiveData = MutableLiveData<Result<List<Characters>>>()
    val charactersLiveData: LiveData<Result<List<Characters>>> = _charactersLiveData


    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api: UseCaseCharacters = UseCaseCharacters()
                val charactersResponse = api.getAllCharacters()

                val characters = charactersResponse.map { it.toDomain() }
                _charactersLiveData.postValue(Result.Success(characters))
            } catch (e: Exception) {
                _charactersLiveData.postValue(Result.Error(e))

            }
        }
    }
}





