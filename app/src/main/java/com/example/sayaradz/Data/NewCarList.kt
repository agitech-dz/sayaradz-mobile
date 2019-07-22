package com.example.sayaradz.Data

import com.google.gson.annotations.SerializedName

class NewCarList {
    @SerializedName("results")
    private val NewCars: MutableList<NewCar>? = mutableListOf()

    fun getNewCars()
            : MutableList<NewCar>?{
        return NewCars
    }

}