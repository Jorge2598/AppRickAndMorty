package com.example.rickandmorty.ui.listeners

import com.example.rickandmorty.domain.model.Character

interface OnCharacterListener {

    fun onCharacterClickListener(character:Character)
}