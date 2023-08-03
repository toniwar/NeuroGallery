package toniwar.projects.neurogallery.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import toniwar.projects.neurogallery.domain.NeuroRepository
import toniwar.projects.neurogallery.domain.entities.NetworkResult
import toniwar.projects.neurogallery.domain.entities.NeuroRequest

class NeuroRepositoryImpl: NeuroRepository {

    override fun sendPrompt(prompt: NeuroRequest) {
        NeuroNetwork.request = prompt

    }

    override fun getResult(): Flow<NetworkResult<Any?>> {

        return flow {

            val result = NeuroNetwork.sendPrompt()
            emit(result)

        }

    }

}