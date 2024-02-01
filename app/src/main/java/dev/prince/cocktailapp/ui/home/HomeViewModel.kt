package dev.prince.cocktailapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.cocktailapp.data.CockTail
import dev.prince.cocktailapp.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    init {
        searchCocktails("Mojito")
    }

    private val _cocktails = MutableStateFlow<List<CockTail>>(emptyList())
    val cocktails: StateFlow<List<CockTail>> = _cocktails

    fun searchCocktails(query: String) {
        viewModelScope.launch {
            try {
                val response = api.getCocktailByName(query)
                _cocktails.emit(response.drinks)
                //Log.d("api-data","response = $response + $cocktails _cocktails = $_cocktails")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}