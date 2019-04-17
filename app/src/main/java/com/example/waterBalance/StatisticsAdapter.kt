package com.example.waterBalance

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.statictics_item.view.*

class StatisticsAdapter(private val days: ArrayList<DailyData>) : RecyclerView.Adapter<StatisticsAdapter.DaysHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsAdapter.DaysHolder {
        val inflatedView = parent.inflate(R.layout.statictics_item, false)
        return DaysHolder(inflatedView)
    }

    override fun getItemCount() = days.size

    override fun onBindViewHolder(holder: StatisticsAdapter.DaysHolder, position: Int) {
        val item = days[position]
        holder.bindItem(item)
    }

    class DaysHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        override fun onClick(p0: View?) {
            TODO("not implemented")
        }

        fun bindItem(day : DailyData) {
            this.day = day
            view.information.text = day.toString()
        }

        private var view: View = v
        private var day: DailyData? = null
    }
}


