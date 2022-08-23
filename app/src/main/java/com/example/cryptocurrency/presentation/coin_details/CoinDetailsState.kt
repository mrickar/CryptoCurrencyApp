package com.example.cryptocurrency.presentation.coin_details

import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetails

data class CoinDetailsState(
    val isLoading: Boolean=false,
    val coin:CoinDetails? = null,
    val error:String=""
)