package com.example.taskmanager.ui

import AddDialogFragment
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Secure.getString
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentPagerAdapter
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.UserNameClass
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.ActivityHomeBinding
import com.example.taskmanager.ui.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.viewmodel.SharedViewModel

class HomeActivity : AppCompatActivity() {

    lateinit var viewPagerAdaptor: ViewPagerAdaptor
    lateinit var binding: ActivityHomeBinding

    private val viewModel: SharedViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((application as App).serviceLocator.repository)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        handleViewPager()

        addNewTaskDialog()

        setUserName()

        topMenu()

        clickProfile()
    }

    private fun clickProfile() {
        binding.homeToolBar.customAppBar.menu.findItem(R.id.profile_menu)
            .setOnMenuItemClickListener {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.apply {
                    title = "Delete All Tasks"
                    setMessage("Are you Sure?")
                }

                alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        viewModel.getAllTask(UserNameClass.username).observe(this@HomeActivity) {
                            if (it != null) {
                                for (i in it) {
                                    viewModel.deleteTask(i)
                                }
                            }
                        }
                    }
                })

                alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })

                alertDialog.create()
                    .show()

                true
            }
    }

    private fun topMenu() {
        binding.username = UserNameClass.username
        viewModel.fragmentNameLiveData.observe(this) {
            binding.fragmentName = it
        }
    }

    private fun setUserName() {
        val userName = intent.getStringExtra("username")
        if (userName != null) {
            UserNameClass.username = userName
        }
    }


    private fun addNewTaskDialog() {
        binding.floatingActionButton.setOnClickListener {
            val addDialogFragment = AddDialogFragment()
            addDialogFragment.show(supportFragmentManager, "add")
            addDialogFragment.setGetTask(object : AddDialogFragment.GetTask {
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
        viewPagerAdaptor.addFragment(fragment = DoneFragment(), "Done")
        binding.viewPager.adapter = viewPagerAdaptor
    }
}