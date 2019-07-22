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
import androidx.navigation.fragment.findNavController
import com.example.sayaradz.Model.RestService

import kotlinx.android.synthetic.main.fragment_home.*

import com.example.sayaradz.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var data: SharedData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        data = this.activity as SharedData

        setUpManufacturersRecycleView(binding.root, data.getRetrofit())
     //   Thread.sleep(30000)
        setUpNewCarsRecycleView(binding.root, data.getRetrofit())
        setUpAdsRecycleView(binding.root, data.getRetrofit())

        return binding.root
    }


    //ManufacturerRecycleView--------------------------------------------
    private fun setUpManufacturersRecycleView(rootView: View, service: RestService) {
        var list: MutableList<Manufactors> = mutableListOf()

        val call = service.ListMarque(1, 10)
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
                        list.addAll(MarqueList.getmodels())
                       // Log.d("Markkkkk ","here you are hhh"+marks[0].name)

                    }
                    var recyclerView = rootView.findViewById(R.id.manufacturer_recycler_view) as RecyclerView


                    //realtime list change
                    var adapter = ManufacturerListAdapter(list, context!!)
                    adapter.itemChanged.observe(getThis(), Observer { state ->
                        if (state) {
                            adapter.notifyDataSetChanged()
                            adapter.itemChanged.value = false
                        }
                    })
                    recyclerView.adapter = adapter


                    recyclerView.setHasFixedSize(true)
                    val horizontalLayoutManagaer = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recyclerView.layoutManager = horizontalLayoutManagaer
                }
            }
        })


    }

    //NewCarsManufacturerRecycleView--------------------------------------------
    private fun setUpNewCarsRecycleView(rootView: View, service: RestService) {
        var Versions: MutableList<Version> = mutableListOf()

        val call = service.ListVersion("all", 1, 10)
        call.enqueue(object : Callback<VersionsList> {
            override fun onFailure(call: Call<VersionsList>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty" + t.message)
            }

            override fun onResponse(call: retrofit2.Call<VersionsList>, response: Response<VersionsList>) {
                if (response.code() == 200) {
                    Log.d("sucess ", "you've got it")
                    var versionList = response.body()

                    if (versionList != null) {

                        Versions.addAll(versionList.getVersion()!!)
                    }
                    var recyclerView = rootView.findViewById(R.id.new_cars_recycler_view) as RecyclerView


                    //realtime list change
                    var adapter = NewCarsListAdapter(Versions, context!!)
                    adapter.itemChanged.observe(getThis(), Observer { state ->
                        if (state) {
                            adapter.notifyDataSetChanged()
                            adapter.itemChanged.value = false
                        }
                    })
                    recyclerView.adapter = adapter


                    recyclerView.setHasFixedSize(true)
                    val horizontalLayoutManagaer = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recyclerView.layoutManager = horizontalLayoutManagaer
                }
            }
        })

    }

    //AdsManufacturerRecycleView--------------------------------------------
    private fun setUpAdsRecycleView(rootView: View, service: RestService) {
        var Ads: MutableList<Ad> = mutableListOf()

        val call = service.ListAds( 1, 10)
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
                    var recyclerView = rootView.findViewById(R.id.ads_recycler_view) as RecyclerView
                    recyclerView.setHasFixedSize(true)

                    //realtime list change
                    var adapter = AdsListAdapter(Ads, context!!)
                    adapter.itemChanged.observe(getThis(), Observer { state ->
                        if (state) {
                            adapter.notifyDataSetChanged()
                            adapter.itemChanged.value = false
                        }
                    })
                    recyclerView.adapter = adapter

                    val horizontalLayoutManagaer = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recyclerView.layoutManager = horizontalLayoutManagaer

                }

            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        // Sending data from one fragment to another fragment
        manufacturer_recycler_view_viewall.setOnClickListener{

                val action = HomeFragmentDirections.actionHomeFragmentToManufacturersListFragment()

                findNavController().navigate(action)

        }

        new_cars_recycler_view_viewall.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToVersionsFragment("")

            findNavController().navigate(action)
        }

        ads_recycler_view_viewall.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToAdsFragment()

            findNavController().navigate(action)
        }


    }

    fun getThis(): HomeFragment{
        return this
    }

}


