package com.autobar.interwood.ui.fragments.packinglist.PackingList

import androidx.compose.runtime.mutableStateListOf
import com.autobar.interwood.data.DummyData.PackingList
import com.ingenious.powergenerations.baseclasses.BaseViewModel
import com.ingenious.powergenerations.data.remote.reporitory.MainRepository
import com.ingenious.powergenerations.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PackingViewModel  @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {


    val packingList = mutableStateListOf<PackingList>(
        PackingList(1, "Back", 2,  ),
        PackingList(2, "front", 6,  ),
        PackingList(3, "sa", 6,  ),
        PackingList(4, "sas", 3,  ),
        PackingList(5, "dsads", 3,  ),
    )


}