package com.example.rickandmorty.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.Telephony.Mms.Intents
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.ui.adapter.CharactersAdapter
import com.example.rickandmorty.ui.listeners.OnCharacterListener
import com.example.rickandmorty.ui.viewModel.MainViewModel
import com.example.rickandmorty.util.Result


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnCharacterListener {
    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    var isLoading = false

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
        val charactersAdapter = CharactersAdapter(this, emptyList(), this)
        viewBinding.recyclerView.adapter = charactersAdapter
        // Observar el LiveData en el contexto de la Activity

        val layoutManager = viewBinding.recyclerView.layoutManager as LinearLayoutManager
        viewBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                // Si el último elemento visible es igual al total de elementos menos uno (estás en el último elemento)
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && isLoading == false) {
                    // Aquí puedes cargar más elementos
                    isLoading = true
                    viewModel.getCharacters()
                    isLoading = false
                }
            }
        })

        viewModel.charactersLiveData.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    viewBinding.progressBar.visibility = View.GONE
                    val characters = result.data
                    charactersAdapter.submitList(characters)
                }
                is Result.Error -> {
                    val exception = result.exception
                    // Manejar error
                    Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
                is Result.Loading ->{
                    viewBinding.progressBar.visibility = View.VISIBLE
                }

            }
        }
    }

    override fun onCharacterClickListener(character: Character) {
        val intent = Intent(this, CharacterDetailView::class.java)
        intent.putExtra("character", character)
        startActivity(intent)

    }
}








