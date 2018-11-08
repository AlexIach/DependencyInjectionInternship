package com.example.dependencyinjectionproject.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeTypeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
  private final DateTimeFormatter formatter = ISODateTimeFormat.dateTime().withOffsetParsed();

  public DateTimeTypeConverter() {
  }

  public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
    return new JsonPrimitive(src.toString());
  }

  public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
    return this.formatter.parseDateTime(json.getAsString());
  }
}
