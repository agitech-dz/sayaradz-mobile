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
import com.example.sayaradz.Data.Models
import com.example.sayaradz.Data.SharedData
import kotlinx.android.synthetic.main.model_list_item.view.*


class ModelsListAdapter(val list: MutableList<Models>, val context: Context) :
    RecyclerView.Adapter<ModelsListAdapter.ModelViewHolder>() {
    private var data: SharedData
    var itemChanged = MutableLiveData<Boolean>()

    init {
        itemChanged.value = false
        data = context as SharedData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val articleItemView = LayoutInflater.from(parent.context).inflate(R.layout.model_list_item, parent, false)
        return ModelViewHolder(articleItemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val model = list[position]
        holder.nameText.text = model.name
        //handleClick(holder.container, article.id)
        holder.followModel.isChecked = model.followed
        holder.image.clipToOutline = true
        // holder.image.setBackgroundResource(R.drawable.logo)
        Glide.with(context).load("https://data.pixiz.com/output/user/frame/preview/400x400/9/1/9/0/1820919_220d6.jpg").into(holder.image)
        handleClick(holder.container, model.code)
    }



    class ModelViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var nameText: TextView
        var followModel: ToggleButton
        var image: ImageView
        var container: View

        init {
            nameText = parent.findViewById(R.id.modelName)
            image = parent.findViewById(R.id.modelImage)
            container = parent.findViewById(R.id.itemContainer)
            followModel = parent.findViewById(R.id.followModel)
        }

    }

    private fun handleClick(view: View, modelCode: String) {
        val action = ModelFragmentDirections.actionModelsFragmentToVersionsFragment(modelCode)
        view.setOnClickListener { v: View ->
            v.findNavController().navigate(action)
        }

        view.followModel.setOnClickListener{
            if(view.followModel.isChecked) {
                data.followModel(data.getCurrentAutomobilst(), modelCode)
            }
            else{
                data.unfollowModel(data.getCurrentAutomobilst(), modelCode)
            }
        }

    }


}