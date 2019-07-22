package com.example.sayaradz.Model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.example.sayaradz.Data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object AdsModel {

  private  var Ads: MutableList<Ad> = mutableListOf()

    fun getAd(id :Int):Ad{
        var searchedAd : Ad? = null
        if(Ads!=null){
            var i:Int = 0
            while (Ads[i].id!=id && i<Ads.size) i++
            if (Ads[i].id == id) searchedAd = Ads[i]

        }
        return searchedAd!!
    }

    fun getAds( numPage: Int, nbrEle: Int, service: RestService): MutableList<Ad> {

        //var Ads: MutableList<Ad> = mutableListOf()

        val call = service.ListAds( numPage, nbrEle)
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
                }
            }
        })
        return Ads
    }

}