package com.autobar.interwood.ui.fragments.receiving

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.autobar.interwood.data.models.packingList.PackingList
import com.autobar.interwood.data.models.receiveGoods.ReceivedGoods
import com.google.gson.JsonObject
import com.ingenious.powergenerations.baseclasses.BaseViewModel
import com.ingenious.powergenerations.data.remote.Resource
import com.ingenious.powergenerations.data.remote.reporitory.MainRepository
import com.ingenious.powergenerations.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecivingFGViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
)  : BaseViewModel(){


    private val _jobDetailResponse = MutableLiveData<Resource<ReceivedGoods>>()
    val jobDetailResponse: LiveData<Resource<ReceivedGoods>>
        get() = _jobDetailResponse

     fun getJobDetails(jobNo : JsonObject){

        viewModelScope.launch {
            _jobDetailResponse.postValue( Resource.loading(null))

            if (networkHelper.isNetworkConnected()){
                try {
                    mainRepository.getJobDetails(jobNo).let {
                        if (it.isSuccessful){
                            _jobDetailResponse.postValue(Resource.success(it.body()!!))

                        }else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                        _jobDetailResponse.postValue(Resource.error(it.message(), null))
                    } else {
                        _jobDetailResponse.postValue(Resource.error("Some thing went wrong", null))
                    }
                    }
                }catch (e:Exception){
                    _jobDetailResponse.postValue(Resource.error("${e.message}", null))

                }
            }else{
                _jobDetailResponse.postValue(Resource.error("No internet connection", null))
            }

        }

    }

}