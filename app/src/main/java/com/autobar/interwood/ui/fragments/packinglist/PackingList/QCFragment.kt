package com.autobar.interwood.ui.fragments.packinglist.PackingList

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.autobar.interwood.R
import com.autobar.interwood.data.models.packingList.PackingListItem
import com.autobar.interwood.data.models.packingList.UpdateQC
import com.autobar.interwood.databinding.FragmentQCBinding
import com.autobar.interwood.ui.MainActivity
import com.google.gson.JsonObject
import com.ingenious.powergenerations.data.remote.Resource
import com.ingenious.powergenerations.utils.DialogHelperClass
import com.ingenious.powergenerations.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QCFragment : Fragment() {

    lateinit var binding: FragmentQCBinding
    protected lateinit var loadingDialog: Dialog
    var updateList = ArrayList<PackingListItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_packing_list, container, false)

        binding = FragmentQCBinding.inflate(layoutInflater, container, false)

        (activity as MainActivity).showHeader(false)
        (activity as MainActivity).binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        (activity as MainActivity).setTitle("Quality Check")

        loadingDialog = DialogHelperClass.loadingDialog(requireContext())


        val qcViewModel : QCViewModel by viewModels()




        binding.scanQRCode.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event!!.action== KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER ){

                    val SplitString: Array<String> = binding.scanQRCode.text.toString().split("*").toTypedArray()
                    val jobNo = SplitString[0]
                    val packingCode = SplitString[1]
                    val fGCode = SplitString[2]
                    val packetNo = SplitString[3]
                    val saleOrderNO = SplitString[4]


                    binding.packetNo.setText(packetNo)
                    binding.packingCode.setText(packingCode)
                    binding.jobNo.setText(jobNo)
                    binding.itemDescription.setText(fGCode)

//                    binding.scanQRCode.text.clear()


                    if (isValidate()) {

                        val qrCodeParams = JsonObject()

                        qrCodeParams.addProperty("JobNo", jobNo)
                        qrCodeParams.addProperty("PackingCode", packingCode)
                        qrCodeParams.addProperty("FGCode",fGCode)
                        qrCodeParams.addProperty("PacketNo", packetNo)
                        qrCodeParams.addProperty("SaleOrderNo", saleOrderNO)

                        qcViewModel.getPacketDetails(qrCodeParams)
                    }

                    return true
                }

                return false
            }

        })


        binding.btnUpdateQC.setOnClickListener {

            val qcUpdate = UpdateQC(
                updateList
            )
            qcViewModel.updateQCList(qcUpdate)


        }



        getPackingListDetails(qcViewModel)
        getUpdateQCResultResponse(qcViewModel)


        return binding.root
    }

    private fun getUpdateQCResultResponse(viewModel: QCViewModel) {

        viewModel.updateQCList.observe(requireActivity(), Observer {

            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {

                            requireActivity().showToast(it.message)

                            clearFields()

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

    private fun clearFields() {

        binding.packetNo.setText("")
        binding.jobNo.setText("")
        binding.itemDescription.setText("")
        binding.packingCode.setText("")
    }

    private fun isValidate(): Boolean {

        if (binding.scanQRCode.text.toString().isEmpty()){
            requireActivity().showToast("Please scan QR CODE")
            return  false
        }
        return true
    }

    private fun getPackingListDetails(viewModel: QCViewModel) {

        viewModel.packingListLiveData.observe(requireActivity(), Observer {

            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {

//                            val list = mutableStateListOf<com.autobar.interwood.data.models.packingList.PackingList>(
//                                it
//                            )

                            binding.composeView.setContent {
                                val packingItem = remember {
                                    mutableStateOf<List<PackingListItem>>(it)
                                }
                                Recyclerview(packingItem)

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



    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Surface(Modifier.fillMaxSize()) {
//            MultiCheckBox()
//            Recyclerview(packingItem)
//            UserCard("")
        }
    }

    @Composable
    fun Recyclerview(packingItem: MutableState<List<PackingListItem>>) {
        val context = LocalContext.current

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.colorPrimary),
                    RoundedCornerShape(10.dp)
                )
                .background(color = colorResource(id = R.color.white)),
//        elevation = 14.dp,
//        shape = RoundedCornerShape(20.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier

                    .wrapContentHeight()
                    .background(color = colorResource(id = R.color.white))


            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

                {
                    RecyclerHeader()

                    LazyColumn(
                        Modifier.padding(0.dp, 10.dp)
                    ) {
                        items(packingItem.value.size) { index ->

                            ItemView(packingItem, index)

                            Divider(
                                color = colorResource(id = R.color.colorAccent),
                                thickness = 1.dp,
                                modifier = Modifier.padding(30.dp,5.dp)
                            )
                        }


                    }
                }


            }

        }


    }

    @Composable
    fun ItemView(packingItem: MutableState<List<PackingListItem>>, index: Int) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = colorResource(id = R.color.white))
                .clickable {
                    packingItem.value = packingItem.value
                        .toMutableList()
                        .also {
                            it[index] = it[index].copy(isSelected = !it[index].isSelected)

                            updateList.clear()
                            updateList.addAll(it)
                        }

//                    requireActivity().showToast( packingItem.value.get(index).isSelected.toString())
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier

                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                text = "${index+1}",
                color = colorResource(id = R.color.colorSecondaryVariant),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
//                letterSpacing = 0.5.sp,
                    textAlign = TextAlign.Center
                ),
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),

                text = packingItem.value.get(index).sItemName!!,
                color = colorResource(id = R.color.colorSecondaryVariant),

                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
//                letterSpacing = 0.5.sp
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),

                text = "${packingItem.value.get(index).packet}",
                color = colorResource(id = R.color.colorSecondaryVariant),

                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
//                letterSpacing = 0.5.sp
                    textAlign = TextAlign.Center
                )
            )


            Row(modifier = Modifier.weight(0.8f), horizontalArrangement = Arrangement.Center) {
                Box() {
                    Card(
                        shape = RoundedCornerShape(10.dp), modifier = Modifier
                            .background(
                                color = colorResource(
                                    id = R.color.white
                                )
                            )
                            .size(35.dp)
                            .align(alignment = Alignment.Center)
                            .border(
                                width = 2.dp,
                                color = colorResource(id = R.color.colorPrimary),
                                shape = RoundedCornerShape(10.dp)
                            )
                    ) {

                        if (packingItem.value.get(index).isSelected) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = Color.White),
                                painter = painterResource(id = R.drawable.icon_checked),
                                contentDescription = null
                            )
                        }


                    }
                }
            }


        }
    }

    @Composable
    fun RecyclerHeader() {
        Row(

            modifier = Modifier
                .wrapContentHeight()
                .background(color = colorResource(id = R.color.white)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(

                text = "S.no",
                color = colorResource(id = R.color.colorPrimary),
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
//                letterSpacing = 0.5.sp
                    textAlign = TextAlign.Center
                ),


                )
            Text(
                text = "Item", color = colorResource(id = R.color.colorPrimary),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
//                letterSpacing = 0.5.sp
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = "Packet",
                color = colorResource(id = R.color.colorPrimary),
                modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
//                letterSpacing = 0.5.sp
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = "Status",
                color = colorResource(id = R.color.colorPrimary),
                modifier = Modifier
                    .weight(0.8f)
                    .align(Alignment.CenterVertically),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
//                letterSpacing = 0.5.sp
                    textAlign = TextAlign.Center
                )
            )


        }
    }

}