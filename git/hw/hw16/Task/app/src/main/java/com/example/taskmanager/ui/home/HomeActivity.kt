package com.example.taskmanager.ui.home

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.UserNameClass
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.ActivityHomeBinding
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.home.viewmodel.SharedViewModel
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var binding: ActivityHomeBinding

    var fragmentName = "TODO"

    private val viewModel: SharedViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((application as App).serviceLocator.repository)
    })

    private lateinit var recyclerAdaptor: MyRecyclerAdaptor

    private var searchResult = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        handleViewPager()

        addNewTaskDialog()

        setUserName()

        topMenu()

        clickProfile()

        search()

        setFragmentName()

        recyclerAdaptor = MyRecyclerAdaptor(searchResult)

        binding.homeRec.layoutManager = LinearLayoutManager(this)
        binding.homeRec.adapter = recyclerAdaptor
    }

    private fun setFragmentName() {
        viewModel.fragmentNameLiveData.observe(this){
            fragmentName = it
            binding.fragmentName = it
        }
    }

    private fun search() {
        val search = binding.homeToolBar.customAppBar.menu.findItem(R.id.search_menu)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        searchView.setOnCloseListener {
            binding.tabLayout.visibility = View.VISIBLE
            binding.viewPager.visibility = View.VISIBLE
            binding.homeRec.visibility = View.INVISIBLE
            true
        }

        searchView.setOnSearchClickListener {
            binding.tabLayout.visibility = View.INVISIBLE
            binding.viewPager.visibility = View.INVISIBLE
            binding.homeRec.visibility = View.VISIBLE
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) searchQuery(query)
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) searchQuery(query)
        return true
    }

    private fun searchQuery(query: String){
        val searchQuery = "%$query%"

        viewModel.searchQuery(searchQuery).observe(this){
            searchResult.clear()
            searchResult.addAll(it.filter { it1 -> it1.situationOfTask.toString() == fragmentName })
            recyclerAdaptor.notifyDataSetChanged()
        }
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
            Log.d("TAG", it)
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
        val viewPager = binding.viewPager
        val items = listOf(ToDoFragment(), DoingFragment(), DoneFragment())

        val adapter  = ViewPagerAdaptor(items, this)
        viewPager.adapter = adapter

        val tabs = binding.tabLayout
        TabLayoutMediator(tabs, viewPager){tab,pos->
            when(pos){
                0-> tab.text = "TODO"
                1-> tab.text = "DOING"
                2-> tab.text = "DONE"
            }
        }.attach()
    }
}