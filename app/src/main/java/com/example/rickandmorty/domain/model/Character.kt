package com.example.rickandmorty.domain.model

import com.example.rickandmorty.data.dto.CharacterDTO
import com.example.rickandmorty.data.dto.ResultDTO


data class Characters(
    val created: String? = "",
    val episode: List<String>? = emptyList(),
    val gender: String? = "",
    val id: Int? = null,
    val image: String? = "",
    val name: String? = "",
    val species: String? = "",
    val status: String? = "",
    val type: String? = "",
    val url: String? = ""
)

fun  ResultDTO.toDomain() = Characters(
    created = created,
    episode = episode,
    gender = gender,
    id =id,
    image = image,
    name = name,
    species = species,
    status = status,
    type = type,
    url = url
)
