package dev.prince.cocktailapp.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.prince.cocktailapp.ui.theme.LightOrange
import dev.prince.cocktailapp.ui.theme.poppinsFamily

@Composable
fun DrinkInfoCard(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    info: String
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(
                color = LightOrange,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = info,
                modifier = modifier.size(24.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(
                text = info,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFamily
                )
            )
        }
    }
}