package com.autobar.interwood.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.autobar.interwood.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }


    fun hideHeader() {
        binding.appHeader.visibility = View.GONE

    }

    fun showHeader(isHomeScreen : Boolean) {
        binding.appHeader.visibility = View.VISIBLE
        if (isHomeScreen){
            binding.btnBack.visibility = View.GONE
        }else{
            binding.btnBack.visibility = View.VISIBLE
        }
    }

    fun setTitle(title : String){
      binding.headerTitle.text = title
    }


}