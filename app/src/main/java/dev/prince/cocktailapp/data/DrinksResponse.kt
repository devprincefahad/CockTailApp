package dev.prince.cocktailapp.data

import com.google.gson.annotations.SerializedName

data class DrinksResponse(
    @SerializedName("drinks")
    val drinks: List<Drink> = emptyList()
)
