package dev.prince.cocktailapp.data

import com.google.gson.annotations.SerializedName

data class CockTailResponse(
    @SerializedName("drinks")
    val drinks: List<CockTail>
)

data class CockTail(
    @SerializedName("idDrink")
    val cocktailId: String,
    @SerializedName("strDrinkThumb")
    val image: String,
    @SerializedName("strDrink")
    val name: String,
    @SerializedName("strInstructions")
    val description: String,
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non_Alcoholic"
)