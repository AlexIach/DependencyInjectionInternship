package com.example.dependencyinjectionproject.di.modules;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.dependencyinjectionproject.repository.network.WeatherRestApi;
import com.example.dependencyinjectionproject.utils.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
  private final String BASE_URL_WEATHER = "http://api.openweathermap.org/data/2.5/forecast/";
  private final int READ_TIME_OUT = 30;

  @Provides
  @Singleton
  @Named("retrofit_weather")
  Retrofit provideRetrofitWithGson(Gson gson, OkHttpClient okHttpClient) {
    return getRetrofitBuilder(okHttpClient, gson)
      .baseUrl(BASE_URL_WEATHER)
      .build();
  }

  @Provides
  @Singleton
  Gson provideGson() {
    return new GsonBuilder()
      .registerTypeAdapter(Date.class, new DateDeserializer())
      .excludeFieldsWithoutExposeAnnotation()
      .create();
  }

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    Builder builder = new Builder();
    builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
    builder.addInterceptor(httpLoggingInterceptor);
    OkHttpClient okHttpClient = builder.build();
    return okHttpClient;
  }

  @Provides
  @Singleton
  WeatherRestApi provideWeatherRestApi(@Named("retrofit_weather") Retrofit retrofit) {
    return retrofit.create(WeatherRestApi.class);
  }

  private Retrofit.Builder getRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
    return new Retrofit.Builder()
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(gson));
  }

}
