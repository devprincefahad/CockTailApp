package dev.prince.cocktailapp.ui.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.prince.cocktailapp.R
import dev.prince.cocktailapp.data.Drink
import dev.prince.cocktailapp.ui.composables.DrinkInfoCard
import dev.prince.cocktailapp.ui.theme.LightOrange
import dev.prince.cocktailapp.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = drinkDetails?.drinkName ?: "Details",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = LightOrange,
                            fontWeight = FontWeight.Bold,
                            fontFamily = poppinsFamily
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigator.navigateUp()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = {
            if (drinkDetails != null) {
                DetailContent(drinkDetails = drinkDetails!!)
            } else {
                CircularProgressIndicator(
                    color = LightOrange,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    )
}

@Composable
fun DetailContent(drinkDetails: Drink) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        AsyncImage(
            model = drinkDetails.drinkThumbnailImage ?: "",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
        ) {
            DrinkInfoCard(
                icon = if (drinkDetails.alcoholic.lowercase() == "alcoholic") R.drawable.wine_bottle else R.drawable.no_drinking,
                info = drinkDetails.alcoholic
            )
            drinkDetails.glass?.let {
                DrinkInfoCard(
                    icon = R.drawable.wine_glass,
                    info = it
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Instructions :-",
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFamily
            )
        )

        Text(
            text = drinkDetails.instructions ?: "",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFamily
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingredients :-",
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFamily
            )
        )

        val ingredients = drinkDetails.getIngredients()
        if (ingredients.isNotEmpty()) {
            for (ingredient in ingredients) {
                Text(
                    text = "${ingredient.name}: ${ingredient.measure}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppinsFamily
                    )
                )
            }
        }
    }
}