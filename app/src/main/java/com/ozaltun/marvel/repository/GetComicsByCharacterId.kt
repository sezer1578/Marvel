package com.ozaltun.marvel.repository

import com.ozaltun.marvel.model.comics.Comic
import java.lang.Exception

class GetComicsByCharacterId(
    private val repository: ComicsRepository
): GetComicsByCharacterIdUseCase {
    override suspend fun invoke(characterId: Int): List<Comic> = try {
        repository.getComicsByCharacterId(characterId)
    } catch (ex: Exception) {
        listOf()
    }
}

interface GetComicsByCharacterIdUseCase {
    suspend operator fun invoke(characterId: Int): List<Comic>
}