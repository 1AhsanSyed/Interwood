package com.autobar.interwood.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.autobar.interwood.R
import com.autobar.interwood.data.models.Home
import com.autobar.interwood.data.models.login.Data
import com.autobar.interwood.databinding.FragmentHomeBinding
import com.autobar.interwood.ui.MainActivity
import com.autobar.interwood.ui.callbacks.OnItemClickListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ingenious.powergenerations.utils.showToast
import timber.log.Timber


class HomeFragment : Fragment(), OnItemClickListener {

    lateinit var binding: FragmentHomeBinding
    lateinit var homeAdapter: HomeAdapter
    var homeArrayList = ArrayList<Home>()
    var  list = ArrayList<Data>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        (activity as MainActivity).showHeader(true)
        (activity as MainActivity).setTitle("Interwood")

        setAdapter()

       /* val bundle =arguments
        val wasi = bundle!!.getString("loginResult")
        val gson = Gson()
        list = gson.fromJson(wasi, object : TypeToken<List<Data>>() {}.type)

        requireActivity().showToast("List$list")
*/
        return binding.root

    }

    private fun setAdapter() {
        homeArrayList.clear()
        homeArrayList.add(Home("Packing List", R.drawable.icon__packing_list))
        homeArrayList.add(Home("Send to Warehouse", R.drawable.icon__send_to_wearhouse))
        homeArrayList.add(Home("Good Receiving", R.drawable.icon__good_receiving))
        homeArrayList.add(Home("Put Away", R.drawable.icon__put_away))
        homeArrayList.add(Home("Picking List", R.drawable.icon__picking_list))
        homeArrayList.add(Home("Quality Check", R.drawable.icon__quality_check))
        homeArrayList.add(Home("Dispatch", R.drawable.icon__dispatch))
        homeArrayList.add(Home("Receiving from Customer", R.drawable.icon__receiving_from_customer))
        homeArrayList.add(Home("Return", R.drawable.icon__return))

        val linearLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerview.layoutManager = linearLayoutManager

        homeAdapter = HomeAdapter(homeArrayList, this)
        binding.recyclerview.adapter = homeAdapter
    }

    override fun onItemClick(position: Int) {

        when (position) {

            0 -> {
                if (list.get(position).hasRight.equals(1)){
                    findNavController().navigate(R.id.packingListFragment)
                }else{
                    requireActivity().showToast("restricted")
                }
            }

            1 -> {
                if (list.get(position).hasRight.equals(1)){
                    findNavController().navigate(R.id.packingListFragment)
                }else{
                    requireActivity().showToast("restricted")
                }
            }

           2 -> {
                if (list.get(position).hasRight.equals(1)){
                    findNavController().navigate(R.id.packingListFragment)
                }else{
                    requireActivity().showToast("restricted")
                }
            }

            4 -> {
                if (list.get(position).hasRight.equals(1)){
                    findNavController().navigate(R.id.packingListFragment)
                }else{
                    requireActivity().showToast("restricted")
                }
            }

            5 -> {
                if (list.get(position).hasRight.equals(1)){
                    findNavController().navigate(R.id.packingListFragment)
                }else{
                    requireActivity().showToast("restricted")
                }
            }

            6 -> {
                if (list.get(position).hasRight.equals(1)){
                    findNavController().navigate(R.id.packingListFragment)
                }else{
                    requireActivity().showToast("restricted")
                }
            }

            7 -> {
                if (list.get(position).hasRight.equals(1)){
                    findNavController().navigate(R.id.packingListFragment)
                }else{
                    requireActivity().showToast("restricted")
                }
            }

            8 -> {
                if (list.get(position).hasRight.equals(1)){
                    findNavController().navigate(R.id.packingListFragment)
                }else{
                    requireActivity().showToast("restricted")
                }
            }
        }

    }


}