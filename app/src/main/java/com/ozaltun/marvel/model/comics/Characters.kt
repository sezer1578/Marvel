package com.ozaltun.marvel.model.comics

data class Characters(
    val available: Int,
    val collectionURI: String,
    val itemCharacters: List<ItemCharacter>,
    val returned: Int
)
