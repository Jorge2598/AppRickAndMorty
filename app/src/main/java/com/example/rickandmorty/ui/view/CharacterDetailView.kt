package com.example.rickandmorty.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityCharacterDetailViewBinding
import com.example.rickandmorty.domain.model.Character
import com.squareup.picasso.Picasso

class CharacterDetailView : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCharacterDetailViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCharacterDetailViewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val intent = intent
        val character = intent.getParcelableExtra<Character>("character")

        if (character != null) {
            Picasso.get().load(character.image).into(viewBinding.profileCharacter)
            viewBinding.nameCharacter.text = character.name
            when(character.status){
                "Alive" ->{
                    viewBinding.statusCharacter.text = character.status
                    viewBinding.statusCharacter.setTextColor(ContextCompat.getColor(this, R.color.green))
                    val drawable = ContextCompat.getDrawable(this, R.drawable.estado_vivo)
                    viewBinding.statusCharacter.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                }
                "Dead" ->{
                    viewBinding.statusCharacter.text = character.status
                    viewBinding.statusCharacter.setTextColor(ContextCompat.getColor(this , R.color.red))
                    val drawable = ContextCompat.getDrawable(this, R.drawable.estado_muerto)
                    viewBinding.statusCharacter.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null)
                }
                "unknown" ->{
                    viewBinding.statusCharacter.text = character.status
                    viewBinding.statusCharacter.setTextColor(ContextCompat.getColor(this , R.color.gris_oscuro))
                    val drawable = ContextCompat.getDrawable(this, R.drawable.estado_desconocido)
                    viewBinding.statusCharacter.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null)
                }

            }
            viewBinding.specieCharacter.text = character.species
            if (character.type != "") viewBinding.typeCharacter.text = character.type
            else viewBinding.typeCharacter.text = "none"
            viewBinding.originCharacter.text = character.origin
            viewBinding.locationCharacter.text = character.location
            viewBinding.genderCharacter.text = character.gender


        }
    }
}

