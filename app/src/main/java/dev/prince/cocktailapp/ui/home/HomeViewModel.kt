package dev.prince.cocktailapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.cocktailapp.data.Drink
import dev.prince.cocktailapp.network.ApiService
import dev.prince.cocktailapp.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    private val _drinks = MutableStateFlow<Resource<List<Drink>>>(Resource.Loading)
    val drinks: StateFlow<Resource<List<Drink>>> = _drinks

    private val searchDebounceTime = 300L
    private val searchQueryChannel = Channel<String>()

    init {
        observeSearchQueries()
    }

    fun searchDrinks(cockTailName: String) {
        viewModelScope.launch {
            searchQueryChannel.send(cockTailName)
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun observeSearchQueries() {
        viewModelScope.launch {
            searchQueryChannel
                .consumeAsFlow()
                .debounce(searchDebounceTime)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    flow {
                        try {
                            val response = api.getCocktailByName(query.trim())
                            emit(Resource.Success(response.drinks))
                        } catch (e: Exception) {
                            emit(Resource.Error(e.message ?: "An error occurred"))
                        }
                    }
                }
                .collect {
                    _drinks.value = it
                }
        }
    }
}