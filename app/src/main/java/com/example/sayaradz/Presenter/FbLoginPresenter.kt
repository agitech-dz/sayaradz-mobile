package com.example.sayaradz.Presenter

import com.example.sayaradz.Model.LoginModel

class FbLoginPresenter : Presenters {
     val model : LoginModel = LoginModel()

        fun LoginFb( login: String, psw : String){

        if(login != null && psw != null ){
            model.FbLoginModel()
        }

    }


}