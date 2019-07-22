package com.example.sayaradz.Presenter

import com.example.sayaradz.Data.Ad
import com.example.sayaradz.Model.AdsModel
import com.example.sayaradz.Model.RestService


object AdsPresenter {
    fun showAds(numPage: Int, nbrEle: Int, service: RestService): MutableList<Ad> {
        return AdsModel.getAds(numPage = numPage, nbrEle = nbrEle, service = service)
    }

    fun getAdById(id:Int):Ad{
        return AdsModel.getAd(id)
    }

}