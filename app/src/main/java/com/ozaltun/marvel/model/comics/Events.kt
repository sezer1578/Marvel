package com.ozaltun.marvel.model.comics

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemEvent>,
    val returned: Int
)