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
import com.example.sayaradz.Model.RestService
import kotlinx.android.synthetic.main.fragment_version.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdFragment : Fragment() {

    private lateinit var data: SharedData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: com.example.sayaradz.databinding.FragmentAdBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_ad, container, false)
        data = this.activity as SharedData
        setUpAdsRecycleView(binding.root, data.getRetrofit())
        setHasOptionsMenu(true)
        return binding.root
    }



    //ManufacturerRecycleView--------------------------------------------
    private fun setUpAdsRecycleView(rootView: View, service:RestService) {

        var Ads: MutableList<Ad> = mutableListOf()

        val call = service.ListAds( 1, 25)
        call.enqueue(object : Callback<AdList> {
            override fun onFailure(call: Call<AdList>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty" + t.message)
            }

            override fun onResponse(call: retrofit2.Call<AdList>, response: Response<AdList>) {
                if (response.code() == 200) {
                    Log.d("sucess ", "you've got it")
                    var AdList = response.body()

                    if (AdList != null) {

                        Ads.addAll(AdList.getVersion()!!)
                    }
                    var recyclerView = rootView.findViewById(R.id.ads_recycler_view_vertical) as RecyclerView


                    //realtime list change
                    var adapter = AdsListAdapterVertical(Ads, context!!)
                    adapter.itemChanged.observe(getThis(), Observer { state ->
                        if (state) {
                            adapter.notifyDataSetChanged()
                            adapter.itemChanged.value = false
                        }
                    })
                    recyclerView.adapter = adapter

                    val verticalLayoutManagaer = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    recyclerView.layoutManager = verticalLayoutManagaer
                    recyclerView.setHasFixedSize(true)
                }
            }
        })

    }

    fun getThis(): AdFragment{
        return this
    }



}


