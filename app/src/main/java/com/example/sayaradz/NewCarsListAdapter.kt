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

import com.example.sayaradz.R
import com.example.sayaradz.Data.Manufactors
import com.example.sayaradz.Data.NewCar
import com.example.sayaradz.Data.SharedData
import com.example.sayaradz.Data.Version
import kotlinx.android.synthetic.main.new_car_list_item.view.*


class NewCarsListAdapter(val list: MutableList<Version>, val context: Context) :
    RecyclerView.Adapter<NewCarsListAdapter.NewCarsViewHolder>() {
    private var data: SharedData
    var itemChanged = MutableLiveData<Boolean>()



    init {
        itemChanged.value = false
        data = context as SharedData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewCarsViewHolder {
        val newCarItemView = LayoutInflater.from(parent.context).inflate(R.layout.new_car_list_item, parent, false)
        return NewCarsViewHolder(newCarItemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NewCarsViewHolder, position: Int) {
        val newCar = list[position]
        holder.versionText.text = newCar.name
        holder.priceText.text = newCar.tarif_price.toString()
        holder.followedNewCar.isChecked = newCar.followed
        //handleClick(holder.container, article.id)
        Glide.with(context).load("https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/1-ferrari-logo-julio-woodmansee.jpg").into(holder.image)
        handleClick(holder.container, newCar.code)
    }



    class NewCarsViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var versionText: TextView
        var priceText: TextView
        var followedNewCar:ToggleButton
        var image: ImageView
        var container: View

        init {
            versionText = parent.findViewById(R.id.newCarVersion)
            priceText = parent.findViewById(R.id.newCarApproximativePrice)
            image = parent.findViewById(R.id.newCarImage)
            followedNewCar = parent.findViewById(R.id.followNewCar)
            container = parent.findViewById(R.id.itemContainer)

        }

    }

    private fun handleClick(view: View, versionCode: String) {
        view.followNewCar.setOnClickListener{
            if(view.followNewCar.isChecked) {
                data.followVersion(data.getCurrentAutomobilst(), versionCode)
            }
            else{
                data.unfollowVersion(data.getCurrentAutomobilst(), versionCode)
            }
        }

    }

}