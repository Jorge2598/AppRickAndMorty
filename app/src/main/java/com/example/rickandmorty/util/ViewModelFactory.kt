/*
package com.example.rickandmorty.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.rickandmorty.data.CharacterRepositoryImp
import com.example.rickandmorty.data.api.network.RickAndMortyApi
import com.example.rickandmorty.domain.useCase.GetAllCharactersUseCase
import com.example.rickandmorty.ui.viewModel.MainViewModel
import com.example.rickandmorty.util.di.ApplicationModule

class ViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val viewModel: ViewModel = when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                ApplicationModule.provideGetAllCharactersUseCase()
            )

            else -> null


        } ?: throw Exception("este viewModel no existe")


        return viewModel as T
    }
}
*/