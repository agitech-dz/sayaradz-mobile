package com.example.sayaradz.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.sayaradz.R
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        setSupportActionBar(binding)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
