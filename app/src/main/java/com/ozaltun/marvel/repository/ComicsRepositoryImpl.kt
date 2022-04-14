package com.ozaltun.marvel.repository

import com.ozaltun.marvel.api.MarvelService
import com.ozaltun.marvel.model.comics.Comic
import com.ozaltun.marvel.resource.Output
import com.ozaltun.marvel.resource.parseResponse
import java.lang.Exception

class ComicsRepositoryImpl(
    private val service: MarvelService): ComicsRepository {
    override suspend fun getComicsByCharacterId(characterId: Int): List<Comic> {
        return when (val result = service.getComicsByCharacterId(characterId).parseResponse()) {
            is Output.Success -> {
                result.value.data.results
            }
            is Output.Failure -> throw GetComicsException()
        }
    }
}

interface ComicsRepository {
    suspend fun getComicsByCharacterId(characterId: Int): List<Comic>
}

class GetComicsException: Exception()