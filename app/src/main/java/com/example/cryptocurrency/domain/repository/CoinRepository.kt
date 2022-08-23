package com.example.cryptocurrency.domain.repository

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetails
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetails>>
}