package dev.prince.cocktailapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.prince.cocktailapp.R
import dev.prince.cocktailapp.ui.theme.LightGray
import dev.prince.cocktailapp.ui.theme.LightOrange
import dev.prince.cocktailapp.ui.theme.poppinsFamily

@Composable
fun RestaurantCard() {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .background(Color.Unspecified)
            .height(110.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row {

            Image(
                painter = painterResource(
                    id = R.drawable.img_restaurant
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .background(LightGray)
                    .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 4.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightGray)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Blue Restaurant",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontFamily = poppinsFamily
                    ),
                    modifier = Modifier.padding(bottom = 2.dp)
                )

                Text(
                    text = "10:00 AM - 03:00 PM",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontFamily = poppinsFamily
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Steve ST Road",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = LightOrange,
                            fontFamily = poppinsFamily
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "4.5",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                                fontFamily = poppinsFamily
                            ),
                            modifier = Modifier.padding(end = 6.dp)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.icon_star),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun RestaurantCardPreview() {
    RestaurantCard()
}
