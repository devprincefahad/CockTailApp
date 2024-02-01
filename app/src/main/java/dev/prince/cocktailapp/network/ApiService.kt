package dev.prince.cocktailapp.network

import dev.prince.cocktailapp.data.CockTailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun getCocktailByName(
        @Query(value = "s") cockTailName: String
    ): CockTailResponse

}