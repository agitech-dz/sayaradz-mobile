package com.example.sayaradz.View

import com.example.sayaradz.Presenter.FbLoginPresenter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.sayaradz.R
import kotlinx.android.synthetic.main.activity_fb.*

class FbActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fb)

        val fbPresenter : FbLoginPresenter =
            FbLoginPresenter()

        val Continue = findViewById(R.id.NextBtnFb) as Button

        Continue.setOnClickListener(){

                    fbPresenter.LoginFb(this.FbmailEdTxt.toString() ,this.PswFb.toString())
        }
    }

}




