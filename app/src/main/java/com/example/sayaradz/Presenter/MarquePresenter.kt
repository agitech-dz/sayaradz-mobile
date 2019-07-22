package com.example.sayaradz.Presenter

import com.example.sayaradz.Data.Manufactors
import com.example.sayaradz.Model.MarqueModel
import com.example.sayaradz.Model.RestService

object MarquePresenter{

    fun showManufacors(numPage: Int, nbrEle: Int, service: RestService):MutableList<Manufactors> {
        return MarqueModel.getManufactors( numPage = numPage, nbrEle = nbrEle, service = service)
    }
}