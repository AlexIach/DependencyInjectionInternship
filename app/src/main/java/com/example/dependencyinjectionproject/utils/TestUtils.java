package com.example.dependencyinjectionproject.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.example.dependencyinjectionproject.model.Car;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.annotation.TargetApi;
import android.os.Build;

public class TestUtils {


  @TargetApi(Build.VERSION_CODES.KITKAT)
  public static <T> T readJson(String path, Class<T> classOfT) {
    String content = readString(path);
    return new Gson().fromJson(content, classOfT);
  }

  public static <T> List<T> readJsonToList(String path) {
    String content = readString(path);
    Type listType = new TypeToken<List<Car>>() {
    }.getType();
    return new Gson().fromJson(content, listType);
  }

  public static String readString(String path) {
    URL resource = TestUtils.class.getClassLoader().getResource(path);
    File file = new File(resource.getPath());
    StringBuilder content = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
      String line;
      while ((line = br.readLine()) != null) {
        content.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content.toString();
  }
}
