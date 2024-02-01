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

    private val _cockTails = MutableStateFlow<List<CockTail>>(emptyList())
    val cockTails: StateFlow<List<CockTail>> = _cockTails

    fun searchCocktails(cockTailName: String) {
        viewModelScope.launch {
            try {
                val response = api.getCocktailByName(cockTailName.trim())
                _cockTails.emit(response.drinks)
                //Log.d("api-data","response = $response + $cocktails _cocktails = $_cocktails")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}