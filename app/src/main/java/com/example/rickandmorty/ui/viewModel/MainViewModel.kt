package com.example.rickandmorty.ui.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.domain.useCase.GetAllCharactersUseCase
import com.example.rickandmorty.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val getCharactersUseCase: GetAllCharactersUseCase) : ViewModel() {

    private var nextPage = 1
    private val _charactersLiveData = MutableLiveData<Result<List<Character>>>()
    val charactersLiveData: LiveData<Result<List<Character>>> = _charactersLiveData

    fun getCharacters() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (_charactersLiveData.value != Result.Loading){
                    _charactersLiveData.postValue(Result.Loading)
                    val characters = getCharactersUseCase(nextPage.toString())
                    nextPage++
                    _charactersLiveData.postValue(characters)

                }
            } catch (e: Exception) {
                _charactersLiveData.postValue(Result.Error(e))
            }
        }
    }

}





