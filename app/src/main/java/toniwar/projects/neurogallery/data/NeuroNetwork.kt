package toniwar.projects.neurogallery.data

import android.util.Log
import kotlinx.coroutines.delay
import toniwar.projects.neurogallery.domain.entities.NeuroRequest
import toniwar.projects.neurogallery.domain.entities.NetworkError
import toniwar.projects.neurogallery.domain.entities.NetworkException
import toniwar.projects.neurogallery.domain.entities.NetworkResult
import toniwar.projects.neurogallery.domain.entities.NetworkSuccess
import java.io.IOException


object NeuroNetwork {

    lateinit var request: NeuroRequest

    private val service = NetworkBuilder(NetworkBuilder.BASE_URL).service

    suspend fun sendPrompt(): NetworkResult<Any?> {


        val result = service.sendPrompt(request)
        delay(1000)
        val url = result.body()
        Log.d("ResBody", url!!)
        return try {
            if(result.isSuccessful) NetworkSuccess(url)
            else NetworkError(result.code(), result.message())
        }
        catch (e: IOException){
            NetworkException(e)
        }

    }


}