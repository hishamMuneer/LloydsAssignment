package com.lloyds.data.repository.source

import com.lloyds.data.api.ChampionService
import com.lloyds.data.mapper.ChampionDetailApiToDomainMapper
import com.lloyds.data.mapper.ChampionListApiToDomainMapper
import com.lloyds.domain.model.Champion
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.shared.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChampDataSourceImpl @Inject constructor(
    private val service: ChampionService,
    private val championListApiMapper: ChampionListApiToDomainMapper,
    private val championDetailApiMapper: ChampionDetailApiToDomainMapper
) : ChampDataSource {
    override suspend fun getChampionMap(): Flow<Result<ChampionMap>> {
        return flow {
            try {
                val response = service.getChampionList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Result.Success(championListApiMapper.map(it)))
                    }
                } else {
                    emit(Result.Error(IllegalArgumentException("response failed")))
                }
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getChampion(champion: String): Flow<Result<Champion>> {
        return flow {
            try {
                val response = service.getChampion(champion)
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