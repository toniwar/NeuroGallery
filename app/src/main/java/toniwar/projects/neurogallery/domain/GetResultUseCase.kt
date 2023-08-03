package toniwar.projects.neurogallery.domain

import kotlinx.coroutines.flow.Flow
import toniwar.projects.neurogallery.domain.entities.NetworkResult


class GetResultUseCase(private val neuroRepository: NeuroRepository) {
    fun getResult(): Flow<NetworkResult<Any?>> {
        return neuroRepository.getResult()
    }
}