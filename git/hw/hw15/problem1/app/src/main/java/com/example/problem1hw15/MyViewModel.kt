package com.example.problem1hw15

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var cities = MutableLiveData(
        mutableListOf(
            Item("Los Angles", false, 0),
            Item("Ohio", false, 1),
            Item("NewYork", false, 2),
            Item("Miami", false, 3),
            Item("Chicago", false, 4),
            Item("Dallas", false, 5),
            Item("Phoenix", false, 6),
            Item("San Jose", false, 7),
            Item("San Diego", false, 8),
            Item("Las Vegas", false, 9)
        )
    )

    fun getCities() = cities

    fun clickFav(item: Item):Boolean?{
        val pos = item.code
        cities.value?.get(pos)?.isFav = cities.value?.get(pos)?.isFav == false

        Log.d("TAG", cities.value?.get(pos)?.isFav.toString())

        return cities.value?.get(pos)?.isFav
    }

}