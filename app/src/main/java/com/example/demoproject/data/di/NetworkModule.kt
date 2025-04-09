package com.example.demoproject.data.di

import com.example.demoproject.data.network.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providerInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val newUrl = originalRequest.url.newBuilder()
                .addQueryParameter("apiKey", Config.API_KEY)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .addHeader("Content-Type", "application/json")
                .build()

            chain.proceed(newRequest)
        }
    }
}