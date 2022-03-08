package com.example.problem1hw15

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var cities = MutableLiveData(
        mutableListOf(
            Item("Los Angles", false),
            Item("Ohio", false),
            Item("NewYork", false),
            Item("Miami", false),
            Item("Chicago", false),
            Item("Dallas", false),
            Item("Phoenix", false),
            Item("San Jose", false),
            Item("San Diego", false),
            Item("Las Vegas", false)
        )
    )

    fun getCities() = cities

    private var favoriteCities = MutableLiveData<MutableList<Item>>()

    private fun updateListFav(){
        var listFav = mutableListOf<Item>()
        for (i in cities.value!!){
            if (i.isFav) listFav.add(i)
        }
        favoriteCities.value?.clear()
        favoriteCities.value?.addAll(listFav)
    }

    fun clickFav(pos:Int):Boolean?{
        cities.value?.get(pos)?.isFav = cities.value?.get(pos)?.isFav == false
        updateListFav()
        return cities.value?.get(pos)?.isFav
    }
}