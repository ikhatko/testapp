package com.ikhatko.testapp.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestUtil {

  private static ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  public static byte[] asByteArray(Object obj) {
    try {
      return mapper.writeValueAsBytes(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}