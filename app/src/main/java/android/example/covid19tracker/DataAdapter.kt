package android.example.covid19tracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_state.view.*


class DataAdapter(val list: List<Data>, val context: Context): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(data: Data){
            itemView.tv_stateName.text = data.state
            itemView.tv_stateActive.text = data.active
            itemView.tv_stateConfirmed.text = data.confirmed
            itemView.tv_stateDeath.text = data.deaths
            itemView.tv_stateRecovered.text = data.recovered

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}