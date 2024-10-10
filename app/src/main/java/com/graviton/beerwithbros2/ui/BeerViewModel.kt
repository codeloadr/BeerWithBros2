package com.graviton.beerwithbros2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graviton.beerwithbros2.model.Beer
import com.graviton.beerwithbros2.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {
    private var _beersStateFlow = MutableStateFlow<List<Beer>>(emptyList())
    val beerStateFlow: StateFlow<List<Beer>> = _beersStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getBeers().collectLatest { beers ->
                _beersStateFlow.value = beers
            }
            onRefresh()
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            repository.fetchBeers()
        }
    }
}