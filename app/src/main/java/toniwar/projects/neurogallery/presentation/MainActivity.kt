package toniwar.projects.neurogallery.presentation


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toniwar.projects.neurogallery.data.entities.NeuroResponse
import toniwar.projects.neurogallery.databinding.ActivityMainBinding
import toniwar.projects.neurogallery.domain.entities.NetworkError
import toniwar.projects.neurogallery.domain.entities.NetworkException
import toniwar.projects.neurogallery.domain.entities.NetworkSuccess
import toniwar.projects.neurogallery.domain.entities.NeuroRequest


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val vm by lazy { ViewModelProvider(this)[MainVM::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.sendPromptButton.setOnClickListener{
            binding.progress.isVisible = true
            val text = binding.promptEt.text.toString()
            val prompt = NeuroRequest(prompt = text)
            vm.sendPrompt(prompt)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                vm.state.collect{ result->
                    result?.let {

                        when (result) {
                            is NetworkSuccess -> populateImage(result.networkData as String)

                            is NetworkError -> Toast.makeText(
                                this@MainActivity,
                                "${result.errorCode}: ${result.errorMessage}",
                                Toast.LENGTH_SHORT
                            ).show()

                            is NetworkException -> println(result.throwable.message)

                        }
                    }


                }
            }

        }

    }

    private  fun populateImage(data: String){
        binding.resultTextView.text = data.toString()



        Picasso.get()
            .load(data)
            .centerCrop()
            .fit()
            .into(binding.resultImage)
        binding.progress.isVisible = false
//        val glide = GlideApp.with(this)
//        CoroutineScope(Dispatchers.Main).launch {
//            glide
//                .load(data)
//                .override(Int.SIZE_BITS, Int.SIZE_BYTES)
//                .centerCrop()
//                .into(binding.resultImage)
//
//        }



    }
}