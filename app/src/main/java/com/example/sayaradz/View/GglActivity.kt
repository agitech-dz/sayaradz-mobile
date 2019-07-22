package com.example.sayaradz.View

import com.example.sayaradz.Presenter.GglLoginPresenter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sayaradz.R
import kotlinx.android.synthetic.main.activity_google_login.*

class GoogleLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)


        val GglPresenter : GglLoginPresenter =
            GglLoginPresenter()
        val Continue = findViewById(R.id.NextBtnGgl) as Button

        Continue.setOnClickListener(){

            GglPresenter.LoginGgl(this.GglmailEdTxt.toString() ,this.PswGgl.toString())
        }
    }
}

