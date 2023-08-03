package toniwar.projects.neurogallery.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkBuilder(private val url: String) {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient().build())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()
    }

    private fun okHttpClient() = OkHttpClient.Builder().addInterceptor{
            chain ->
        HttpLoggingInterceptor.Level.BODY
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("x-api-key", TOKEN)
            .build()
        return@addInterceptor chain.proceed(request)
    }

    private val gsonBuilder = GsonBuilder().setLenient().create()

    val service: NeuroService by lazy { retrofit.create(NeuroService::class.java) }

    companion object{
        const val BASE_URL = "https://api.segmind.com/v1/"
        const val TOKEN = "SG_82193fa26b3be5d3"
    }



}