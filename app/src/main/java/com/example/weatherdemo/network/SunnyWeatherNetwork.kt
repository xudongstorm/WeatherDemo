package com.example.weatherdemo.network

import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)

    suspend fun searchPlace(query : String) = placeService.searchPlace(query).await()

    private suspend fun <T> retrofit2.Call<T>.await() : T{
        return suspendCoroutine {  continuation ->
            enqueue(object : retrofit2.Callback<T>{
                override fun onFailure(call: retrofit2.Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: retrofit2.Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body != null){
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

            })
        }
    }
}