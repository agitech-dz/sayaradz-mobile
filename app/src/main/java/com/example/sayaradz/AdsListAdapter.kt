package com.example.sayaradz
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sayaradz.Data.Ad

import com.example.sayaradz.Data.NewCar
import kotlinx.android.synthetic.main.occasion_car_list_item.view.*


class AdsListAdapter(val list: MutableList<Ad>, val context: Context) :
    RecyclerView.Adapter<AdsListAdapter.AdsViewHolder>() {

    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val adItemView = LayoutInflater.from(parent.context).inflate(R.layout.occasion_car_list_item, parent, false)
        return AdsViewHolder(adItemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        val ad = list[position]
        holder.carInfoText.text = ad.manufacturer_name + " " + ad.model + "\n" + ad.version
        holder.priceText.text = ad.minPrice.toString()
        holder.yearAndDistanceText.text = ad.year.toString() + " | " + ad.distance.toString() + " DA"
        handleClick(holder.container, ad.id)
        Glide.with(context).load("https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/1-ferrari-logo-julio-woodmansee.jpg").into(holder.image)
    }



    class AdsViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var carInfoText: TextView
        var priceText: TextView
        var yearAndDistanceText: TextView
        var image: ImageView
        var container: View

        init {
            carInfoText = parent.findViewById(R.id.carInfo)
            priceText = parent.findViewById(R.id.carMinPrice)
            yearAndDistanceText= parent.findViewById(R.id.carYearAndDistance)
            image = parent.findViewById(R.id.adImage)
            container = parent.findViewById(R.id.itemContainer)
        }

    }

    private fun handleClick(view: View, adId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToAdDetailsFragment(adId)
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }

    }

}