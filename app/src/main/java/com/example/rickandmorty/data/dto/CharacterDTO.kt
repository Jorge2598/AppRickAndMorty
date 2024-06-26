package com.example.rickandmorty.data.dto

data class CharacterDTO(
    val created: String? = "",
    val episode: List<String>? = emptyList(),
    val gender: String? = "",
    val id: Int? = null,
    val image: String? = "",
    val name: String? = "",
    val species: String? = "",
    val status: String? = "",
    val type: String? = "",
    val url: String? = "",
    val location: LocationDTO,
    val origin: OriginDTO
)