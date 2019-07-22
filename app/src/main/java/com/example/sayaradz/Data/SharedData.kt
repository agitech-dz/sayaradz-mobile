package com.example.sayaradz.Data

import android.content.Context
import com.example.sayaradz.Model.RestService
interface SharedData {

    fun getManufacturers(service: RestService): MutableList<Manufactors>
    fun getNewCars(): MutableList<NewCar>
    fun getAds(service: RestService): MutableList<Ad>
    fun getModels(manufacturerId:Int,service: RestService): MutableList<Models>
    fun getVersions(modelCode:String,service: RestService): MutableList<Version>
    fun getVersions(service: RestService): MutableList<Version>
    fun getAd(adId: Int): Ad
    fun followModel(automobilistId:Int, codeModel:String)
    fun unfollowModel(automobilistId:Int, codeModel:String)
    fun followVersion(automobilistId:Int, codeVersion:String)
    fun unfollowVersion(automobilistId:Int, codeVersion:String)
    fun getCurrentAutomobilst():Int
    fun getRetrofit() : RestService
}