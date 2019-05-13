package com.booleansystems.codechallenge.di

import com.booleansystems.codechallenge.dependencies.rest.RestEndpoints
import com.booleansystems.codechallenge.utils.Constants
import com.booleansystems.data.repository.ResumeRepository
import com.booleansystems.mycv.ui.home.presenter.HomePresenter
import com.booleansystems.mycv.ui.home.usecase.ResumeDataSourceImpl
import com.booleansystems.usecase.ExtractResumeInformationUseCase
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

/**

Created by oscar on 10/05/19
operez@na-at.com.mx
 */

val ApplicationModule = module(definition = {

    factory {
        val endpoints: RestEndpoints = get()
        return@factory ResumeDataSourceImpl(endpoints)
    }

    factory {
        val remoteDataSource: ResumeDataSourceImpl = get()
        return@factory ResumeRepository(remoteDataSource)
    }
    factory {
        val extractResumeInformationUseCase = ExtractResumeInformationUseCase(get())
        return@factory extractResumeInformationUseCase
    }

    factory {
        return@factory HomePresenter(get())
    }

})


val NetModule = module {


    factory {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return@factory loggingInterceptor
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.HttpConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        StethoInterceptor()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addNetworkInterceptor(get<StethoInterceptor>())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    single { get<Retrofit>().create(RestEndpoints::class.java) }
}
