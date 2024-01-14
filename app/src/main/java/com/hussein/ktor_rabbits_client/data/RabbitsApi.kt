package com.hussein.ktor_rabbits_client.data

import retrofit2.http.GET

interface RabbitsApi {

    @GET("randomrabbit")
    suspend fun randomRabbits():Rabbit

    companion object{
         const val BASE_URL = "http://10.0.2.2:8100/"//http://10.0.2.2This is ip for emulator
    }
}