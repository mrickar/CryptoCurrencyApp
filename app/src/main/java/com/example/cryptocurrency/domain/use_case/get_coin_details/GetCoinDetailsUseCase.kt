package com.example.cryptocurrency.domain.use_case.get_coin_details

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.data.remote.dto.toCoinDetails
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetails
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> {
        return return repository.getCoinById(coinId)
    }
}
//class GetCoinDetailsUseCase @Inject constructor(
//    private val repository: CoinRepository
//){
//    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow {
//        try {
//            emit(Resource.Loading<CoinDetails>())
//            val coin = repository.getCoinById(coinId).toCoinDetails()
//            emit(Resource.Success<CoinDetails>(coin))
//        } catch(e: HttpException) {
//            emit(Resource.Error<CoinDetails>(e.localizedMessage ?: "An unexpected error occured"))
//        } catch(e: IOException) {
//            emit(Resource.Error<CoinDetails>("Couldn't reach server. Check your internet connection."))
//        }
//    }
//}