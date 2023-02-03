package com.autobar.interwood.ui.fragments.login

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.autobar.interwood.R
import com.autobar.interwood.databinding.FragmentLoginBinding
import com.autobar.interwood.ui.MainActivity
import com.google.gson.JsonObject
import com.ingenious.powergenerations.data.remote.Resource
import com.ingenious.powergenerations.utils.DialogHelperClass
import com.ingenious.powergenerations.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private lateinit var loginBinding: FragmentLoginBinding
    lateinit var navController: NavController
    protected lateinit var loadingDialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)

        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        val loginViewModel : LoginViewModel by viewModels()
//        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)



        (activity as MainActivity?)!!.hideHeader()

        loadingDialog = DialogHelperClass.loadingDialog(requireContext())

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_Fragmnet)

        loginBinding.btnLogin.setOnClickListener {

            if (isValidate()) {

                val loginParams = JsonObject()
                loginParams.addProperty("Email", loginBinding.username.editText?.text.toString())
                loginParams.addProperty("Name", loginBinding.username.editText?.text.toString())
                loginParams.addProperty("Password", loginBinding.password.editText?.text.toString())

                loginViewModel.userLogin(loginParams)
            }



        }

        getResponse(loginViewModel)


        return loginBinding.root
    }

    private fun getResponse(loginViewModel: LoginViewModel) {

        loginViewModel.loginResponse.observe(requireActivity(), Observer {

            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {
                            requireContext().showToast(it.message)
                            findNavController().navigate(R.id.homeFragment)

                        }
                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }

        })
    }


    private fun isValidate(): Boolean {

        if (loginBinding.username.editText?.text.toString().isEmpty()) {
            requireContext().showToast("Please Enter username")
            return false
        }

        if (loginBinding.password.editText?.text.toString().isEmpty()) {
            requireContext().showToast("Please Enter Password")
            return false
        }

        return true
    }


}