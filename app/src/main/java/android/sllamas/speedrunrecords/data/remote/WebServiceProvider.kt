package android.sllamas.speedrunrecords.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceProvider {

    fun <T> getService(service: Class<T>): T = Retrofit.Builder()
        .baseUrl("https://www.speedrun.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(service)
}