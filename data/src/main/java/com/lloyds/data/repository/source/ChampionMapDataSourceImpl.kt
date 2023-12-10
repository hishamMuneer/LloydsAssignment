package com.lloyds.data.repository.source

import com.lloyds.data.api.ChampionService
import com.lloyds.data.mapper.ChampionListApiToDomainMapper
import com.lloyds.data.repository.ApiResponseHandler
import com.lloyds.domain.model.ChampionMap
import com.lloyds.domain.shared.Result
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ChampionMapDataSourceImpl @Inject constructor(
    private val service: ChampionService,
    private val championListApiMapper: ChampionListApiToDomainMapper,
    private val apiResponseHandler: ApiResponseHandler
) : ChampionMapDataSource {
    override suspend fun getChampionMap(): Flow<Result<ChampionMap>> {
        return flow {
            try {
                val response = service.getChampionList()
                apiResponseHandler.handleApiResponse(response,
                    onSuccess = {
                        emit(Result.Success(championListApiMapper.map(it)))
                    }, onError = {
                        emit(Result.Error(IllegalArgumentException("response failed")))
                    }
                )
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}