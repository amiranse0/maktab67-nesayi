package com.example.taskmanager.ui

import AddDialogFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.ActivityHomeBinding
import com.example.taskmanager.ui.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.viewmodel.SharedViewModel

class HomeActivity : AppCompatActivity(){

    lateinit var viewPagerAdaptor: ViewPagerAdaptor
    lateinit var binding: ActivityHomeBinding

    private val viewModel:SharedViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((application as App).serviceLocator.repository)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        handleViewPager()

        addNewTaskDialog()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.search_menu){
            Toast.makeText(
                this,
                "this is search",
                Toast.LENGTH_SHORT
            ).show()
        }
        else Toast.makeText(
            this,
            "this is person",
            Toast.LENGTH_SHORT
        ).show()

        return true
    }

    private fun addNewTaskDialog() {
        binding.floatingActionButton.setOnClickListener {
            val addDialogFragment = AddDialogFragment()
            addDialogFragment.show(supportFragmentManager, "add")
            addDialogFragment.setGetTask(object : AddDialogFragment.GetTask{
                override fun getTask(task: Task) {
                    viewModel.addNewTask(task)
                }
            })
        }
    }

    private fun handleViewPager() {
        viewPagerAdaptor = ViewPagerAdaptor(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

        binding.tabLayout.setupWithViewPager(binding.viewPager)

        viewPagerAdaptor.addFragment(fragment = ToDoFragment(), "TODO")
        viewPagerAdaptor.addFragment(fragment = DoingFragment(), "DOING")
        viewPagerAdaptor.addFragment(fragment = DoneFramgment(), "Done")
        binding.viewPager.adapter = viewPagerAdaptor
    }
}