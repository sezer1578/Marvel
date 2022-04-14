package com.ozaltun.marvel.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ozaltun.marvel.api.MarvelService
import com.ozaltun.marvel.model.character.Character
import kotlinx.coroutines.flow.Flow

class CharactersRepository(private val apiService: MarvelService) {

    fun getResultStream(): Flow<PagingData<Character>> {
        return Pager(config = PagingConfig(pageSize = pageSize, maxSize = pageMax),
            pagingSourceFactory = { CharacterPagingSource(apiService) }
        ).flow
    }

    companion object {
        const val pageSize: Int = 30
        const val pageMax: Int = 200
    }
}