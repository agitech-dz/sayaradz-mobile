package com.example.sayaradz.View

import androidx.appcompat.app.AppCompatActivity


import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import com.example.sayaradz.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val nextGgl = findViewById(R.id.cnxBtn) as Button
        nextGgl.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val myIntent = Intent(getBaseContext(), GoogleLogin::class.java)
                startActivityForResult(myIntent, 0)
            }

        })


        val nextFb = findViewById(R.id.AnoBtn) as Button
        nextFb.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val myIntent = Intent(getBaseContext(), FbActivity::class.java)
                startActivityForResult(myIntent, 0)
            }

        })
            }


}
