package com.example.unitarytests.feature.main.view

import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitarytests.feature.main.viewModel.MainViewModel
import com.example.unitarytests.ui.theme.UnitaryTestsTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }


    @Composable
    fun MyApp() {
        val viewModel: MainViewModel by viewModel()
        val list = viewModel.numberList.collectAsState().value
        val isFailure = viewModel.isFailure.collectAsState().value
        val errorMessage = viewModel.errorMessage.collectAsState().value

        UnitaryTestsTheme {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {

                val lazyGridState = rememberLazyGridState()
                LazyHorizontalGrid(
                    state = lazyGridState,
                    rows = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(300.dp)
                        .width(300.dp)
                        .padding(bottom = 50.dp)
                ) {
                    items(list){
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .background(color = Color.Gray)
                                .padding(0.dp)
                        ){
                            Text(text = "$it", textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
                Button(
                    onClick = { viewModel.append() },
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = (-200).dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 50.dp)
                ) {
                    Text("Add", color = Color.White)
                }
                Button(
                    onClick = { viewModel.remove() },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = (-130).dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 50.dp)
                ) {
                    Text("Remove", color = Color.White)
                }
                if (isFailure) {
                    Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}