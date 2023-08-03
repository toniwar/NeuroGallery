package toniwar.projects.neurogallery.domain

import kotlinx.coroutines.flow.Flow
import toniwar.projects.neurogallery.domain.entities.NetworkResult
import toniwar.projects.neurogallery.domain.entities.NeuroRequest

interface NeuroRepository {

    fun sendPrompt(prompt: NeuroRequest)

    fun getResult(): Flow<NetworkResult<Any?>>

}