package android.example.covid19tracker

import com.google.gson.annotations.SerializedName

data class Result (

    @SerializedName("regionData") val stateWiseData: List<Data>,
    val activeCases: Int,
    val recovered: Int,
    val deaths: Int,
    val totalCases: Int,
    val lastUpdatedAtApify: String

    /*
    * "activeCases": 240221,
    "activeCasesNew": -3977,
    "recovered": 33225221,
    "recoveredNew": 24963,
    "deaths": 450127,
    "deathsNew": 271,
    "previousDayTests": 1385706,
    "totalCases": 33915569,*/
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