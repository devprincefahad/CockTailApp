package dev.prince.cocktailapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.prince.cocktailapp.data.Drink
import dev.prince.cocktailapp.ui.composables.CockTailItem
import dev.prince.cocktailapp.ui.composables.RestaurantCard
import dev.prince.cocktailapp.ui.composables.SearchBar
import dev.prince.cocktailapp.util.Resource

@Composable
@RootNavGraph(start = true)
@Destination
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val resource by viewModel.drinks.collectAsState(initial = Resource.Loading)

    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Let's eat \nQuality food",
            color = Color.Black,
            style = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
        )

        SearchBar(
            value = search,
            onValueChange = {
                search = it
                viewModel.searchDrinks(it)
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Near Restaurant",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "See All",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        RestaurantCard()

        if (search.isNotBlank()) {
            when (resource) {
                is Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally))
                }
                is Resource.Success -> {
                    val drinks = (resource as Resource.Success<List<Drink>>).data
                    if (drinks != null && drinks.isNotEmpty()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(count = 2),
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            items(drinks) { cocktail ->
                                CockTailItem(
                                    navigator = navigator,
                                    drink = cocktail
                                )
                            }
                        }
                    } else {
                        Text(
                            text = "No drinks available",
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
                is Resource.Error -> {
                    Text(
                        text = "Error: ${(resource as Resource.Error).message}",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}