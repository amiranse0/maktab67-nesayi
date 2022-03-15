package com.example.problem1hw15

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.problem1hw15.databinding.SecondFragmentBinding

class SecondFragment : Fragment(R.layout.second_fragment) {

    private lateinit var binding: SecondFragmentBinding
    private lateinit var recyclerAdaptor: FavoriteRecyclerAdaptor
    lateinit var recyclerView: RecyclerView
    private val viewModel: MyViewModel by activityViewModels()
    private val listItems = mutableListOf<Item>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = SecondFragmentBinding.bind(view)

        showItems()

        clickBack()

        setUpItemTouchHelper()

    }

    private fun clickBack() {
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }

    private fun showItems() {
        recyclerView = binding.recyclerViewFavourite

        viewModel.getCities().observe(viewLifecycleOwner) {
            val list = it.filter { item -> item.isFav }
            Log.d("TAG", "size list: ${list.size}")
            listItems.clear()
            listItems.addAll(list)
            recyclerAdaptor.notifyDataSetChanged()
        }

        recyclerAdaptor = FavoriteRecyclerAdaptor(listItems)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdaptor
    }


    private fun setUpItemTouchHelper() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedPosition = viewHolder.adapterPosition
                    viewModel.clickFav(listItems[swipedPosition])

                    viewModel.getCities().observe(viewLifecycleOwner) {
                        val list = it.filter { item -> item.isFav }
                        Log.d("TAG", "size list: ${list.size}")
                        listItems.clear()
                        listItems.addAll(list)
                    }

                    recyclerAdaptor.notifyItemRemoved(swipedPosition)
                    recyclerAdaptor.notifyDataSetChanged()

                }
            }

        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(recyclerView)
    }
}