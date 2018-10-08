package com.example.dependencyinjectionproject.model.weather;

import java.util.List;

import com.google.gson.annotations.Expose;

public class WeatherList {
  @Expose
  private List<WeatherObject> list;

  public List<WeatherObject> getList() {
    return list;
  }

  public void setList(List<WeatherObject> list) {
    this.list = list;
  }
}
