package dev.prince.cocktailapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.cocktailapp.data.Drink
import dev.prince.cocktailapp.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    private val _drinks = MutableStateFlow<List<Drink>>(emptyList())
    val drinks: StateFlow<List<Drink>> = _drinks

    fun searchDrinks(cockTailName: String) {
        viewModelScope.launch {
            try {
                val response = api.getCocktailByName(cockTailName.trim())
                _drinks.emit(response.drinks)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}