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
import kotlinx.android.synthetic.main.ad_list_item.view.*


class AdsListAdapterVertical(val list: MutableList<Ad>, val context: Context) :
    RecyclerView.Adapter<AdsListAdapterVertical.AdsViewHolder>() {

    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val adItemView = LayoutInflater.from(parent.context).inflate(R.layout.ad_list_item, parent, false)
        return AdsViewHolder(adItemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        val ad = list[position]
        holder.adManufacturerModelText.text = ad.manufacturer_name + " " + ad.model + "\n"
        holder.adVersionText.text = ad.version
        holder.adPriceText.text = ad.minPrice.toString()
        holder.adYearAndDistanceText.text = ad.year.toString() + " | " + ad.distance.toString() + " Km"
        //handleClick(holder.container, article.id)
        Glide.with(context).load("https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/1-ferrari-logo-julio-woodmansee.jpg").into(holder.image)
    }



    class AdsViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var adNumberOfViewText: TextView
        var adManufacturerModelText: TextView
        var adVersionText: TextView
        var adYearAndDistanceText: TextView
        var adPriceText: TextView
        var image: ImageView
        var container: View

        init {
            adNumberOfViewText = parent.findViewById(R.id.adNumberOfView)
            adManufacturerModelText = parent.findViewById(R.id.adManufacturerModel)
            adVersionText= parent.findViewById(R.id.adVersion)
            adYearAndDistanceText = parent.findViewById(R.id.adYearAndDistance)
            adPriceText = parent.findViewById(R.id.adPrice)
            image = parent.findViewById(R.id.adFisrstImage)
            container = parent.findViewById(R.id.itemContainer)
        }

    }

}