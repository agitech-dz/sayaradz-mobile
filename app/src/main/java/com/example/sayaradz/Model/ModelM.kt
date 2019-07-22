package com.example.sayaradz.Model
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.example.sayaradz.Data.FollowedModel
import com.example.sayaradz.Data.ModelList
import com.example.sayaradz.Data.Models
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ModelM {



    fun getModels( manif : Int, numPage:Int, nbrEle :Int, service:RestService) :  MutableList<Models> {
    var models: MutableList<Models>  = mutableListOf()


    val call = service.ListModel(manif,numPage, nbrEle)
    call.enqueue(object : Callback<ModelList> {
        override fun onFailure(call: Call<ModelList>, t: Throwable) {
            Log.d("fail ", "you've got it but with a big fail shitty" + t.message)
        }

        override fun onResponse(call: retrofit2.Call<ModelList>, response: Response<ModelList>) {
            if (response.code() == 200) {
                Log.d("sucess ", "you've got it")
                var modelList = response.body()
                if (modelList != null) modelList.getmodels()?.let { models.addAll(it) }

            }
        }
    })
    return models
}


    fun followModel(automobilistId:Int ,modelCode: String, service: RestService): FollowedModel {

        var followedModel: FollowedModel? = null
        service.followModel(modelCode,automobilistId).enqueue(object : Callback<FollowedModel> {
            override fun onResponse(call: Call<FollowedModel>, response: Response<FollowedModel>) {

                if (response.isSuccessful()) {
                    followedModel = response.body()!!
                    Log.d( "TFollowMarqueSuccess","post submitted to API." + response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<FollowedModel>, t: Throwable) {
                Log.d( "TFollowMarqueError","Unable to submit post to API.")
            }
        })

        return followedModel!!

    }

    fun unfollowModel(associationId:Int , service: RestService){
        service.UnfollowModel(associationId).enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                if (response.isSuccessful()) {

                    Log.d( "TFollowVersionSuccess","post submitted to API." + response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d( "TFollowVersionError","Unable to submit post to API.")
            }

        })
    }


    fun getFollowedModels(IdAutomobilist:Int,service: RestService):MutableList<FollowedModel>{

        var followdModels : MutableList<FollowedModel> = mutableListOf()
        service.getFollowedModels(IdAutomobilist).enqueue(object : Callback<MutableList<FollowedModel>> {
            override fun onFailure(call: Call<MutableList<FollowedModel>>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty" + t.message)
            }

            override fun onResponse(call: retrofit2.Call<MutableList<FollowedModel>>, response: Response<MutableList<FollowedModel>>) {
                if (response.code() == 200) {
                    Log.d("sucess ", "you've got it")
                    followdModels= response.body()!!

                }
            }
        })
        return followdModels
    }

}