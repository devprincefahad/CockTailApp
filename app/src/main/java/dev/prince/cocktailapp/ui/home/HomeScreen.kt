package dev.prince.cocktailapp.ui.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Composable
@RootNavGraph(start = true)
@Destination
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val data by viewModel.cocktails.collectAsState(initial = emptyList())
    Log.d("api-data", "data = $data")
}