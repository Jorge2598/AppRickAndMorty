package com.example.rickandmorty.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.adapter.CharactersAdapter
import com.example.rickandmorty.ui.viewModel.MainViewModel
import com.example.rickandmorty.util.Result
import com.example.rickandmorty.util.ViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels(factoryProducer = { ViewModelFactory() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setUpRecyclerView()
        // Obtener los datos en el ViewModel
        viewModel.getCharacters()
    }

    fun setUpRecyclerView() { 
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        val charactersAdapter = CharactersAdapter(this, emptyList())
        viewBinding.recyclerView.adapter = charactersAdapter
        // Observar el LiveData en el contexto de la Activity
        viewModel.charactersLiveData.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    val characters = result.data
                    charactersAdapter.submitList(characters)
                }

                is Result.Error -> {
                    val exception = result.exception
                    // Manejar error
                    Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



