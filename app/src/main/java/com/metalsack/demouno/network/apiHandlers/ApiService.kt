package com.metalsack.demouno.network.apiHandlers

import com.metalsack.demouno.BuildConfig
import com.metalsack.demouno.network.apiResponse.ExpertListResponse
import com.metalsack.demouno.network.models.Android
import com.metalsack.demouno.network.models.Result
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * using companion object retrofit instance is created, this class also contains interfaces for apis
 */
interface ApiService {
    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://chatblitz.in/work/foodetect/web_health/")
                    .client(createClient())
                    .build()

            return retrofit.create(ApiService::class.java);
        }

        private fun createClient(): OkHttpClient {
            val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                okHttpClientBuilder.addInterceptor(loggingInterceptor)
            }
            return okHttpClientBuilder.build()
        }
    }

    /*fun makeRetrofit(vararg interceptors: Interceptor) = Retrofit.Builder()
            .baseUrl("https://api.someexample.com/")
            .client(makeHttpClient(interceptors))
            .build()

    private fun makeHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
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
    }*/

    @GET("api/android")
    fun getData(): Observable<List<Android>>

    @GET("search/users")
    fun search(@Query("q") query: String,
               @Query("page") page: Int = 1,
               @Query("per_page") perPage: Int = 20): Observable<Result>

    @GET("search/users")
    fun searchUser(@Query("location") location: String,
                   @Query("language") language: String): Observable<Result>

    @GET("get_recommended_users")
    fun getRecommendedUser(): Observable<ExpertListResponse>
}