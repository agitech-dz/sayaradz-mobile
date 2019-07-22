package com.example.sayaradz.Data

class Version(
    val code: String,
    val name: String,
    val options: Array<String>,
    val model: String,
    val tarif_id: Int,
    val tarif_price: Int,
    val tarif_date_begin: String,
    val tarif_date_end: String,
    var followed:Boolean = false
)