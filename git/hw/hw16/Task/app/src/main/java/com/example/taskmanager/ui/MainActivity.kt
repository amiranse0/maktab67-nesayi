package com.example.taskmanager.ui

import AddDialogFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import com.example.taskmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewPagerAdaptor: ViewPagerAdaptor
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdaptor = ViewPagerAdaptor(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        handleViewPager()

        addNewTask()
    }

    private fun addNewTask() {
        binding.floatingActionButton.setOnClickListener {
            val addDialogFragment = AddDialogFragment()
            addDialogFragment.show(supportFragmentManager, "add")
        }
    }

    private fun handleViewPager() {
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        viewPagerAdaptor.addFragment(fragment = ToDoFragment(), "TODO")
        viewPagerAdaptor.addFragment(fragment = DoingFragment(), "DOING")
        viewPagerAdaptor.addFragment(fragment = DoneFramgment(), "Done")
        binding.viewPager.adapter = viewPagerAdaptor
    }
}