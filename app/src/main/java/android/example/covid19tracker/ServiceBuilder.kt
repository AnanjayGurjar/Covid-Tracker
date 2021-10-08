package android.example.covid19tracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    fun createInstance(): ApiInterface{
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(ApiInterface::class.java)
        return api
    }

}