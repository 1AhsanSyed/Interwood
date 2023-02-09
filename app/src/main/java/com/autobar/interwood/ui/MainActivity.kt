package com.autobar.interwood.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import com.autobar.interwood.R
import com.autobar.interwood.databinding.ActivityMainBinding
import com.ingenious.powergenerations.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

    }

/*
    fun changeColorStatusBar(color: Int = R.color.white) {
        val window: Window = window
        val decorView = window.decorView
        val wic = WindowInsetsControllerCompat(window, decorView)
        wic.isAppearanceLightStatusBars = true
        // And then you can set any background color to the status bar.
        window.statusBarColor = ContextCompat.getColor(this, color)
    }
*/


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


    private val barcode = StringBuffer()

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {

        if (event?.action == KeyEvent.ACTION_DOWN) {
            val pressedKey = event.unicodeChar.toChar()
            barcode.append(pressedKey)
        }
        if (event?.action == KeyEvent.ACTION_DOWN && event?.keyCode == KeyEvent.KEYCODE_ENTER) {
            Toast.makeText(baseContext, barcode.toString(), Toast.LENGTH_SHORT).show()
            Log.e("barcode", barcode.toString()  )
            barcode.delete(0, barcode.length)
        }

        return super.dispatchKeyEvent(event)
    }




}