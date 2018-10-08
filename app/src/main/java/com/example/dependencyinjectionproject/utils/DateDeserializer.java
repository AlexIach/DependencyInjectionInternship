package com.example.dependencyinjectionproject.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateDeserializer implements JsonDeserializer<Date> {
  private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  private static final String TIME_ZONE = "UTC+02:00";

  @Override
  public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
    String date = element.getAsString();

    SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    formatter.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));

    try {
      return formatter.parse(date);
    } catch (ParseException e) {
      return null;
    }
  }
}