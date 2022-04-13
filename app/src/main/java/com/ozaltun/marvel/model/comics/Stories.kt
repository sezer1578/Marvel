package com.ozaltun.marvel.model.comics

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemStories>,
    val returned: Int
)
