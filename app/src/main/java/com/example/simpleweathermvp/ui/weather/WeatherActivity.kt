package com.example.simpleweathermvp.ui.weather

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleweathermvp.R
import com.example.simpleweathermvp.domain.entity.City
import com.example.simpleweathermvp.extension.inject
import com.example.simpleweathermvp.ui.entity.WeatherUi
import kotlinx.android.synthetic.main.activity_weather_for_city.*
import org.koin.core.parameter.parametersOf

class WeatherActivity : AppCompatActivity(), WeatherView {

    val presenter: WeatherPresenter by inject {
        parametersOf(this as WeatherView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_for_city)
        val city = intent.extras.getParcelable("name") as City
        Log.d("123", city.name)

        presenter.weatherByCityRefresh(city)
        presenter.weatherByCityChenges(city)
    }

    override fun showWeather(weathers: List<WeatherUi>) {

        if(weathers.size > 0){
            val weather = weathers.get(0)

            ll_weather.visibility = View.VISIBLE

            description.text = weather.description
            tempToDay.text = weather.temp
            icon_today.setImageResource(weather.iconToday)


            tv_hpa.text = weather.pressure
            tv_humidity.text = weather.humidity
            tv_wind_speed.text = weather.windSpeed

            tv_one_day.text = weather.oneDay
            tv_temp_one_day.text = weather.tempOneDay
            ic_one_day.setImageResource(weather.iconOneDay)

            tv_two_day.text = weather.twoDay
            tv_temp_two_day.text = weather.tempTwoDay
            ic_two_day.setImageResource(weather.iconTwoDay)

            tv_three_day.text = weather.threeDay
            tv_temp_three_day.text = weather.tempThreeDay
            ic_three_day.setImageResource(weather.iconThreeDay)

            tv_four_day.text = weather.fourDay
            tv_temp_four_day.text = weather.tempFourDay
            ic_four_day.setImageResource(weather.iconFourDay)

            tv_five_day.text = weather.fiveDay
            tv_temp_five_day.text = weather.tempFiveDay
            ic_five_day.setImageResource(weather.iconFiveDay)

            tv_last_update.text = weather.lastUpdate

            tv_sunrise.text = weather.sunrise
            tv_sunset.text = weather.sunset

        } else{
            ll_weather.visibility = View.INVISIBLE

        }
    }
}