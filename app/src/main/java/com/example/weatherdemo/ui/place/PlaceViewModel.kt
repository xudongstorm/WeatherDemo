package com.example.weatherdemo.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weatherdemo.model.Place
import com.example.weatherdemo.model.Repository

class PlaceViewModel : ViewModel(){

    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData){content ->
        Repository.searchPlace(content)
    }

    fun searchPlaces(content : String){
        searchLiveData.value = content
    }
}