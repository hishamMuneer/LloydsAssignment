package com.lloyds.data.repository.source

import com.lloyds.data.api.ChampionService
import com.lloyds.data.mapper.ChampionDetailApiToDomainMapper
import com.lloyds.domain.model.Champion
import com.lloyds.domain.shared.Result
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ChampionDetailsDataSourceImpl @Inject constructor(
    private val service: ChampionService,
    private val championDetailApiMapper: ChampionDetailApiToDomainMapper
) : ChampionDetailsDataSource {

    override suspend fun getChampion(id: String): Flow<Result<Champion>> {
        return flow {
            try {
                val response = service.getChampion(id)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Result.Success(championDetailApiMapper.map(it)))
                    }
                    Result
                } else {
                    emit(Result.Error(IllegalArgumentException("response failed")))
                }
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}