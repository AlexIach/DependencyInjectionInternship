package com.example.dependencyinjectionproject.utils;

import java.lang.reflect.Type;

import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import timber.log.Timber;


/**
 * Helper class for dealing with JSON parsing/serialization using {@link Gson} library
 */
public final class JsonUtil {

  private static final Gson GSON = new GsonBuilder()
    .registerTypeAdapter(DateTime.class, new DateTimeTypeConverter())
    .create();

  private JsonUtil() {
    throw new AssertionError("Util class should never be instantiated");
  }

  public static String toString(Object src) {
    Timber.e("toString");
    return GSON.toJson(src);
  }

  public static JsonObject toJson(Object src) {

    return (JsonObject) GSON.toJsonTree(src);
  }

  public static <T> T fromJson(JsonElement json, Class<T> tClass) {
    return GSON.fromJson(json, tClass);
  }

  public static <T> T fromJson(String json, Class<T> tClass) {
    Timber.e("fromJson");
    return GSON.fromJson(json, tClass);
  }

  public static Object fromJson(JsonElement jsonElement, Type type) {
    return GSON.fromJson(jsonElement, type);
  }
}
