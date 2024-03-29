package com.example.forecastmvvm.data

import com.example.forecastmvvm.data.response.CurrentWeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "a58eaca89066405aa32143949192208"

// http://api.apixu.com/v1/current.json?key=a58eaca89066405aa32143949192208&q=Dhaka

interface ApixuWeatherService {

    @GET("current.json")
    suspend fun getCurrentWeatherAsync(
        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"
    ): CurrentWeatherResponse

    companion object{
        operator fun invoke(): ApixuWeatherService{
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = chain
                    .request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor).build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.apixu.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherService::class.java)
        }
    }
}