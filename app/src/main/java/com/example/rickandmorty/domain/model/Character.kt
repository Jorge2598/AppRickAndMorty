package com.example.rickandmorty.domain.model

import android.os.Parcelable
import com.example.rickandmorty.data.dto.CharacterDTO
import com.example.rickandmorty.data.dto.LocationDTO
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Character(
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
    val location: String? = "",
    val origin: String? = ""
):Parcelable

fun CharacterDTO.toDomain() = Character(
    created = created,
    episode = episode,
    gender = gender,
    id =id,
    image = image,
    name = name,
    species = species,
    status = status,
    type = type,
    url = url,
    location = location.name,
    origin = origin.name
)
