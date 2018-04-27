package com.homevip.util.em;

import com.homevip.util.http.BaiDuHttpUtil;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapUtil {
	public static Object keyToSt(Object data){
		if(data instanceof List){
			data = MapUtil.keyToSt4List((List<Map<String, Object>>)data);
		}else if(data instanceof Map){
			data = MapUtil.keyToSt4Map((Map<String, Object>)data);
		}
		return data;
	}

	public static List<Map<String, Object>> keyToSt4List(List<Map<String, Object>> data){
		if(data == null || data.size() == 0){
			return null;
		}

		for(Map<String, Object> item : data){
			keyToSt4Map(item);
		}
		return data;
	}

	public static Map<String, Object> keyToSt4Map(Map<String, Object> data){
		if(data == null){
			return null;
		}
		ObjectPool op = ObjectPool.getInstance();
		List<String> keyList = op.getStringList();
		Set<String> keySet = data.keySet();
		for(String key : keySet){
			keyList.add(key);
		}
		for(String key : keyList){
			if(key != null && !"".equals(key.trim())){
				String newKey = key.toLowerCase();
				int index = newKey.indexOf("_");
				while(index != -1){
					String nextChar = String.valueOf(newKey.charAt(index + 1));
					String nextCharU = nextChar.toUpperCase();
					newKey = newKey.replace("_" + nextChar, nextCharU);
					index = newKey.indexOf("_");
				}
				if(DataUtil.strCompare(key, newKey)){
					continue;
				}
				data.put(newKey, data.get(key));
				data.remove(key);
			}
		}
		op.ccStringList(keyList);
		return data;
	}

	public static <T> List<T> listMapConvertToListObject(List<Map<String, Object>> mapList, Class<T> clazz){
		if(mapList == null || mapList.size() == 0){
			return null;
		}
		List<T> tList = new ArrayList<T>();
		for(Map<String, Object> mapItem : mapList){
			T t = mapConvertToObject(mapItem, clazz);
			tList.add(t);
		}
		return tList;
	}

	public static <T> T mapConvertToObject(Map<String, Object> map, Class<T> clazz){
		if(clazz == null || map == null){
			return null;
		}
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if(t != null){
			Set<String> keySet = map.keySet();
			for(String key : keySet){
				if(key == null || "".equals(key.trim())){
					continue;
				}
				String newKey = key.toLowerCase();
				int index = newKey.indexOf("_");
				while(index != -1){
					String nextChar = String.valueOf(newKey.charAt(index + 1));
					String nextCharU = nextChar.toUpperCase();
					newKey = newKey.replace("_" + nextChar, nextCharU);
					index = newKey.indexOf("_");
				}
				newKey = String.valueOf(newKey.charAt(0)).toUpperCase() + newKey.substring(1);
				Object value = map.get(key);
				Method targetMethod = null;
				if(value == null){
					continue;
				}
				Class valueClass = value.getClass();
				if(valueClass == BigInteger.class){
					valueClass = Long.class;
				}
				try {
					targetMethod = clazz.getMethod("set" + newKey, valueClass);
				} catch (Exception e) {
				}

				if(targetMethod == null){
					if(valueClass == Boolean.class){
						valueClass = boolean.class;
					}else if(valueClass == Integer.class){
						valueClass = int.class;
					}else if(valueClass == Long.class){
						valueClass = long.class;
					}else if(valueClass == Double.class){
						valueClass = double.class;
					}else if(valueClass == Short.class){
						valueClass = short.class;
					}else if(valueClass == Float.class){
						valueClass = float.class;
					}else if(valueClass == Byte.class){
						valueClass = byte.class;
					}
					try {
						targetMethod = clazz.getMethod("set" + newKey, valueClass);
					} catch (Exception e) {
					}
				}
				if(targetMethod == null){
					continue;
				}
				try {
					if(value.getClass() == BigInteger.class){
						value = ((BigInteger)value).longValue();
					}
					targetMethod.invoke(t, value);
				} catch (Exception e) {
				}
			}
		}
		return t;
	}

	public static void mapData2Map(Map<String, Object> source, Map<String, Object> target, String[][] attrs){
		if(source == null || target == null || attrs == null || attrs.length == 0){
			return;
		}

		if(attrs == null || attrs.length == 0){
			Set<String> keySet = source.keySet();
			for(String key : keySet){
				Object sourceObj = source.get(key);
				target.put(key, sourceObj);
			}
			return;
		}

		for(String[] item : attrs){
			if(item == null || item.length == 0){
				continue;
			}
			String sourceAttr = item[0];
			String targetAttr = null;
			if(item.length < 2){
				targetAttr = item[0];
			}else{
				targetAttr = item[1];
			}
			Object sourceObj = source.get(sourceAttr);
			target.put(targetAttr, sourceObj);
		}
	}

	public static List<Map<String, Object>> changeKey(List<Map<String, Object>> data, String[][] attrs){
		if(data == null || data.size() == 0 || attrs == null || attrs.length == 0){
			return null;
		}

		for(Map<String, Object> item : data){
			changeKey(item, attrs);
		}

		return data;
	}

	public static Map<String, Object> changeKey(Map<String, Object> data, String[][] attrs){
		if(data == null || attrs == null || attrs.length == 0){
			return null;
		}

		for(String[] item : attrs){
			if(item == null || item.length == 0){
				continue;
			}
			String sourceAttr = item[0];
			String targetAttr = null;
			if(item.length < 2){
				targetAttr = item[0];
			}else{
				targetAttr = item[1];
			}
			if(DataUtil.strCompare(sourceAttr, targetAttr)){
				continue;
			}
			Object sourceObj = data.get(sourceAttr);
			data.put(targetAttr, sourceObj);
			data.remove(sourceAttr);
		}
		return data;
	}


	public static Map<String, BigDecimal> getLatAndLngByAddress(String addr){
		String address = "";
		String lat = "";
		String lng = "";
		try {
			address = java.net.URLEncoder.encode(addr,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String url = String.format("http://api.map.baidu.com/geocoder/v2/?"
				+"ak=4rcKAZKG9OIl0wDkICSLx8BA&output=json&address=%s",address);
		URL myURL = null;
		URLConnection httpsConn = null;
		//进行转码
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {

		}
		try {
			httpsConn = (URLConnection) myURL.openConnection();
			if (httpsConn != null) {
				InputStreamReader insr = new InputStreamReader(
						httpsConn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if ((data = br.readLine()) != null) {
					lat = data.substring(data.indexOf("\"lat\":")
							+ ("\"lat\":").length(), data.indexOf("},\"precise\""));
					lng = data.substring(data.indexOf("\"lng\":")
							+ ("\"lng\":").length(), data.indexOf(",\"lat\""));
				}
				insr.close();
			}
		} catch (IOException e) {

		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("lat", new BigDecimal(lat));
		map.put("lng", new BigDecimal(lng));
		return map;
	}

	public static Map<String,Double> getLngAndLat(String address){
		Map<String,Double> map=new HashMap<String, Double>();
		String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=F454f8a5efe5e577997931cc01de3974";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.fromObject(json);
		if(obj.get("status").toString().equals("0")){
			double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
			map.put("lng", lng);
			map.put("lat", lat);
			//System.out.println("经度："+lng+"---纬度："+lat);
		}else{
			//System.out.println("未找到相匹配的经纬度！");
		}
		return map;
	}

	public static String loadJSON (String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine = null;
			while ( (inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return json.toString();
	}

	public static String getCity(String lat, String lng) {
		JSONObject obj = getLocationInfo(lat, lng).getJSONObject("result")
				.getJSONObject("addressComponent");
		return obj.getString("city");
	}

	public static JSONObject getLocationInfo(String lat, String lng) {
		String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + ","
				+ lng + "&output=json&ak=" + BaiDuHttpUtil.BAIDU_GEOCONV_KEY+"&pois=0";
		JSONObject obj = JSONObject.fromObject(BaiDuHttpUtil.getRequest(url));
		return obj;
	}

	// 申请的密钥，注：这个密钥一天只能访问50万次，而且需要申请
	public static final String AK = "3OwsREAN0XK5707kEbbniePY";
	// 服务地址
	public static final String SUGGESTION_URL = "http://api.map.baidu.com/place/v2/suggestion";
	// 接口参数（一共7个，有些是非必输的）
	public static HashMap<String, String> parameters = new HashMap<String, String>();
	static {
		parameters.put("query", ""); //输入建议关键字（支持拼音）
		parameters.put("region", ""); //所属城市/区域名称或代号
		parameters.put("location", ""); //传入location参数后，返回结果将以距离进行排序
		parameters.put("output", "json"); //返回数据格式，可选json、xml两种
		parameters.put("ak", AK); //开发者访问密钥，必选
		parameters.put("sn", ""); //用户的权限签名
		parameters.put("timestamp", ""); //设置sn后该值必选
	};


	public static void main(String[] args){
//		Map<String, Object> map = new HashMap<String,Object>();
//		map.put("TEST_HH", "zzj");
//		map.put("AGE_DD", 1);
//		map.put("AGE_D2", 33l);
//		map.put("AGE_D3", 345d);
//		map.put("AGE_D4", new Date());
		//TestEntity entity = MapUtil.mapConvertToObject(map, TestEntity.class);
		//System.out.println(entity.getTestHh() + "===" + entity.getAgeDd() + "===" + entity.getAgeD2() + "===" + entity.getAgeD3() + "===" + entity.getAgeD4());

//		Map<String, Object> map2 = new HashMap<String,Object>();
//		MapUtil.mapData2Map(map, map2,
//				new String[][]{
//					{"TEST_HH", "NAME"},
//					{"AGE_D2"}
//					});
//		System.out.println(map2.get("NAME") + "===" + map.get("AGE_D2"));

//		MapUtil.changeKey(map, new String[][]{{"TEST_HH", "aa"}});
//		Set<String> keySet = map.keySet();
//		for(String key : keySet){
//			Object sourceObj = map.get(key);
//			System.out.println(key + "===" + map.get(key));
//		}
//		Map<String, Double> map = getLngAndLat("广州市中山大学");
//		System.out.println("经度："+map.get("lng")+"---纬度："+map.get("lat"));
		String city = getCity("113.30461","23.104288");
		System.out.print(city);
	}
	
}
