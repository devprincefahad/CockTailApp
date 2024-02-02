package dev.prince.cocktailapp.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.prince.cocktailapp.data.Drink
import dev.prince.cocktailapp.ui.destinations.DetailScreenDestination
import dev.prince.cocktailapp.ui.noRippleClickable
import dev.prince.cocktailapp.ui.theme.DarkGray
import dev.prince.cocktailapp.ui.theme.LightOrange
import dev.prince.cocktailapp.ui.theme.poppinsFamily

@Composable
fun CockTailItem(
    navigator: DestinationsNavigator,
    drink: Drink
) {

    Column(
        modifier = Modifier.padding(6.dp)
    ) {

        AsyncImage(
            model = drink.drinkThumbnailImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .size(54.dp)
                .clip(shape = RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = drink.drinkName,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = poppinsFamily
            )
        )

        Text(
            text = drink.categoryName,
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkGray,
                fontFamily = poppinsFamily
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = drink.alcoholic,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(1f),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightOrange,
                    fontFamily = poppinsFamily
                )
            )

            Text(
                modifier = Modifier
                    .border(
                        1.dp, LightOrange,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .noRippleClickable {
                        navigator.navigate(DetailScreenDestination(drink.drinkId))
                    },
                text = "View",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightOrange,
                    fontFamily = poppinsFamily
                )
            )
        }
    }
}

