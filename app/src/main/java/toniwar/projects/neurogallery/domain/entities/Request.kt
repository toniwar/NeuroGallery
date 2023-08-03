package toniwar.projects.neurogallery.domain.entities

import com.google.gson.annotations.SerializedName

data class NeuroRequest(
    @SerializedName("prompt")
    val prompt:String,
    @SerializedName("negative_prompt")
    val negativePrompt: String = "NONE",
    @SerializedName("samples")
    val samples: Int = 1,
    @SerializedName("scheduler")
    val scheduler: String =  "DDIM",
    @SerializedName("num_inference_steps")
    val steps: Int = 20,
    @SerializedName("guidance_scale")
    val guidanceScale: Float =  7.5f,
    @SerializedName("seed")
    val seed:Int = 1024,
    @SerializedName("img_width")
    val imgWidth: Int =512,
    @SerializedName("img_height")
    val imgHeight: Int = 512

)