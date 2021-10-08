package android.example.covid19tracker

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    fun createInstance(): ApiInterface{
        val retrofit = Retrofit.Builder()
                .baseUrl("https://data.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(ApiInterface::class.java)
        return api
    }

}