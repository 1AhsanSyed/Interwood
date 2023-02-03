package com.autobar.interwood.ui.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.autobar.interwood.data.models.login.UserLogin
import com.google.gson.JsonObject
import com.ingenious.powergenerations.baseclasses.BaseViewModel
import com.ingenious.powergenerations.data.remote.Resource
import com.ingenious.powergenerations.data.remote.reporitory.MainRepository
import com.ingenious.powergenerations.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _loginResponse = MutableLiveData<Resource<UserLogin>>()
    val loginResponse: LiveData<Resource<UserLogin>>
        get() = _loginResponse

    fun userLogin(param : JsonObject) {
        viewModelScope.launch {
            _loginResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.userLogin(param) .let {
                        if (it.isSuccessful) {
                            _loginResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _loginResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _loginResponse.postValue(Resource.error("Some thing went wrong", null))
                        }
                    }
                } catch (e: Exception) {
                    _loginResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _loginResponse.postValue(Resource.error("No internet connection", null))
        }
    }
}