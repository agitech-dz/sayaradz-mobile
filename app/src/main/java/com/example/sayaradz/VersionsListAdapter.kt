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
import com.example.sayaradz.Data.SharedData
import com.example.sayaradz.Data.Version
import kotlinx.android.synthetic.main.model_list_item.view.*
import kotlinx.android.synthetic.main.version_list_item.view.*


class VersionsListAdapter(val list: MutableList<Version>, val context: Context) :
    RecyclerView.Adapter<VersionsListAdapter.VersionViewHolder>() {

    var itemChanged = MutableLiveData<Boolean>()
    private var data: SharedData

    init {
        itemChanged.value = false
        data = context as SharedData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersionViewHolder {
        val articleItemView = LayoutInflater.from(parent.context).inflate(R.layout.version_list_item, parent, false)
        return VersionViewHolder(articleItemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VersionViewHolder, position: Int) {
        val version = list[position]
        holder.manufacturerAndModelText.text = version.model
        holder.versionNameText.text = version.name
        holder.tarifVersionText.text = version.tarif_price.toString() + " DA"
        holder.followedVersion.isChecked = version.followed
        //handleClick(holder.container, article.id)
        holder.image.clipToOutline = true
        // holder.image.setBackgroundResource(R.drawable.logo)
        Glide.with(context).load("https://data.pixiz.com/output/user/frame/preview/400x400/9/1/9/0/1820919_220d6.jpg").into(holder.image)
        handleClick(holder.container, version.code)
    }



    class VersionViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var manufacturerAndModelText: TextView
        var versionNameText: TextView
        var tarifVersionText: TextView
        var followedVersion:ToggleButton
        var image: ImageView
        var container: View

        init {
            manufacturerAndModelText = parent.findViewById(R.id.newCarManufacturerModel)
            versionNameText = parent.findViewById(R.id.newCarVersion)
            tarifVersionText = parent.findViewById(R.id.newCarPrice)
            followedVersion = parent.findViewById(R.id.followVersion)
            image = parent.findViewById(R.id.newCarImage)
            container = parent.findViewById(R.id.itemContainer)
        }

    }

    private fun handleClick(view: View, versionCode: String) {
        view.followVersion.setOnClickListener{
            if(view.followVersion.isChecked) {
                data.followVersion(data.getCurrentAutomobilst(), versionCode)
            }
            else{
                data.unfollowVersion(data.getCurrentAutomobilst(), versionCode)
            }
        }

    }




}