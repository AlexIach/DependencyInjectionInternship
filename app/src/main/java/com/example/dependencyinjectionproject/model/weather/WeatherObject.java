package com.example.dependencyinjectionproject.model.weather;

import java.util.List;

public class WeatherObject {
  private List<WeatherData> weather;
  private Temperature temp;
  private String dt;
  private String pressure;
  private String humidity;
  private String speed;
  private String deg;

  public List<WeatherData> getWeather() {
    return weather;
  }

  public void setWeather(List<WeatherData> weather) {
    this.weather = weather;
  }

  public String getDt() {
    return dt;
  }

  public void setDt(String dt) {
    this.dt = dt;
  }

  public String getPressure() {
    return pressure;
  }

  public void setPressure(String pressure) {
    this.pressure = pressure;
  }

  public String getHumidity() {
    return humidity;
  }

  public void setHumidity(String humidity) {
    this.humidity = humidity;
  }

  public String getSpeed() {
    return speed;
  }

  public void setSpeed(String speed) {
    this.speed = speed;
  }

  public String getDeg() {
    return deg;
  }

  public void setDeg(String deg) {
    this.deg = deg;
  }

  public Temperature getTemp() {
    return temp;
  }

  public void setTemp(Temperature temp) {
    this.temp = temp;
  }
}
