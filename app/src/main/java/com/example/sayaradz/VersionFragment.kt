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


class VersionFragment : Fragment() {

    private lateinit var data: SharedData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: com.example.sayaradz.databinding.FragmentVersionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_version, container, false)
        data = this.activity as SharedData
        var modelCode = VersionFragmentArgs.fromBundle(arguments!!).modelCode
        if(modelCode != "") setUpVersionsRecycleView(binding.root, modelCode, data.getRetrofit())
        else setUpVersionsRecycleView(binding.root, "all", data.getRetrofit())
        setHasOptionsMenu(true)
        return binding.root
    }



    //ManufacturerRecycleView--------------------------------------------
    private fun setUpVersionsRecycleView(rootView: View, modelCode:String, service:RestService) {

        var Versions: MutableList<Version> = mutableListOf()

        val call = service.ListVersion(modelCode, 1, 25)
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
                    var recyclerView = rootView.findViewById(R.id.versions_recycler_view) as RecyclerView


                    //realtime list change
                    var adapter = VersionsListAdapter(Versions, context!!)
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

    fun getThis(): VersionFragment{
        return this
    }



}


