package com.mustafayosri.gadsleaderboard.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mustafayosri.gadsleaderboard.BuildConfig
import com.noha.gadsleaderboard.network.LeadershipServicesAPIs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitLeaderBoardClient {
    private const val BASE_URL = "https://gadsapi.herokuapp.com"
    private val loggingInterceptor = HttpLoggingInterceptor()

    val callApi: LeadershipServicesAPIs by lazy {


        when (BuildConfig.DEBUG) {

            true -> loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            false -> loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        // Create Retrofit client
        return@lazy retrofit.create(LeadershipServicesAPIs::class.java)
    }

}