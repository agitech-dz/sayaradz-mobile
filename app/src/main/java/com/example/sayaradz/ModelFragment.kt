package com.example.sayaradz

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sayaradz.Data.*
import com.example.sayaradz.Model.RestService
import kotlinx.android.synthetic.main.fragment_model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ModelFragment : Fragment() {

    private lateinit var data: SharedData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: com.example.sayaradz.databinding.FragmentModelBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_model, container, false)
        data = this.activity as SharedData
        var manufacturerId = ModelFragmentArgs.fromBundle(arguments!!).manufacturerId
        setUpModelsRecycleView(binding.root, manufacturerId, data.getRetrofit())
        setHasOptionsMenu(true)
        return binding.root
    }



        //ManufacturerRecycleView--------------------------------------------
    private fun setUpModelsRecycleView(rootView: View, manufacturerId:Int, service: RestService) {
            var models: MutableList<Models>  = mutableListOf()


            val call = service.ListModel(manufacturerId,1, 25)
            call.enqueue(object : Callback<ModelList> {
                override fun onFailure(call: Call<ModelList>, t: Throwable) {
                    Log.d("fail ", "you've got it but with a big fail shitty" + t.message)
                }

                override fun onResponse(call: retrofit2.Call<ModelList>, response: Response<ModelList>) {
                    if (response.code() == 200) {
                        Log.d("sucess ", "you've got it")
                        var modelList = response.body()
                        if (modelList != null) modelList.getmodels()?.let { models.addAll(it) }

                        var recyclerView = rootView.findViewById(R.id.models_recycler_view) as RecyclerView


                        //realtime list change
                        var adapter = ModelsListAdapter(models, context!!)
                        adapter.itemChanged.observe(getThis(), Observer { state ->
                            if (state) {
                                adapter.notifyDataSetChanged()
                                adapter.itemChanged.value = false
                            }
                        })
                        recyclerView.adapter = adapter
                        val gridLayoutManagaer = GridLayoutManager(context, 2)
                        recyclerView.layoutManager = gridLayoutManagaer
                        recyclerView.setHasFixedSize(true)
                    }
                }
            })



    }
    fun getThis():ModelFragment{
        return this
    }



}


