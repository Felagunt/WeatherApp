package com.example.weatherapp.api

import com.example.weatherapp.models.Weather
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("weather/Milan")
    suspend fun getWeather() : Response<Weather>

}