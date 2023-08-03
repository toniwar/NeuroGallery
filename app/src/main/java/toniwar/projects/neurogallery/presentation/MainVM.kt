package toniwar.projects.neurogallery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import toniwar.projects.neurogallery.data.NeuroRepositoryImpl
import toniwar.projects.neurogallery.domain.GetResultUseCase
import toniwar.projects.neurogallery.domain.SendPromptUseCase
import toniwar.projects.neurogallery.domain.entities.NetworkResult
import toniwar.projects.neurogallery.domain.entities.NeuroRequest

class MainVM: ViewModel() {

    private val repository = NeuroRepositoryImpl()
    private val getResultUseCase = GetResultUseCase(repository)
    private val sendPromptUseCase = SendPromptUseCase(repository)

    private val _state = MutableStateFlow<NetworkResult<Any?>?>(null)
    val state = _state.asStateFlow()


    fun sendPrompt(prompt: NeuroRequest){
        sendPromptUseCase.sendPrompt(prompt)

        getResult()
    }



    private fun getResult() {
        viewModelScope.launch {

            getResultUseCase.getResult().collect() {
               _state.value = it

            }
        }
    }

}