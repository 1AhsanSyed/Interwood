package com.autobar.interwood.ui.fragments.walkThrough

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.autobar.interwood.R
import com.autobar.interwood.databinding.FragmentSplashBinding
import com.autobar.interwood.ui.MainActivity


class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)

        (activity as MainActivity?)!!.hideHeader()

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.splash_animation)
        binding.interwoodImage.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code

            /* if (userToken.isNotEmpty()) {
               *//*  val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)*//*
            } else {
findNavController().popBackStack(R.id.splashFragment, true)
                    findNavController().navigate(R.id.homeFragment, null, options)
            }*/

            findNavController().popBackStack(R.id.splashFragment, true)
            findNavController().navigate(R.id.loginFragment, null)


        }, 3000)



        return binding.root

    }


}