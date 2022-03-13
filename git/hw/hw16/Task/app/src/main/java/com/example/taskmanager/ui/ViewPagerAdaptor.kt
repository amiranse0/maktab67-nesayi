package com.example.taskmanager.ui

import androidx.annotation.NonNull
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdaptor(@NonNull fragmentManager: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fragmentManager, behavior) {

    private final val listOfFragment = mutableListOf<Fragment>()
    private final val listOfTitle = mutableListOf<String>()

    override fun getCount(): Int {
        return listOfFragment.size
    }

    override fun getItem(position: Int): Fragment {
        return listOfFragment.get(position)
    }

    fun addFragment(fragment: Fragment, title:String){
        listOfFragment.add(fragment)
        listOfTitle.add(title)
    }

    @NonNull
    override fun getPageTitle(position: Int):CharSequence{
        return listOfTitle.get(position)
    }
}