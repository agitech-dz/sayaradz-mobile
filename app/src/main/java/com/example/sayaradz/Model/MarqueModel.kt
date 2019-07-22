package com.example.sayaradz.Model

import android.util.Log
import com.example.sayaradz.Data.ManufactorList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.sayaradz.Data.Manufactors

object MarqueModel {

    fun getManufactors( numPage: Int, nbrEle: Int, service: RestService):  MutableList<Manufactors>{

        var marks: MutableList<Manufactors> = mutableListOf()

        val call = service.ListMarque(numPage, nbrEle)
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
                }
            }
        })
        return marks
    }





}