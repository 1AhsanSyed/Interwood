package com.autobar.interwood.ui.fragments.packinglist.PackingList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.navigation.fragment.findNavController
import com.autobar.interwood.databinding.FragmentPackingListBinding
import com.autobar.interwood.ui.MainActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.autobar.interwood.R
import com.autobar.interwood.data.DummyData.PackingList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PackingListFragment : Fragment() {

    lateinit var binding: FragmentPackingListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_packing_list, container, false)

        binding = FragmentPackingListBinding.inflate(layoutInflater, container, false)

        (activity as MainActivity).showHeader(false)
        (activity as MainActivity).binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        (activity as MainActivity).setTitle("Packing List")

        binding.composeView.setContent {
            val viewModel: PackingViewModel = viewModel()

val packingList = remember {
    viewModel.packingList
}
//            UserCard("hello Every one")
            Recyclerview(packingList, viewModel)
        }

        return binding.root
    }



    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Surface(Modifier.fillMaxSize()) {
//            Recyclerview(packingList)
//            UserCard("")
        }
    }

    @Composable
    fun Recyclerview(
        model: SnapshotStateList<PackingList>,
        viewModel: PackingViewModel
    ) {
        val context = LocalContext.current

        val mutableState = remember {
            mutableStateListOf(model)
        }

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

                    LazyColumn(Modifier.padding(0.dp, 10.dp),
                    state = rememberLazyListState()) {
                        itemsIndexed(model) { index, item ->
                            ItemView(
                                model.get(index).sno,
                                model.get(index).item,
                                model.get(index).packet,
                                model.get(index).isSelected.value
                            ){
                                viewModel.packingList[index].isSelected = mutableStateOf(it)
                            }

                            Divider(
                                color = colorResource(id = R.color.colorSecondaryVariant),
                                thickness = 1.dp,
                                modifier = Modifier.padding(horizontal = 10.dp, 4.dp)
                            )


                            /*  Box(
                                  modifier = Modifier
                                      .fillMaxWidth()
                                      .height(1.dp)
                                      .background(color = colorResource(id = R.color.colorSecondaryVariant))
                              )*/
                        }
                    }
                }


            }

        }


    }

    @Composable
    fun ItemView(sno: Int, item: String, packet: Int, isStatusSelected: Boolean, onStatusChange:(Boolean)->Unit) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .background(color = colorResource(id = R.color.white)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier

                    .weight(1f)
                    .align(Alignment.CenterVertically),
                text = "$sno",
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

                text = item, color = colorResource(id = R.color.colorSecondaryVariant),

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
                    .weight(1f)
                    .align(Alignment.CenterVertically),

                text = "$packet",
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

            CustomCheckBox(modifier = Modifier.weight(1f), isChecked = isStatusSelected){
onStatusChange(it)
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
                    .weight(1f)
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
                text = "Status",
                color = colorResource(id = R.color.colorPrimary),
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


        }
    }

    @Composable
    fun CustomCheckBox(modifier: Modifier = Modifier,
    isChecked:Boolean = false, onCheckChange:(Boolean)->Unit) {


       Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
           Box() {
               Card(
                   shape = RoundedCornerShape(10.dp), modifier = modifier
                       .background(
                           color = colorResource(
                               id = R.color.white
                           )
                       )
                       .size(30.dp)
                       .align(alignment = Alignment.Center)
                       .border(
                           width = 2.dp,
                           color = colorResource(id = R.color.colorPrimary),
                           shape = RoundedCornerShape(10.dp)
                       )
                       .clickable {
                           onCheckChange(!isChecked)
                       }) {

                   if (isChecked) {
                       Image(
                           modifier = modifier
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

