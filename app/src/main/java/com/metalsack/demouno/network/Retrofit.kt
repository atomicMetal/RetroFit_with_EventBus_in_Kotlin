package com.metalsack.demouno.network

import com.metalsack.demouno.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object UnauthenticatedApi : Provider<Retrofit>({
    makeRetrofit()
})
fun makeRetrofit(vararg interceptors: Interceptor) = Retrofit.Builder()
        .baseUrl("https://api.someexample.com/")
        .client(makeHttpClient(interceptors))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun makeHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .addInterceptor(loggingInterceptor())
        .build()


fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}

fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build())
}