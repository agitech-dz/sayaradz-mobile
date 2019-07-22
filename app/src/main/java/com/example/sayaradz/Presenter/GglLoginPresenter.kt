package com.example.sayaradz.Presenter

import com.example.sayaradz.Model.LoginModel

class GglLoginPresenter : Presenters {

    val model : LoginModel = LoginModel()

    fun LoginGgl( login: String, psw : String){

        if(login != null && psw != null ){
            model.GglLoginModel()
        }

    }

}