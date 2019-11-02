package com.example.simpleweathermvp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweathermvp.R
import com.example.simpleweathermvp.entity.City
import kotlinx.android.synthetic.main.city_list_items.view.*

class CityAdapter(
        private val onRemoveClicks: (City) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var values: List<City> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.city_list_items, parent, false)
        return CityViewHolder(itemView)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(viewHolder: CityViewHolder, position: Int) {
        viewHolder.bind(values[position])
    }

    fun setItems(listCity: List<City>){
        values = listCity
        notifyDataSetChanged()
    }


    inner class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        init {
            itemView.v_close.setOnClickListener {
                onRemoveClicks.invoke(values[adapterPosition])
            }
        }

        fun bind(item: City) {
            itemView.tv_city_item.text = item.name
        }
    }
}
