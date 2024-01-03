package com.example.data.model

import com.example.model.RMCharacter

fun com.example.network.retrofit.model.Result.asCharacter(): RMCharacter  = RMCharacter(
    name = name ?: "",
    imageUrl = image
)
