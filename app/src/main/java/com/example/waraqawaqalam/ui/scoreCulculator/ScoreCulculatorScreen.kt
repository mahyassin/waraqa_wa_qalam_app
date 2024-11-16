package com.example.waraqawaqalam.ui.scoreCulculator

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waraqawaqalam.R
import com.example.waraqawaqalam.data.Girl
import com.example.waraqawaqalam.data.Kingdom
import com.example.waraqawaqalam.data.kingdoms
import com.example.waraqawaqalam.ui.home.AppViewModelProvider
import com.example.waraqawaqalam.ui.home.ScreenTopBar
import com.example.waraqawaqalam.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch


object ScoreCulculatorDestinatoin: NavigationDestination {
    override val route: String
        get() = "Score"
    override val title: String
        get() = "Score culculation"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreCulculatorScreen(
    vm: ScoreCulculatorVM = viewModel(factory = AppViewModelProvider.Factory),
    gotoDisplay: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val kingdom by vm.kingdom.collectAsState()
    val uiState by vm.uistate.collectAsState()

    var lotochArrow: ImageVector = Icons.Default.KeyboardArrowUp
    lotochArrow = if (!uiState.expanded) Icons.Default.KeyboardArrowDown
    else Icons.Default.KeyboardArrowUp

    var girlsArrow: ImageVector = Icons.Default.KeyboardArrowUp
    if (uiState.expanded) girlsArrow = Icons.Default.KeyboardArrowDown
    else girlsArrow = Icons.Default.KeyboardArrowUp

    Scaffold(
        topBar = {
            ScreenTopBar(
                scrollBehavior = null,
                canGoBack = true,
                navigateUp = {  },
                title = ScoreCulculatorDestinatoin.title
            )
        }
    ) { innerpadding ->

        Column(
            Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            LazyColumn(Modifier.weight(12f)){
                item {
                    Sliders(
                        lotochArrow = lotochArrow,
                        vm = vm,
                        uistate = uiState,
                        kingdom = kingdom
                    )
                    Column() {
                        SoawarsCollector(
                            girlsArrow = girlsArrow,
                            vm = vm,
                            uistate = uiState,
                            kingdom = kingdom
                        )
                    }
                }

            }

            val modifier = Modifier.weight(3f)
            if (!uiState.expanded) {val modifier = Modifier
            }else {modifier}
            BottomBar(modifier = modifier,kingdom.girls){
                coroutineScope.launch {
                    vm.scoreCulolator(kingdom)
                    kingdoms.add(kingdom)
                    vm.insertKingdom(kingdom)
                    vm.decideWinner()
                    gotoDisplay()
                }

            }
        }
    }
}
@Composable
fun SoawarsCollector(
    girlsArrow: ImageVector,
    vm: ScoreCulculatorVM,
    uistate: ScoreUistate,
    kingdom: Kingdom
) {
    ElevatedCard(Modifier.padding(8.dp)){
        Row(
            Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = girlsArrow,
                contentDescription = null,
                Modifier.clickable { vm.expand() }
            )
            Text("بنات")
        }
        if (!uistate.expanded) {
            GirlsList(
                kingdom,
                updateGirl = { index, p1ate ->
                    vm.updateGirl(index, p1ate)
                },
                doubled = { vm.doubled(it) }
            )
        }
    }
    GirlSelector(
        kingdom.girls[4],
        { vm.updateGirl(4,it ) },
        source = 4,
        doubled = { vm.doubled(4) },
        height = 200,
    )
}
@Composable
fun Sliders(
    lotochArrow: ImageVector,
    vm: ScoreCulculatorVM,
    uistate: ScoreUistate,
    kingdom: Kingdom
            ) {
    ElevatedCard(Modifier.padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = lotochArrow,
                    contentDescription = null,
                    Modifier.clickable { vm.expand() }
                )
                Text("اللطوش")
            }
            if(uistate.expanded) {
                LotuchSlider(
                    lutoch = kingdom.lutoch,
                    type = stringResource(R.string.lotoch_name)
                ) { vm.updateLutoch(it) }
                LotuchSlider(
                    lutoch = kingdom.dinari,
                    type = stringResource(R.string.dinari_name)
                ) { vm.updateDinari(it) }
            }
        }
    }
}
@Composable
fun BottomBar(modifier: Modifier = Modifier,girls: List<Girl?>,gotoDisplay: () -> Unit) {

   if (
       girls[0]?.p1Ate != null &&
       girls[1]?.p1Ate != null &&
       girls[2]?.p1Ate != null &&
       girls[3]?.p1Ate != null &&
       girls[4]?.p1Ate != null
       ) {
       Column(
           modifier = modifier,
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
       ) {
           Divider()
           Button(
               onClick = { gotoDisplay() },
               modifier = Modifier.padding(8.dp)
           ) {
               Text("Add Kingdom")
           }
       }
   }


}

