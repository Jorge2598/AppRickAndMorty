package com.example.rickandmorty.data.dto


data class CharacterResponseDTO(
    val  info: InfoDTO,
    val results: List<CharacterDTO>
)