package android.example.covid19tracker

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("records/LATEST?disableRedirect=true")
    fun getStateData(): Call<Result>
}