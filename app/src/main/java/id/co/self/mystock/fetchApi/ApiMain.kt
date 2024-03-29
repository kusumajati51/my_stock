package id.co.self.mystock.fetchApi

import id.co.self.mystock.BuildConfig
import id.co.self.mystock.fetchApi.url.LinkApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiMain {
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else
                HttpLoggingInterceptor.Level.NONE
        })
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    private val retrofit =  Retrofit.Builder()
        .baseUrl(LinkApi.url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val services: ApiInterface = retrofit.create(ApiInterface::class.java)
}