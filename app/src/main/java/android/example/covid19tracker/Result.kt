package android.example.covid19tracker

import com.google.gson.annotations.SerializedName

data class Result (
    val stateWiseData: List<Data>
)
data class Data(

        val active: String,
        val confirmed: String,
        val deaths: String,
        val deltaconfirmed: String,
        val deltadeaths: String,
        val deltarecovered: String,
        val lastupdatedtime: String,
        val recovered: String,
        val state: String,

)