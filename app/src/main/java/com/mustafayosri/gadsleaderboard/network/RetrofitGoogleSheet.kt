package com.mustafayosri.gadsleaderboard.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mustafayosri.gadsleaderboard.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitGoogleSheet {

    private const val GOOGLE_SHEET_URL = "https://docs.google.com"
    private val loggingInterceptor = HttpLoggingInterceptor()

    val submitAPI: SubmitServicesAPIs by lazy {

        when (BuildConfig.DEBUG) {

            true -> loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            false ->loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        }
        val retrofit = Retrofit.Builder()
            .baseUrl(GOOGLE_SHEET_URL)
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

        retrofit.create(SubmitServicesAPIs::class.java)

    }
}
