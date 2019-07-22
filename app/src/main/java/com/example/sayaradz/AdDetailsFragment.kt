package com.example.sayaradz

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.custom.sliderimage.logic.SliderImage
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.sayaradz.Data.*
import kotlinx.android.synthetic.main.fragment_ad_details.*


class AdDetailsFragment : Fragment() {

    private lateinit var data: SharedData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: com.example.sayaradz.databinding.FragmentAdDetailsBinding=
            DataBindingUtil.inflate(inflater, R.layout.fragment_ad_details, container, false)
        data = this.activity as SharedData
        var adId = AdDetailsFragmentArgs.fromBundle(arguments!!).adId
        setUpAdSliser(binding.root, data.getAd(adId))
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }

    //ManufacturerRecycleView--------------------------------------------
    private fun setUpAdSliser(rootView: View, ad: Ad) {

        val imageList = ArrayList<SlideModel>()

        // imageList.add(SlideModel("String Url" or R.drawable)
        // imageList.add(SlideModel("String Url" or R.drawable, "title") Also you can add title
        imageList.add(SlideModel("https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85"))
        imageList.add(SlideModel("https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85"))
        imageList.add(SlideModel("https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85"))
        imageList.add(SlideModel("https://imgd.aeplcdn.com/424x424/cw/ec/26916/Audi-Q3-Front-view-92293.jpg?v=201711021421&q=85"))
        val imageSlider = rootView.findViewById<ImageSlider>(R.id.ad_details_slider)
        imageSlider.setImageList(imageList)
    }





}


