package com.ozaltun.marvel.di

import com.ozaltun.marvel.api.MarvelService
import com.ozaltun.marvel.repository.*
import com.ozaltun.marvel.resource.MarvelRetrofit
import com.ozaltun.marvel.viewmodel.AllCharactersViewModel
import com.ozaltun.marvel.viewmodel.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marvelModule = module {

    single { MarvelRetrofit.createService(MarvelService::class.java) }

    single { CharactersRepository(get()) }

    viewModel { AllCharactersViewModel(get()) }

    single<ComicsRepository> { ComicsRepositoryImpl(get()) }

    single<GetComicsByCharacterIdUseCase> { GetComicsByCharacterId(get()) }

    viewModel { CharacterDetailsViewModel(get()) }
}