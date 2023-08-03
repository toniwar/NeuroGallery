package toniwar.projects.neurogallery.domain

import toniwar.projects.neurogallery.domain.entities.NeuroRequest

class SendPromptUseCase(private val neuroRepository: NeuroRepository) {
    fun sendPrompt(prompt: NeuroRequest){
        neuroRepository.sendPrompt(prompt)
    }
}