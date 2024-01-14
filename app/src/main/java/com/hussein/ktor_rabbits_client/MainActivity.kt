package com.hussein.ktor_rabbits_client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.hussein.ktor_rabbits_client.ui.theme.KtorrabbitsclientTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorrabbitsclientTheme {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)) {
                    val mainViewModel:MainViewModel = hiltViewModel()
                    val rabbit = mainViewModel.state.value.rabbit
                    val isLoading = mainViewModel.state.value.isLoading
                    rabbit?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = it.imageUrl,
                                builder = { crossfade(true) }),
                            contentDescription ="Rabbit"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it.description,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = mainViewModel::getRandomRabbit,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next Rabbit")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if(isLoading){
                        CircularProgressIndicator()
                    }

                }
            }
        }
    }
}