package toniwar.projects.neurogallery.data

import okio.ByteString
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import toniwar.projects.neurogallery.domain.entities.NeuroRequest


interface NeuroService {

    @POST("kandinsky2.1-txt2img")

    suspend fun sendPrompt(@Body req: NeuroRequest): Response<String>

}