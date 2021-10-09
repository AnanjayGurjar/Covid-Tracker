package android.example.covid19tracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    val states = mutableListOf<Data>()
    var adapter = DataAdapter(states, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeContainer.setOnRefreshListener {
            getResultFromApi()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        getResultFromApi()


    }

    private fun getResultFromApi(){
        swipeContainer.isRefreshing = true
        val apiInterface =  ServiceBuilder.createInstance().getStateData()

        apiInterface.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                swipeContainer.isRefreshing = false
                val responseBody = response.body()
                if (responseBody == null) {
                    Log.d(TAG, "Something wrong in fetching the result")
                    return
                }

                states.addAll(responseBody.stateWiseData)
                tv_activeCases.text = responseBody.activeCases.toString()
                tv_confirmedCases.text = responseBody.totalCases.toString()
                tv_deceasedCases.text = responseBody.deaths.toString()
                tv_recoverdCases.text = responseBody.recovered.toString()
                val time = responseBody.lastUpdatedAtApify
                val finalTiem = covertTimeToText(time)
                tv_lastUpdated.text = "Last Updated \n $finalTiem"
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                swipeContainer.isRefreshing = false
                Log.d("Failure", t.message.toString())
            }

        })
    }


    private fun covertTimeToText(dataDate: String?): String? {
        var convTime: String? = null
        val prefix = ""
        val suffix = "Ago"
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val pasTime = dateFormat.parse(dataDate)
            val nowTime = Date()
            val dateDiff = nowTime.time - pasTime.time
            val second = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day = TimeUnit.MILLISECONDS.toDays(dateDiff)
            if (second < 60) {
                convTime = "$second Seconds $suffix"
            } else if (minute < 60) {
                convTime = "$minute Minutes $suffix"
            } else if (hour < 24) {
                convTime = "$hour Hours $suffix"
            } else if (day >= 7) {
                convTime = if (day > 360) {
                    (day / 360).toString() + " Years " + suffix
                } else if (day > 30) {
                    (day / 30).toString() + " Months " + suffix
                } else {
                    (day / 7).toString() + " Week " + suffix
                }
            } else if (day < 7) {
                convTime = "$day Days $suffix"
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e("ConvTimeE", "fh")
        }
        return convTime
    }

}
