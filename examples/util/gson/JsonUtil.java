package com.homevip.util.gson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.JsonParser;
import org.apache.commons.lang.StringUtils;

import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;

/**
 * json数据转换类
 * 
 * @author 卢斌晖
 */
public class JsonUtil 
{
	private static GsonBuilder gsonBuilder;
	private static Gson gson;

	static
	{
		gsonBuilder=new GsonBuilder();
		gsonBuilder.enableComplexMapKeySerialization();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
		gsonBuilder.registerTypeAdapter(Map.class, new MapSerializer());
		gson=gsonBuilder.create();
	}
	/** 获取gson对象 **/
	public static Gson getGson(){
		return gson;
	}

	public static String toJson(Object object)
	{
		return gson.toJson(object);
	}

	public static String toJson(Object[] arr)
	{
		return gson.toJson(arr);
	}

	public static String toJson(char[] arr)
	{
		return gson.toJson(arr);
	}

	public static String toJson(String[] arr)
	{
		return gson.toJson(arr);
	}

	public static String toJson(short[] arr)
	{
		return gson.toJson(arr);
	}

	public static String toJson(int[] arr)
	{
		return gson.toJson(arr);
	}

	public static String toJson(long[] arr)
	{
		return gson.toJson(arr);
	}

	public static String toJson(float[] arr)
	{
		return gson.toJson(arr);
	}

	public static String toJson(double[] arr)
	{
		return gson.toJson(arr);
	}

	/*public static Object fromJson(String jsonString,Class<?> _class)
	{
		return gson.fromJson(jsonString,_class);
	}*/
	
	public static Object fromJson(String jsonString, Class<?> _class) {
		ObjectMapper mapper = new ObjectMapper();
//		JSONObject fromObject = JSONObject.fromObject(jsonString);
//		String json = mapper.writeValueAsString(user);
		Object readValue = null;
		String trim = jsonString.trim();
		try {
			readValue = mapper.readValue(trim, _class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readValue;
	}

	/**
	 * 将Json格式的字符串转换成指定对象组成的List返回
	 * <br>例如：List<"String"> list = json2List("……", new TypeToken<"List<"String">">(){});
	 * <br>     List<"Map<"Integer, Object">"> maplist = json2List("……", new TypeToken<"List<"Map<"Integer, Object">">">(){});
	 * @param <T>   泛型标识
	 * @param jsonString    JSON数据格式字符串
	 * @param typeToken     目标类型器，标识需要转换成的目标List对象
	 * @return
	 */
	public static <T> List<T> fromJson(String jsonString, TypeToken<List<T>> typeToken){
		Type type = typeToken.getType(); 
		return gson.fromJson(jsonString, type); 
	}


	/**
	 * BaseProto数据转JsonObject
	 * @param data
	 * @return
	 */
	public static <T> JsonObject toJsonObject(T data) {
		JsonObject jo = new JsonObject();
		if (null != data){
			try {
				Field[] fs = data.getClass().getDeclaredFields();
				for (Field f : fs) {
					try {
						Method getMethod = data.getClass().getDeclaredMethod("get"+toFirstUpper(f.getName()));
						Object val = getMethod.invoke(data);
						if(val!=null){
							if(getMethod.getReturnType()==int.class&&val!=null){
								jo.addProperty(f.getName(), (Integer)val);
							}else if(getMethod.getReturnType()==double.class&&val!=null){
								jo.addProperty(f.getName(), (Double)val);
							}else if(val.getClass().getAnnotation(Entity.class)!=null){
								jo.add(f.getName(), toJsonObject(val));
							}else{
								jo.addProperty(f.getName(), (val+"").replaceAll("\\$", "＄"));
							}
						}else{
							jo.addProperty(f.getName(), "");
						}
					} catch (Exception e) {}
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jo;
	}

	/**
	 * List列表数据转JsonArray
	 * @param datas
	 * @return
	 */
	public static <T> JsonArray toJsonArray(List<T> datas) {
		JsonArray ja = new JsonArray();
		if (null != datas){
			for (T data : datas) {
				ja.add(toJsonObject(data));
			}
		}
		return ja;
	}

	private static String toFirstUpper(String str){
		if(!"".equals(StringUtils.defaultString(str))){
			String f = str.substring(0, 1);
			str = f.toUpperCase()+str.substring(1);
		}
		return str;
	}

	/**
	 * 验证一个字符串是否是合法的JSON串
	 *
	 * @param input 要验证的字符串
	 * @return true-合法 ，false-非法
	 */
	public static boolean validate(String input) {
		try{
			input = input.trim();
			JsonParser parser = new JsonParser();
			parser.parse(input);
			return true;
		}catch (Exception e){
			return false;
		}

	}

	public static void main(String[] args)
	{
		Map<String, String> maps = new LinkedHashMap<String, String>();
		//maps.put("q1", "<html> fdfdfd\"</html>");
		//maps.put("q2", "<html> fdfdfd\"</html>");
		maps.put("q2", "test1");
		maps.put("q1", "test2");
		String str=JsonUtil.toJson(maps);
		System.out.println(str);
		maps.clear();
		maps = (Map<String, String>) JsonUtil.fromJson(str, Map.class);
		System.out.println(maps.get("q2"));
		str = "{\"action\":\"gotoServiceList\",\"params\":{\"key\":\"catalog_package_jz\"}}";
//		str = "";
//		str = "123fsdafas";
		System.out.println(validate(str));
	}

    public static String getValue(JsonObject paramObj, String key) {
		String ret = null;
		if(paramObj == null){
			return  ret;
		}
		if(paramObj.get(key) != null){
			ret = paramObj.get(key).getAsString();
		}
		return ret;
    }
}
