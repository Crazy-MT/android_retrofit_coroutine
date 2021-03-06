package com.example.retrofitandcoroutine.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestUtil private constructor() {
    private val API_BASE_URL = "https://api.github.com/"
    val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

        retrofit = builder.client(httpClient).build()
    }

    companion object {
        private var self: RestUtil? = null

        val instance: RestUtil
            get() {
                if (self == null) {
                    synchronized(RestUtil::class.java) {
                        if (self == null) {
                            self = RestUtil()
                        }
                    }
                }
                return self!!
            }
    }

}
