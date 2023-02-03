package com.autobar.interwood.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.autobar.interwood.R
import com.autobar.interwood.data.models.Home
import com.autobar.interwood.databinding.FragmentHomeBinding
import com.autobar.interwood.ui.MainActivity
import com.autobar.interwood.ui.callbacks.OnItemClickListener
import com.ingenious.powergenerations.utils.showToast


class HomeFragment : Fragment(), OnItemClickListener {

    lateinit var binding : FragmentHomeBinding
    lateinit var homeAdapter: HomeAdapter
    var homeArrayList = ArrayList<Home>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        (activity as MainActivity).showHeader(true)
        (activity as MainActivity).setTitle("Interwood")

        setAdapter()



        return binding.root

    }

    private fun setAdapter() {
        homeArrayList.add(Home("Packing List",R.drawable.icon__packing_list))
        homeArrayList.add(Home("Send to \nWarehouse",R.drawable.icon__send_to_wearhouse))
        homeArrayList.add(Home("Good Receiving",R.drawable.icon__good_receiving))
        homeArrayList.add(Home("Put Away",R.drawable.icon__put_away))
        homeArrayList.add(Home("Picking List",R.drawable.icon__picking_list))
        homeArrayList.add(Home("Quality Check",R.drawable.icon__quality_check))
        homeArrayList.add(Home("Dispatch",R.drawable.icon__dispatch))
        homeArrayList.add(Home("Receiving from \nCustomer",R.drawable.icon__receiving_from_customer))
        homeArrayList.add(Home("Return",R.drawable.icon__return))

        val linearLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.layoutManager = linearLayoutManager

        homeAdapter = HomeAdapter(homeArrayList,this)
        binding.recyclerview.adapter = homeAdapter
    }

    override fun onItemClick(position: Int) {

        requireContext().showToast(homeArrayList.get(position).title)

        when (position){
            0-> {
                findNavController().navigate(R.id.packingListFragment)
            }
        }

    }


}