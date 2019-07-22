package com.example.sayaradz.Data


import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import com.example.sayaradz.Presenter.AdsPresenter
import com.example.sayaradz.Presenter.MarquePresenter
import com.example.sayaradz.Presenter.ModelPresenter
import com.example.sayaradz.Presenter.VersionPresenter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.example.sayaradz.Model.RestService
import okhttp3.Cache
import okhttp3.OkHttpClient

class DataUtil {


    var number: Int
        get() = this.number
        set(value) {
            this.number = value
        }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }


    fun getRetrofit(context: Context) : Retrofit {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://sayaradz-back-end.herokuapp.com/api/")
            .client(okHttpClient)
            .build()



    }


    fun getManufacturers(service: RestService): MutableList<Manufactors> {
        return MarquePresenter.showManufacors(1, 5, service)
    }

    fun getNewCars(): MutableList<NewCar> {

        return mutableListOf(
            NewCar("NumChassis", "Fer1024644", "seller", "890000DA"),
            NewCar("NumChassis", "Fer1024644", "seller", "890000DA"),
            NewCar("NumChassis", "Fer1024644", "seller", "890000DA"),
            NewCar("NumChassis", "Fer1024644", "seller", "890000DA"),
            NewCar("NumChassis", "Fer1024644", "seller", "890000DA")
        )
    }

    fun getAds(service: RestService): MutableList<Ad> {
        //return AdList().getmodels()*
        return AdsPresenter.showAds(1, 10, service)

    }

    fun getModels(manufacturerId: Int, service: RestService): MutableList<Models> {
        //return AdList().getmodels()*
        return ModelPresenter.showModels(manufacturerId, 1, 10, service)
    }

    fun getVersions(modelCode: String, service: RestService): MutableList<Version> {
        return VersionPresenter.showVersion(modelCode, 1, 10, service)!!
    }

    fun getVersions(service: RestService): MutableList<Version> {
        return VersionPresenter.showVersion("all", 1, 10, service)!!

    }


    fun followModel(automobilistId: Int, modelCode: String, service: RestService): FollowedModel {
        return ModelPresenter.followModel(automobilistId, modelCode, service)
    }

    fun unfollowModel(associationId: Int, service: RestService) {
        ModelPresenter.unfollowModel(associationId, service)

    }

    fun followVersion(automobilistId: Int, versionCode: String, service: RestService): FollowedVersion {
        return VersionPresenter.followVersion(automobilistId, versionCode, service)
    }

    fun unfollowVersion(associationId: Int, service: RestService) {
        VersionPresenter.unfollowVersion(associationId, service)
    }

    fun getCurrentAutomobilst(): Int {
        return 1
    }//automobiliste à priori

    fun getAd(adId: Int): Ad {
      //  return Ad(1, "Ferari", "Model", "version", "900000DA", "owner", 13000, 2009)
        return AdsPresenter.getAdById(adId)

    }

    fun getFollowedModels(Id: Int, service: RestService ):MutableList<FollowedModel>{
         return ModelPresenter.getFollowedModels(Id,service)
    }

    fun getFollowedVersions(Id: Int, service: RestService ):MutableList<FollowedVersion>{
        return VersionPresenter.getFollowedVersions(Id,service)
    }


}