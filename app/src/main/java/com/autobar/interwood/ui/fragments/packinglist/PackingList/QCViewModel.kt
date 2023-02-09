package com.autobar.interwood.ui.fragments.packinglist.PackingList

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.autobar.interwood.data.DummyData.PackingList
import com.autobar.interwood.data.models.packingList.UpdateQC
import com.autobar.interwood.data.models.packingList.UpdateQCResult
import com.google.gson.JsonObject
import com.ingenious.powergenerations.baseclasses.BaseViewModel
import com.ingenious.powergenerations.data.remote.Resource
import com.ingenious.powergenerations.data.remote.reporitory.MainRepository
import com.ingenious.powergenerations.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QCViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {


    var packingList = mutableStateListOf<PackingList>(
        PackingList(1, "Back", 2),
        PackingList(2, "front", 6),
        PackingList(3, "sa", 6),
        PackingList(4, "sas", 3),
        PackingList(5, "dsads", 3),
        PackingList(5, "dsads", 3),
        PackingList(5, "dsads", 3),
        PackingList(5, "dsads", 3),
        PackingList(5, "dsads", 3),
        PackingList(5, "dsads", 3),
        PackingList(5, "dsads", 3),
    )


    private val _packingResponse = MutableLiveData<Resource<com.autobar.interwood.data.models.packingList.PackingList>>()
    val packingListLiveData: LiveData<Resource<com.autobar.interwood.data.models.packingList.PackingList>>
        get() = _packingResponse

    fun getPacketDetails(param: JsonObject) {
        viewModelScope.launch {
            _packingResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getPackingDetails(param).let {
                        if (it.isSuccessful) {
                            _packingResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _packingResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _packingResponse.postValue(Resource.error("Some thing went wrong", null))
                        }
                    }
                } catch (e: Exception) {
                    _packingResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _packingResponse.postValue(Resource.error("No internet connection", null))
        }
    }


    private val _updateQCList = MutableLiveData<Resource<UpdateQCResult>>()
    val updateQCList: LiveData<Resource<UpdateQCResult>>
        get() = _updateQCList


    fun updateQCList(list: UpdateQC){
        viewModelScope.launch {
            _updateQCList.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.updateQCList(list).let {
                        if (it.isSuccessful) {
                            _updateQCList.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _updateQCList.postValue(Resource.error(it.message(), null))
                        } else {
                            _updateQCList.postValue(Resource.error("Some thing went wrong", null))
                        }
                    }
                } catch (e: Exception) {
                    _updateQCList.postValue(Resource.error("${e.message}", null))
                }
            } else _updateQCList.postValue(Resource.error("No internet connection", null))
        }

    }





}