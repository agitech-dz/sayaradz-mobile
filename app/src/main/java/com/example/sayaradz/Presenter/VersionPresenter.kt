package com.example.sayaradz.Presenter

import com.example.sayaradz.Data.FollowedModel
import com.example.sayaradz.Data.FollowedVersion
import com.example.sayaradz.Data.Version
import com.example.sayaradz.Model.RestService
import com.example.sayaradz.Model.VersionModel

object VersionPresenter {

    fun showVersion(model : String,numPage : Int, nbrEle : Int, service: RestService) :MutableList<Version>? {
        return VersionModel.getVersions( model=model, numPage =  numPage,nbrEle = nbrEle, service = service)
    }


    fun followVersion(automobilistId:Int ,versionCode: String, service: RestService): FollowedVersion{
        return VersionModel.followVersion(automobilistId,versionCode = versionCode,service = service)
    }

    fun unfollowVersion(associationId:Int , service: RestService){

      VersionModel.unfollowVersion(associationId,service)
    }

    fun getFollowedVersions(id: Int, service: RestService): MutableList<FollowedVersion> {
        return VersionModel.getFollowedVersions(id,service)

    }


}