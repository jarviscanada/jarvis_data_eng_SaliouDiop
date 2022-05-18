package ca.jrvs.apps.twitter.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonUtil {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static String toJson(Object obj, boolean pretty, boolean includeNullValues) {
    try {
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      if (pretty) {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
      }
      if (includeNullValues) {
          mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      }
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T fromJson(String json, Class<T> clazz) {
    try {
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new IllegalArgumentException("JSON to Object conversion failed", e);
    }
  }
}
