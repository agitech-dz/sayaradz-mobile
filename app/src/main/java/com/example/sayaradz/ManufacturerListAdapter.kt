package com.example.sayaradz
import android.content.Context
import android.util.Log
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
import kotlinx.android.synthetic.main.manufacturer_list_item.view.*


class ManufacturerListAdapter(val list: MutableList<Manufactors>, val context: Context) :
    RecyclerView.Adapter<ManufacturerListAdapter.ManufacturerViewHolder>() {

    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerViewHolder {
        val articleItemView = LayoutInflater.from(parent.context).inflate(R.layout.manufacturer_list_item, parent, false)
        return ManufacturerViewHolder(articleItemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ManufacturerViewHolder, position: Int) {
        val manufacturer = list[position]
        Log.d("maaaaaaaaaa", manufacturer.name)
        holder.nameText.text = manufacturer.name
        holder.nationalityText.text = manufacturer.nationality
        //handleClick(holder.container, article.id)
        holder.image.clipToOutline = true
       // holder.image.setBackgroundResource(R.drawable.logo)

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
        val action = HomeFragmentDirections.actionHomeFragmentToManufacturerModelsFragment(manufacturerId)
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }

    }



}