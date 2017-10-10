package com.example.weather;

import android.widget.TextView;

/**
 * Created by 怪蜀黍 on 2016/11/16.
 */

/**
 * 天气实体类
 */
public class Weather {
  //  private TextView day_city, day_temperature, day_wind, day_humidity, day_sunrise, advise;
    private String day_city;
    private double day_temperature;
    private Integer day_wind;
    private double day_humidity;
    private String advise;

    public String getDay_city() {
        return day_city;
    }

    public void setDay_city(String day_city) {
        this.day_city = day_city;
    }

    public double getDay_temperature() {
        return day_temperature;
    }

    public void setDay_temperature(double day_temperature) {
        this.day_temperature = day_temperature;
    }

    public Integer getDay_wind() {
        return day_wind;
    }

    public void setDay_wind(Integer day_wind) {
        this.day_wind = day_wind;
    }

    public double getDay_humidity() {
        return day_humidity;
    }

    public void setDay_humidity(double day_humidity) {
        this.day_humidity = day_humidity;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public Weather(String day_city, double day_temperature, Integer day_wind, double day_humidity, String advise) {
        this.day_city = day_city;
        this.day_temperature = day_temperature;
        this.day_wind = day_wind;
        this.day_humidity = day_humidity;
        this.advise = advise;
    }

    public Weather() {
    }
}
