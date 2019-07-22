package com.example.sayaradz.Model

import android.util.Log
import com.example.sayaradz.Data.FollowedVersion
import com.example.sayaradz.Data.Version
import com.example.sayaradz.Data.VersionsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object VersionModel {

    fun getVersions(model: String, numPage: Int, nbrEle: Int, service: RestService): MutableList<Version> {

        var Versions: MutableList<Version> = mutableListOf()

        val call = service.ListVersion(model, numPage, nbrEle)
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
                }
            }
        })
        return Versions
    }


    fun followVersion(automobilistId:Int, versionCode: String, service: RestService):FollowedVersion{

        var followVersion: FollowedVersion? = null
        service.followVersion(versionCode,automobilistId).enqueue(object : Callback<FollowedVersion> {
            override fun onResponse(call: Call<FollowedVersion>, response: Response<FollowedVersion>) {

                if (response.isSuccessful()) {
                    followVersion = response.body()!!
                    Log.d( "TFollowVersionSuccess","post submitted to API." + response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<FollowedVersion>, t: Throwable) {
                Log.d( "TFollowVersionError","Unable to submit post to API.")
            }
        })

        return followVersion!!

    }

    fun unfollowVersion(associationId:Int , service: RestService){
        service.UnfollowVersion(associationId).enqueue(object: Callback<Void> {
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

    fun getFollowedVersions(id: Int, service: RestService): MutableList<FollowedVersion> {

        var followdVersions : MutableList<FollowedVersion> = mutableListOf()
        service.getFolloweVersions(id).enqueue(object : Callback<MutableList<FollowedVersion>> {
            override fun onFailure(call: Call<MutableList<FollowedVersion>>, t: Throwable) {
                Log.d("fail ", "you've got it but with a big fail shitty" + t.message)
            }

            override fun onResponse(call: retrofit2.Call<MutableList<FollowedVersion>>, response: Response<MutableList<FollowedVersion>>) {
                if (response.code() == 200) {
                    Log.d("sucess ", "you've got it")
                    followdVersions= response.body()!!

                }
            }
        })
        return followdVersions

    }
}




