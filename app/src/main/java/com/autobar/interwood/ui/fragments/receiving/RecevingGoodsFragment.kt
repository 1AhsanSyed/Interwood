package com.autobar.interwood.ui.fragments.receiving

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.autobar.interwood.R
import com.autobar.interwood.data.models.packingList.PackingListItem
import com.autobar.interwood.data.models.receiveGoods.Data
import com.autobar.interwood.databinding.FragmentRecevingGoodsBinding
import com.autobar.interwood.ui.customComponent.customTextView
import com.ingenious.powergenerations.data.local.db.AppDatabase
import com.ingenious.powergenerations.data.remote.Resource
import com.ingenious.powergenerations.utils.DialogHelperClass
import com.ingenious.powergenerations.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.*

@AndroidEntryPoint
class RecevingGoodsFragment : Fragment() {

    lateinit var binding: FragmentRecevingGoodsBinding
    protected lateinit var loadingDialog: Dialog
    var receivingList = ArrayList<Data>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecevingGoodsBinding.inflate(layoutInflater, container, false)

        val recivingFGViewModel: RecivingFGViewModel by viewModels()

        loadingDialog = DialogHelperClass.loadingDialog(requireContext())

        binding.jobNo.editText?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    val SplitString: Array<String> =
                        binding.jobNo.editText?.text.toString().split("*").toTypedArray()
                    val jobNo = SplitString[0]
                    val packingCode = SplitString[1]
                    val fGCode = SplitString[2]
                    val packetNo = SplitString[3]
                    val saleOrderNO = SplitString[4]

                    if (isValidate()) {


                        //check if database is not empty/null
                        recivingFGViewModel.isDataPresent()
                        recivingFGViewModel.isDataPresent.observe(requireActivity(), Observer {
                            if (it > 0) {

                                //if data present then, check is packet scanned or not
                                recivingFGViewModel.isAlreadyScanned(
                                    packetNo.toInt(),
                                    fGCode,
                                    packingCode
                                )
                                recivingFGViewModel.isAlreadyScanned.observe(
                                    requireActivity(),
                                    Observer {
                                        if (!it) {

                                            recivingFGViewModel.updateScannedList(
                                                packetNo.toInt(),
                                                fGCode,
                                                packingCode
                                            )
                                            recivingFGViewModel.receiveData.observe(requireActivity(),
                                                Observer {
                                                    if (it > 0) {
                                                        requireActivity().showToast("Updated")
                                                    } else {
                                                        requireActivity().showToast("Not updated")
                                                    }
                                                })


                                        }
                                        else {
                                            requireActivity().showToast("Already Scanned")
                                        }
                                    })

                            } else {
                                recivingFGViewModel.getJobDetails(jobNo.toInt())
                            }
                        })

                    }

                    return true
                }

                return false
            }

        })

        binding.composeView.setContent {


            Recyclerview()


        }


        getJobDetails(recivingFGViewModel)


        return binding.root

    }

    private fun getJobDetails(recivingFGViewModel: RecivingFGViewModel) {

        recivingFGViewModel.jobDetailResponse.observe(requireActivity(), Observer {

            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {
//                            requireActivity().showToast("Details against job no stored")
//                            val list = mutableStateListOf<com.autobar.interwood.data.models.packingList.PackingList>(
//                                it
//                            )
                            lifecycleScope.launch(Dispatchers.IO) {
                                async {
                                    AppDatabase.getDatabase(requireContext()).receiveDao()
                                        .storeJobData(it.data)
                                }.await()

                                Dispatchers.Main {
                                    requireActivity().showToast("Details against job no stored")
                                }
                            }

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

        if (binding.jobNo.editText?.text.toString().isEmpty()) {
            requireContext().showToast("Please Enter Job Number")
            return false
        }
        return true
    }


    @Composable
    fun Recyclerview() {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(colorResource(id = R.color.white)),
            verticalArrangement = Arrangement.Top
        )
        {


            RecyclerHeader()

            /* LazyColumn(Modifier.padding(0.dp, 5.dp)) {
                 val receiveList = remember {
                     mutableStateOf<List<Data>>(receivingList)
                 }

             }*/
        }

    }

    @Composable
    fun RecyclerHeader() {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = colorResource(id = R.color.white)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            customTextView(
                modifier = Modifier
                    .weight(0.25f)
                    .align(Alignment.CenterVertically),
                text = "Sno",
                color = R.color.colorPrimary,
                font = R.font.poppins_medium,
                24
            )

            customTextView(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "JOB NO",
                color = R.color.colorPrimary,
                font = R.font.poppins_medium,
                24
            )

            customTextView(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "FG NO",
                color = R.color.colorPrimary,
                font = R.font.poppins_medium,
                24
            )

            customTextView(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "PACKET NO",
                color = R.color.colorPrimary,
                font = R.font.poppins_medium,
                24
            )

            customTextView(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "Scanned",
                color = R.color.colorPrimary,
                font = R.font.poppins_medium,
                24
            )

        }
    }


    @Composable
    fun ItemView(sNO: Int) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = colorResource(id = R.color.white)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val sn = sNO + 1
            customTextView(
                modifier = Modifier
                    .weight(0.25f)
                    .align(Alignment.CenterVertically),
                text = "$sn",
                color = R.color.black,
                font = R.font.poppins_light,
                18
            )

            customTextView(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "JOB NO",
                color = R.color.black,
                font = R.font.poppins_light,
                18
            )

            customTextView(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "FG NO",
                color = R.color.black,
                font = R.font.poppins_light,
                18
            )

            customTextView(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "PACKET NO",
                color = R.color.black,
                font = R.font.poppins_light,
                18
            )

            customTextView(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "Scanned",
                color = R.color.black,
                font = R.font.poppins_light,
                18
            )

        }
    }


    @Preview
    @Composable
    fun MainPreview() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Recyclerview()

        }
    }

}