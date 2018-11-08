package com.example.dependencyinjectionproject.utils;

public final class StringUtils {

  public static boolean isStringNullOrEmpty(String value) {
    return value == null || value.length() == 0;
  }
}
