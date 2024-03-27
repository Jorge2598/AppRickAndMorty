package com.example.rickandmorty.ui.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.rickandmorty.data.CharacterRepositoryImp
import com.example.rickandmorty.data.CharacterRepositoryInt
import com.example.rickandmorty.data.api.network.RickAndMortyApi
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.useCase.GetAllCharactersUseCase
import com.example.rickandmorty.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val getCharactersUseCase: GetAllCharactersUseCase ) : ViewModel() {

    private val _charactersLiveData = MutableLiveData<Result<List<Character>>>()
    val charactersLiveData: LiveData<Result<List<Character>>> = _charactersLiveData

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characters = getCharactersUseCase()
                _charactersLiveData.postValue(characters)
            } catch (e: Exception) {
                _charactersLiveData.postValue(Result.Error(e))
            }
        }
    }


}





