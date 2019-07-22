package com.example.sayaradz.Presenter

import android.graphics.ColorSpace
import com.example.sayaradz.Data.FollowedModel
import com.example.sayaradz.Data.Models
import com.example.sayaradz.Model.ModelM
import com.example.sayaradz.Model.RestService

object ModelPresenter {
    fun showModels(manu: Int, numPage: Int, nbrEle: Int, service: RestService): MutableList<Models> {
        return ModelM.getModels(manif = manu, numPage = numPage, nbrEle = nbrEle, service = service)
    }


    fun followModel(automobilistId: Int, modelCode: String, service: RestService): FollowedModel {
        return ModelM.followModel(automobilistId, modelCode, service = service)
    }

    fun unfollowModel(associationId: Int, service: RestService) {
        ModelM.unfollowModel(associationId, service)

    }

    fun getFollowedModels(Id:Int, service: RestService):MutableList<FollowedModel>{
        return ModelM.getFollowedModels(Id,service)
    }


}