package com.example.melanomaclassifier.di.module

import android.content.Context
import com.example.melanomaclassifier.BuildConfig
import com.example.melanomaclassifier.data.network.MainApi
import com.example.melanomaclassifier.data.network.RequestInterceptor
import com.example.melanomaclassifier.data.preferences.AppPreferencesHelper
import com.example.melanomaclassifier.util.CommonUtil
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.Util
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttp(request: RequestInterceptor, chuckInterceptor: ChuckInterceptor): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder().addInterceptor(logging)
            .addInterceptor(request)
            .addInterceptor(chuckInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
    }

    @Provides
    @Singleton
    fun providesChuckInterceptor(context: Context) : ChuckInterceptor{
        return ChuckInterceptor(context)
    }

    @Provides
    @Singleton
    fun providesRequestInterceptor(preferenceHelper: AppPreferencesHelper): RequestInterceptor {
        return RequestInterceptor(preferenceHelper)
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient.Builder): Retrofit {
        val retroBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(CommonUtil.buildGson()))

        retroBuilder.client(httpClient.build())

        return retroBuilder.build()
    }

    @Provides
    @Singleton
    fun providedApi(retrofit: Retrofit): MainApi = MainApi.create(retrofit)
}