@Composable
fun GirlsList(
    kingdom: Kingdom,
    updateGirl: (Int, Boolean) -> Unit,
    doubled: (Int) -> Unit,
) {
    kingdom.girls.forEachIndexed { index,girl ->
        if (index == 4) {}
        else {
            GirlSelector(
                girl, { updateGirl(index, it) },
                doubled = { doubled(index) },
                source = index,
                150,
            )
        }
    }
}


@Composable
fun GirlSelector(
    girl: Girl,
    girlEaten: (Boolean) -> Unit,
    doubled: () -> Unit,
    source: Int,
    height: Int
) {

    val offsetX: MutableState<Float> = remember { mutableFloatStateOf(0f) }
    val offestY: MutableState<Float> = remember { mutableFloatStateOf(0f) }
    val translationx = animateDpAsState(targetValue = offsetX.value.dp)
    val translationy = animateDpAsState(targetValue = offestY.value.dp)
    val imageSource = when (source) {
        0 -> R.drawable.queen_0
        1 -> R.drawable.queen_1
        2 -> R.drawable.queen_2
        3 -> R.drawable.queen_3
        else -> R.drawable.queen_4
    }

    when(girl.p1Ate) {
        true -> {
            offsetX.value = -100f
            offestY.value = 10f
        }
        false -> {
            offsetX.value = 100f
            offestY.value = 10f
        }

        null ->  {
            offsetX.value = 0f
            offestY.value = 0f
        }
    }
    Box(
        Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .height(height.dp)
                .fillMaxWidth(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            if (girl.doubled){
            Text(
                "مدبل",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xffff0000),
                modifier = Modifier.clickable{
                    doubled()
                }
            )
            } else {
                Text(
                    "مش مدبل",
                    fontSize = 20.sp,
                    color = Color(0xffcccccc),
                    modifier = Modifier.clickable{
                        doubled()
                    }
                )
            }
        }
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            Image(
                painter = painterResource(imageSource),
                contentDescription = null,
                modifier = Modifier
                    .height(height.dp)
                    .offset(x = translationx.value, y = translationy.value)
                    .pointerInput(Unit) {
                        detectDragGestures() { _, dragAmount ->

                            when {
                                dragAmount.x < 0 -> girlEaten(true)
                                dragAmount.x > 0 -> girlEaten(false)

                            }
                        }
                    },
            )
        }

    }
}
@Composable
fun LotuchSlider(
    lutoch: Float,
    type: String,
    updateLotuch: (Float) -> Unit,

) {

    OutlinedCard (
        Modifier
            .fillMaxWidth()
            .padding(8.dp)){
        Column(Modifier.padding(4.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                Arrangement.SpaceBetween
            ) {
                Text("${lutoch.toInt()}")
                Text(type)
                Text("${13 - lutoch.toInt()}")
            }
            Slider(
                value = lutoch,
                onValueChange = {
                    updateLotuch(it)
                },
                valueRange = 0f..13f,
                steps = 12
            )
        }
    }
}


@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(girls = listOf()){}
}

@Preview(showBackground = true)
@Composable
fun ScoreScreenPreview() {
    val vm: ScoreCulculatorVM = viewModel()
    val kingdom by vm.kingdom.collectAsState()
    Column(){
        ScoreCulculatorScreen(
            vm = vm,
            gotoDisplay = { }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun LotuchSliderPreview() {
    val vm: ScoreCulculatorVM = viewModel()
    val kingdom by vm.kingdom.collectAsState()
    LotuchSlider(
        lutoch = kingdom.lutoch,
          "عدد اللوطوش"
    ){vm.updateLutoch(it)}
}

