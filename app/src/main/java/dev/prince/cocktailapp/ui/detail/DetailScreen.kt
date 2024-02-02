package dev.prince.cocktailapp.ui.detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.prince.cocktailapp.data.Drink

@Composable
@Destination
fun DetailScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailViewModel = hiltViewModel(),
    drinkId: String
) {

    val drinkDetails by viewModel.drinkDetails.collectAsState()

    LaunchedEffect(drinkId) {
        viewModel.getDrinkDetailsById(drinkId)
    }


    Log.d("api-data", "response screen = $drinkDetails")

}