package com.example.cryptocurrency.presentation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.model.CoinDetails
import com.example.cryptocurrency.domain.use_case.get_coin_details.GetCoinDetailsUseCase
import com.example.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptocurrency.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase,
    savedStateHandle: SavedStateHandle,
):ViewModel() {

    private val _state= mutableStateOf<CoinDetailsState>(CoinDetailsState())
    val state : State<CoinDetailsState> = _state

    init {
        viewModelScope.launch {
            savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId->
            getCoinDetails(coinId) }
        }
    }
    private suspend fun getCoinDetails(coinId:String)
    {
        getCoinDetailsUseCase(coinId).onEach {
            when(it)
            {
                is Resource.Success->
                {
                    _state.value= CoinDetailsState(coin = it.data)
                }
                is Resource.Error->
                {
                    _state.value= CoinDetailsState(error = it.message?:"ERROR")
                }
                is Resource.Loading->
                {
                    _state.value= CoinDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}