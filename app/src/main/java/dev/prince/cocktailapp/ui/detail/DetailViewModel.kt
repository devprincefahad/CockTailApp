package dev.prince.cocktailapp.ui.detail

import android.util.Log
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
class DetailViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    private val _drinkDetails = MutableStateFlow<Drink?>(null)
    val drinkDetails: StateFlow<Drink?> = _drinkDetails

    fun getDrinkDetailsById(id: String) {
        viewModelScope.launch {
            try {
                val response = api.getDrinkById(id)
                val detail = response.drinks.firstOrNull()
                _drinkDetails.emit(detail)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}