package dev.qamar.a24itest.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dev.qamar.a24itest.BuildConfig
import dev.qamar.a24itest.utils.AppConst
import dev.qamar.a24itest.utils.Utils
import dev.qamar.a24itest.data.MovieService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object RetrofitModule {

    @JvmStatic @Provides @Singleton
    fun provideBooksService() : MovieService = Retrofit.Builder()
        .baseUrl(AppConst.BASE_URL)
        .client(
            OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .enableLogs()
                .addInterceptor { chain ->
                    var request = chain.request()
                    val url = request.url().newBuilder().addQueryParameter("api_key", Utils.decode(AppConst.K)).build()
                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                }.build())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(MovieService::class.java)
}

fun OkHttpClient.Builder.enableLogs() : OkHttpClient.Builder{
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        this.addInterceptor(logging)
    }
    return this;
}
