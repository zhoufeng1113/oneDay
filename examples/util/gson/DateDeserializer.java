package com.homevip.util.gson;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.homevip.util.date.DateTimeUtil;

/**
 * date转换序列化
 * @author Administrator
 *
 */
public class DateDeserializer implements JsonDeserializer<Date>
{
  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)throws JsonParseException 
  {
    return DateTimeUtil.getDate(json.getAsJsonPrimitive().getAsString());
  }
}
