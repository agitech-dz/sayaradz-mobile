package com.example.sayaradz.View

import com.example.sayaradz.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.Toast


class Recherche : AppCompatActivity() {

    /*val model = RechercheModel()
    val modelPres = RecherchePresenter(model)
    var nextName: Int = 0
    var level: Int = 0
    var list: ListView? = null
    var adapter: ArrayAdapter<String>? = null
    var numPage = 1
    var codeMarks  = Array(5){""}
    var listItems = Array(5){""}
   // var IdManuf : Array<Int?> = null!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d("valeur obtenus", "here")
        setContentView(R.layout.activity_recherche)

        list = findViewById(R.id.lstMark)
        val btn: Button = findViewById(R.id.btnSuiv)

        Log.d("valeur obtenus", "here")
        var (listItems,IdMan) = modelPres.showManufacors(numPage, 5)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        list!!.adapter = adapter

        list!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            nextName = IdMan.get(position)!!
            when (level) {
                0 -> upadateListViewWithModels(nextName)
               // 1 -> updateListeViewWithVersion(nextName)
                /*2 -> getTechSheet(nextName)*/
            }
            level++
            numPage = 1
            finish()
        }


        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                numPage++
                LancerPageSuivante(level, numPage, nextName)
            }

            })


    }


    fun getManifactors(page:Int){
        var (ManName,IdManuf) = modelPres.showManufacors(page, 5)
        ManName.copyInto(listItems)
        IdManuf.copyInto(IdManuf)
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems)
        list!!.adapter = adapter
    }

    fun getModels(page:Int, Id:Int){
        var (modelName,modelCode) = modelPres.showModels(Id, page, 5)
        modelName.copyInto(listItems)
        modelCode.copyInto(codeMarks)
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems)
        list!!.adapter = adapter
    }





    fun LancerPageSuivante(level: Int, page: Int, Id: Int) {

        when (level) {
            0 -> {
                var (ManName,IdManuf) = modelPres.showManufacors(page, 5)
                ManName.copyInto(listItems)
                IdManuf.copyInto(IdManuf)

            }
           1 -> {
               var (modelName,modelCode) = modelPres.showModels(Id, page, 5)
               modelName.copyInto(listItems)
               modelCode.copyInto(codeMarks)
            }
            /*2 -> {
                listItems = modelPres.showModels(Id, page, 5)
            }*/
        }
        if (!listItems.contentEquals(emptyArray())) {

            adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems)
            list!!.adapter = adapter
        } else {
            val toast =
                Toast.makeText(applicationContext, "pas d'avantage d'informations à afficher !", Toast.LENGTH_SHORT)
            toast.show()
            numPage--
        }

    }

    /*private fun getTechSheet(nextName: String) {
        var listItems = modelPres.showTechSheet(nextName)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        list!!.adapter = adapter
    }

    private fun updateListeViewWithVersion(nextName: Int) {
        var listItems = modelPres.showModels(nextName, 1, 5)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        list!!.adapter = adapter
    }*/

    private fun upadateListViewWithModels(markName: Int) {
        var (listItems,CodeModel) = modelPres.showModels(markName, 1, 5)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        list!!.adapter = adapter
    }*/
}
