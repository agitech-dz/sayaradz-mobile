package com.example.sayaradz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sayaradz.Data.Manufactors


class ManufacturersListAdapterVertical(val list: MutableList<Manufactors>, val context: Context) :
    RecyclerView.Adapter<ManufacturersListAdapterVertical.ManufacturerViewHolder>() {

    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerViewHolder {
        val manufacturerItemView = LayoutInflater.from(parent.context).inflate(R.layout.manufacturer_list_item, parent, false)
        return ManufacturerViewHolder(manufacturerItemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ManufacturerViewHolder, position: Int) {
        val manufacturer = list[position]
        holder.nameText.text = manufacturer.name
        holder.nationalityText.text = manufacturer.nationality
        //holder.modelsNumberText.text = "1200"
        // holder.versionsNumberText.text = "1200"
        //holder.followsNumberText.text = "1200"
        //handleClick(holder.container, article.id)
        Glide.with(context).load("https://data.pixiz.com/output/user/frame/preview/400x400/9/1/9/0/1820919_220d6.jpg").into(holder.image)
        handleClick(holder.container, manufacturer.id)
    }



    class ManufacturerViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var nameText: TextView
        var nationalityText: TextView

        var image: ImageView
        var container: View

        init {
            nameText = parent.findViewById(R.id.manufacturerName)
            nationalityText = parent.findViewById(R.id.manufacturerNationality)
            image = parent.findViewById(R.id.manufacturerImage)
            container = parent.findViewById(R.id.itemContainer)
        }

    }

    private fun handleClick(view: View, manufacturerId: Int) {
        val action = ManufacturersListFragmentDirections.actionManufacturersListFragmentToManufacturerModelsFragment(manufacturerId)
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }

    }

}