package android.example.covid19tracker

import com.google.gson.annotations.SerializedName

data class Result (

    @SerializedName("regionData") val stateWiseData: List<Data>,
    val lastUpdatedAtApify: String
)
data class Data(

        @SerializedName("activeCases")
        val active: String,
        @SerializedName("totalInfected")
        val confirmed: String,
        @SerializedName("deceased")
        val deaths: String,
        @SerializedName("newInfected")
        val deltaconfirmed: String,
        @SerializedName("newDeceased")
        val deltadeaths: String,
        @SerializedName("newRecovered")
        val deltarecovered: String,
       // val lastupdatedtime: String,
        val recovered: String,
        @SerializedName("region")
        val state: String,



)