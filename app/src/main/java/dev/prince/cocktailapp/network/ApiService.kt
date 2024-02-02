package dev.prince.cocktailapp.network

import dev.prince.cocktailapp.data.Drink
import dev.prince.cocktailapp.data.DrinksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun getCocktailByName(
        @Query(value = "s") cockTailName: String
    ): DrinksResponse

    @GET("lookup.php")
    suspend fun getDrinkById(
        @Query("i") id: String
    ): DrinksResponse

}