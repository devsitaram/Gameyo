package com.sitaram.gameyo.features.setting

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sitaram.gameyo.R
import java.text.DecimalFormat

@Composable
fun SettingViewScreen() {
    val showDialog = remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(5.dp),
            horizontalArrangement = Arrangement.End // gravity end
        ) {
            IconButton(onClick = { showDialog.value = true }) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Add Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.LightGray,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Calculation()
        }

        // if press the button the show the dialog box
        if (showDialog.value) {
            DialogBox(onDismiss = { showDialog.value = false })
        }
    }
}

// logout dialog box
@Composable
fun DialogBox(onDismiss: () -> Unit) {
    val activity = (LocalContext.current as Activity)
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(id = R.string.logout)) },
        text = { Text(text = "Are you sure you want to log out?") },
        modifier = Modifier.fillMaxWidth(),
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(
                onClick = { activity.finish() }
            ) {
                Text(text = "Yes")
            }
        }
    )
}

//@OptIn(ExperimentalPagerApi::class, ExperimentalPagerApi::class)
//@Composable
//fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
//    val scope = rememberCoroutineScope()
//    // or ScrollableToRow
//    TabRow(
//        selectedTabIndex = pagerState.currentPage,
//        Modifier.background(color = Color.DarkGray),
//        contentColor = Color.White,
////        indicator = { tabPositions ->
////            TabRowDefaults.Indicator(
//////                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
////            )
////        }
//    ) {
//        tabs.forEachIndexed { index, tabItem ->
//            // OR Tab()
//            LeadingIconTab(
//                icon = {
//                    Icon(
//                        painter = painterResource(id = tabItem.icon),
//                        contentDescription = ""
//                    )
//                },
//                text = { Text(tabItem.title) },
//                selected = pagerState.currentPage == index,
//                onClick = {
//                    scope.launch {
//                        pagerState.animateScrollToPage(index)
//                    }
//                },
//            )
//        }
//    }
//}

//@OptIn(ExperimentalFoundationApi::class)
//@ExperimentalPagerApi
//@Composable
//fun HorizontalPager(
//    state: PagerState,
//    modifier: Modifier = Modifier,
//    reverseLayout: Boolean = false,
//    itemSpacing: Dp = 0.dp,
//    dragEnabled: Boolean = true,
//    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
//    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
//    content: @Composable PagerScope.(page: Int) -> Unit,
//){}
//
//@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
//@Composable
//fun TabContent(pagerState: PagerState) {
//    HorizontalPager(state = pagerState) { index ->
//        when (index) {
//            0 -> {
//                MusicScreen()
//            }
//
//            1 -> {
//                MusicScreen()
//            }
//
//            2 -> {
//                BooksScreen()
//            }
//
//            3 -> {
//                NewsScreen()
//            }
//        }
//    }
//}

@Composable
fun MusicScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Music Screen")
    }
}

@Composable
fun MoviesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Movies Screen")
    }
}

@Composable
fun BooksScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Book Screen")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Calculation() {

    var buyingPrice by remember {
        mutableStateOf("")
    }

    var noOfShares by remember {
        mutableStateOf("")
    }

    var formattedShareAmount =  "0"
    var formattedSEBONCommission = "0"
    var formattedBrokerCommission = "0"
    val dpFee = 25.0
    var formattedTotalPayableAmount = "0"
    var formattedCostPericePerShare = "0"

    if (buyingPrice.isNotEmpty() && noOfShares.isNotEmpty()) {
        if (buyingPrice.isNotEmpty() && noOfShares.isNotEmpty()) {

            val shareAmount = buyingPrice.toDouble() * noOfShares.toDouble()
            val sebonCommission = ((shareAmount) * 0.015 / 100)
            val brokerCommission = (shareAmount * 0.40) / 100
            val totalPayableAmount = shareAmount + brokerCommission + dpFee
            val costPericePerShare = totalPayableAmount / noOfShares.toDouble()

            val decimalFormat = DecimalFormat("#.##")
            formattedShareAmount = decimalFormat.format(shareAmount)
            formattedSEBONCommission = decimalFormat.format(sebonCommission)
            formattedBrokerCommission = decimalFormat.format(brokerCommission)
            formattedTotalPayableAmount = decimalFormat.format(totalPayableAmount)
            formattedCostPericePerShare = decimalFormat.format(costPericePerShare)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            OutlinedTextField(
                value = buyingPrice,
                onValueChange = { buyingPrice = it },
                label = { Text(text = "Buying Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier
                    .width(350.dp)
                    .padding(5.dp),
            )

            OutlinedTextField(
                value = noOfShares,
                onValueChange = { noOfShares = it },
                label = { Text(text = "No of shares") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier
                    .width(350.dp)
                    .padding(5.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Text(text = "Share Amount")
                Spacer(modifier = Modifier.width(140.dp))
                Text(text = "Rs $formattedShareAmount", textAlign = TextAlign.End)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "SE-BON Commission")
                Spacer(modifier = Modifier.width(95.dp))
                Text(text = "Rs $formattedSEBONCommission")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Broker Commission (0.40%)")
                Spacer(modifier = Modifier.width(55.dp))
                Text(text = "Rs $formattedBrokerCommission")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "DP Fee")
                Spacer(modifier = Modifier.width(185.dp))
                Text(text = "Rs $dpFee")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                Text(text = "Total Payable Amount")
                Spacer(modifier = Modifier.width(90.dp))
                Text(text = "Rs $formattedTotalPayableAmount")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Cost Price Per Share")
                Spacer(modifier = Modifier.width(100.dp))
                Text(text = "Rs $formattedCostPericePerShare")
            }
        }
    }
}
