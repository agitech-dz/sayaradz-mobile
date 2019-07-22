package com.example.sayaradz

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sayaradz.Data.*

import com.example.sayaradz.R
import com.example.sayaradz.Main2Activity
import com.example.sayaradz.Model.RestService
import com.example.sayaradz.databinding.FragmentHomeBinding

import com.example.sayaradz.databinding.FragmentManufacturersListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ManufacturersListFragment : Fragment() {

    private lateinit var data: SharedData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentManufacturersListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_manufacturers_list, container, false)
        data = this.activity as SharedData

        setUpManufacturersRecycleView(binding.root, data.getRetrofit())

        return binding.root
    }

    //ManufacturerRecycleView--------------------------------------------
    private fun setUpManufacturersRecycleView(rootView: View, service:RestService) {

        var marks: MutableList<Manufactors> = mutableListOf()

        val call = service.ListMarque(1, 25)
        call.enqueue(object : Callback<ManufactorList> {
            override fun onFailure(call: Call<ManufactorList>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty" + t.message)
            }

            override fun onResponse(call: retrofit2.Call<ManufactorList>, response: Response<ManufactorList>) {
                if (response.code() == 200) {
                    Log.d("Tmarque ", "you've got it"+response.body().toString())
                    // Thread.sleep(40000)
                    var MarqueList = response.body()
                    Log.d("Tmarque ","here you are "+MarqueList!!.getmodels()[0].name)
                    if (MarqueList != null) {
                        marks.addAll(MarqueList.getmodels())
                        Log.d("Markkkkk ","here you are hhh"+marks[0].name)

                    }
                    var recyclerView = rootView.findViewById(R.id.manufacturer_recycler_view_vertical) as RecyclerView


                    //realtime list change
                    var adapter = ManufacturersListAdapterVertical(marks, context!!)
                    adapter.itemChanged.observe(getThis(), Observer { state ->
                        if (state) {
                            adapter.notifyDataSetChanged()
                            adapter.itemChanged.value = false
                        }
                    })
                    recyclerView.adapter = adapter


                    val gridLayoutManagaer = GridLayoutManager(context, 3)
                    recyclerView.layoutManager = gridLayoutManagaer
                    recyclerView.setHasFixedSize(true)
                }
            }
        })



    }

    fun getThis(): ManufacturersListFragment{
        return this
    }



}


