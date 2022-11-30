package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    val currentDay = Calendar.DAY_OF_MONTH

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.weatherResp.observe(this) { weather ->
            binding.apply {
                tvCity.text = "Milan"
                tvDate.text = getDate()
                tvTemperature.text = weather.temperature
                tvDescription.text = weather.description
                tvWind.text = weather.wind

                val forecast1 = weather.forecast[0]
                val forecast2 = weather.forecast[1]
                val forecast3 = weather.forecast[2]

                tvForecast1.text = "${forecast1.temperature} .. ${forecast1.wind}"
                tvForecast2.text = "${forecast2.temperature} .. ${forecast2.wind}"
                tvForecast3.text = "${forecast3.temperature} .. ${forecast3.wind}"

                tvForecastDay1.text = "On ${currentDay+forecast1.day}"
                tvForecastDay2.text = "On ${currentDay+forecast2.day}"
                tvForecastDay3.text = "On ${currentDay+forecast3.day}"

            }
        }
    }

    fun getDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        return current.format(formatter)
    }
}