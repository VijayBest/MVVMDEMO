package com.app.demo.di



import com.app.demo.activity.LoginViewModel
import com.app.demo.activity.list.ListViewModel
import com.app.demo.api.ApiService
import com.app.demo.api.Constants
import com.app.demo.repo.AuthRepo
import com.app.demo.sharedpref.SharedPreferenceHelper
import com.example.ks.di.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Vijay.
 */

val viewModels = module {

    viewModel { LoginViewModel(get()) }
    viewModel { ListViewModel(get()) }


}


val networkModule = module {
    single { AuthInterceptor(get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideForecastApi(get()) }
    single { SharedPreferenceHelper(androidContext()) }
}
val repos= module {
    single { AuthRepo(get(),get()) }

}
fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
    return Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient(authInterceptor))
        .build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(50, TimeUnit.SECONDS)
        .writeTimeout(50, TimeUnit.SECONDS)
        .readTimeout(50, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .addInterceptor(httpInterceptor()).build()
}

fun provideForecastApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
private fun httpInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

