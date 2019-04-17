package com.example.waterBalance
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_types.view.*
import android.widget.ImageView



// Адаптер для RecycleView
class NewAdapter(val waterTypes : ArrayList<String>, val waterTypesImages : ArrayList<Int>,
                 val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Подключение activity_types.xml в ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_types, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.waterType.text = waterTypes.get(position) // Вывод названия напитка
        holder.imageView.setImageResource(waterTypesImages.get(position)) // Картинка для напитка
        holder.itemView.setOnClickListener {
            DayActivity().setSelectedType(position)
        }
    }

    override fun getItemCount(): Int {
        return waterTypes.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val waterType = view.waterType
    val imageView = view.findViewById<ImageView>(R.id.image)
}
