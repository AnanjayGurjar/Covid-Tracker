package android.example.covid19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    val states = mutableListOf<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var adapter = DataAdapter(states, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiInterface =  ServiceBuilder.createInstance().getStateData()

        apiInterface.enqueue(object : Callback<Result>{
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val responseBody = response.body()
                if(responseBody == null){
                    Log.d(TAG, "Something wrong in fetching the result")
                    return
                }

                states.addAll(responseBody.stateWiseData)
                tv_activeCases.text = responseBody.activeCases.toString()
                tv_confirmedCases.text = responseBody.totalCases.toString()
                tv_deceasedCases.text = responseBody.deaths.toString()
                tv_recoverdCases.text = responseBody.recovered.toString()
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<Result>, t: Throwable) {
                Log.d("Failure", t.message.toString())
            }

        })



        }


}
