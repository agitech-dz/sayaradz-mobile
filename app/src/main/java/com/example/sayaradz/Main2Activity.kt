package com.example.sayaradz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.sayaradz.Data.*
import com.example.sayaradz.databinding.ActivityMain2Binding
import com.example.sayaradz.Model.RestService



class Main2Activity : AppCompatActivity(), SharedData {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var navController: NavController
    private val multiStartNavigationUi = MultiStartNavigationUI(
        listOf(
            R.id.homeFragment
        )
    )
    private var dataUtil = DataUtil()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        setSupportActionBar(binding.toolbar)

        navController = this.findNavController(R.id.nav_host_fragment)
        // setup NavController with actionBar & Drawer
        multiStartNavigationUi.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)


    }


    override fun onBackPressed() {
        if (!multiStartNavigationUi.onBackPressed(this, navController)) {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp() = multiStartNavigationUi.navigateUp(binding.drawerLayout, navController)

    override fun getManufacturers(service: RestService): MutableList<Manufactors> {
        return dataUtil.getManufacturers(service)
    }

    override fun getNewCars(): MutableList<NewCar> {
        return dataUtil.getNewCars()
    }

    override fun getAds(service: RestService): MutableList<Ad> {
        return dataUtil.getAds(service)
    }

    override fun getModels(manufacturerId: Int, service: RestService): MutableList<Models> {
        return dataUtil.getModels(manufacturerId, service)
    }

    override fun getVersions(service: RestService): MutableList<Version> {
        return dataUtil.getVersions(service)
    }

    override fun getVersions(modelCode: String, service: RestService): MutableList<Version> {
        return dataUtil.getVersions(modelCode, service)
    }

    override fun getAd(adId: Int): Ad {
        return dataUtil.getAd(adId)
    }

    override fun followModel(automobilistId: Int, codeModel: String) {
         dataUtil.followModel(automobilistId, codeModel)
    }

    override fun followVersion(automobilistId: Int, codeVersion: String) {
         dataUtil.followVersion(automobilistId, codeVersion)
    }

    override fun unfollowModel(automobilistId: Int, codeModel: String) {
         dataUtil.unfollowModel(automobilistId, codeModel)
    }

    override fun unfollowVersion(automobilistId: Int, codeVersion: String) {
         dataUtil.unfollowVersion(automobilistId, codeVersion)
    }

    override fun getCurrentAutomobilst():Int{
        return dataUtil.getCurrentAutomobilst()
    }

    override fun getRetrofit() : RestService {
        return dataUtil.getRetrofit(this).create(RestService::class.java)
    }
}
