package com.ozaltun.marvel.resource

import com.ozaltun.marvel.constant.Constant
import com.ozaltun.marvel.extensions.md5
import com.ozaltun.marvel.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MarvelRetrofit {

    companion object {
        private lateinit var retrofit: Retrofit
        private const val BASE_URL = Constant.BASE_URL

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val chainRequest = chain.request()
                val urlOriginal = chainRequest.url()
                val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
                val httpUrl = urlOriginal.newBuilder()
                    .addQueryParameter("apikey", BuildConfig.API_KEY)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter("hash", "$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}".md5())
                    .build()

                chain.proceed(chainRequest.newBuilder().url(httpUrl).build())
            }
            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}