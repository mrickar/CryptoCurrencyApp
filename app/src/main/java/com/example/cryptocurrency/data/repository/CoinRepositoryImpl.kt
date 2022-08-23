package com.example.cryptocurrency.data.repository
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
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

class CoinRepositoryImpl @Inject constructor(
    private val api:CoinPaprikaApi
): CoinRepository {

    override suspend fun getCoins(): Flow<Resource<List<Coin>>> {
        return flow {
            try {
                emit(Resource.Loading<List<Coin>>())
                val coins = api.getCoins().map { it.toCoin() }
                emit(Resource.Success<List<Coin>>(coins))
            } catch(e: HttpException) {
                emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetails>> {
        return flow {
            try {
                emit(Resource.Loading<CoinDetails>())
                val coin = api.getCoinById(coinId).toCoinDetails()
                emit(Resource.Success<CoinDetails>(coin))
            } catch(e: HttpException) {
                emit(Resource.Error<CoinDetails>(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                emit(Resource.Error<CoinDetails>("Couldn't reach server. Check your internet connection."))
            }
        }
    }
}