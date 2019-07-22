package com.example.sayaradz.Data

import com.google.gson.annotations.SerializedName

class AdList {
    @SerializedName("results")
    private val Ads: MutableList<Ad>? = mutableListOf()

    fun getVersion()
            : MutableList<Ad>?{
        return Ads
    }

}