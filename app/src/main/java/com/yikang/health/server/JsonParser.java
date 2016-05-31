package com.yikang.health.server;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;



public class JsonParser {

	static JsonParser jsonParser = new JsonParser();

	public static JsonParser getInstance() {
		return jsonParser == null ? new JsonParser() : jsonParser;
	}
	
	
	
	/**
	 * {@code 实体嵌套集合数据结构模型}<br>
	 *  将JSON数据转化为实体对象
	 * @param parentCls 父类实体对象
	 * @param childCls  嵌套子类实体对象
	 * @param childKey 嵌套子类JSON的Key
	 * @return 
	 */
	public Object jsonToEntityList(String orgJson,Class<?> parentCls,Class<?> childCls,String childKey){
		Object object = null;
		try {
			object = parentCls.newInstance();
			JSONObject jsonObject = new JSONObject(orgJson);
			Field[] fields = parentCls.getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				
				Field field = fields[j];
				String fieldName = field.getName();
				String methodName = "set"+ Character.toUpperCase(fieldName.charAt(0))	+ fieldName.substring(1);
				Method method = parentCls.getMethod(methodName,	new Class[] { field.getType() });
				
				Object filedValue = null;
				try {
					filedValue = jsonObject.get(fieldName);
					if (null != filedValue) {
						if(fieldName.equals(childKey)){
							ArrayList<Object> childList = jsonToList(jsonObject.getString(childKey), childCls);
							if (null != childList&& childList.size() > 0) {
								method.invoke(object, childList);
							}
						}else{
							method.invoke(object, filedValue);
						}
						
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			
		}
		return object;
	}
	
	/**
	 * {@code 实体嵌套集合数据结构模型}<br>
	 *  将JSON数据转化为实体对象
	 * @param parentCls 父类实体对象
	 * @param childCls  嵌套子类实体对象
	 * @param childKey 嵌套子类JSON的Key
	 * @return 
	 */
	public Object jsonEntityToEntity(String orgJson,Class<?> parentCls,Class<?> childCls,String childKey){
		Object object = null;
		try {
			object = parentCls.newInstance();
			JSONObject jsonObject = new JSONObject(orgJson);
			Field[] fields = parentCls.getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				
				Field field = fields[j];
				String fieldName = field.getName();
				String methodName = "set"+ Character.toUpperCase(fieldName.charAt(0))	+ fieldName.substring(1);
				Method method = parentCls.getMethod(methodName,	new Class[] { field.getType() });
				
				Object filedValue = null;
				try {
					filedValue = jsonObject.get(fieldName);
					if (null != filedValue) {
						if(fieldName.equals(childKey)){
							Object childList = jsonToEntity(jsonObject.getString(childKey), childCls);
							if (null != childList) {
								method.invoke(object, childList);
							}
						}else{
							method.invoke(object, filedValue);
						}
						
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		return object;
	}
	
	/**
	 * {@code Key集合数据结构模型}<br>
	 * 将JSON数据转化为ArrayList
	 * @param cls 对应的JSON字符串实体对象
	 * @param jsonKey 基本JSON数据中的某个Key值
	 * @return
	 */
	public List<?> jsonToList(String orgJson, Class<?> cls, String jsonKey) {
		try {
			
			JSONObject jsonObj = new JSONObject(orgJson);

			return GsonTools.getList(jsonObj.getString(jsonKey),cls);
//			return jsonToList(jsonObj.getString(jsonKey),cls);

		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	/**
	 * {@code 集合数据结构模型}<br>
	 * 将JSON数据格式化为ArrayList
	 * @param cls 对应的JSON字符串实体对象
	 * @return 
	 */
	public ArrayList<Object> jsonToList(String arrJson, Class<?> cls) {
		ArrayList<Object> alObjects =null;
		try {
			JSONArray jsonArray = new JSONArray(arrJson);
			alObjects = new ArrayList<Object>(jsonArray.length());
			if (jsonArray.length() > 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					String jsonItem = jsonArray.getString(i);
					alObjects.add(jsonToEntity(jsonItem, cls));
				}
			}

		} catch (Exception e) {
		}
		return alObjects;
	}
	
	/**
	 * {@code 集合嵌套集合数据结构模型}<br>
	 * 将JSON数据格式化为ArrayList<br>
	 * 如：[{"title":"aaa","itemList":[{"itemTitle"："cca"},{"itemTitle"："cca"}]},{"title":"aab","itemList":[{"itemTitle"："cca"},{"itemTitle"："cca"}]}]
	 * @param parentCls 父类实体对象
	 * @param childCls  嵌套子类实体对象
	 * @param childKey 嵌套子类JSON的Key
	 * @return
	 */
	public ArrayList<Object> jsonToList(String arrJson, Class<?> parentCls,Class<?> childCls,String childKey) {
		ArrayList<Object> alObjects = null;
		try {
			JSONArray jsonArray = new JSONArray(arrJson);
			if (jsonArray.length() > 0) {
				alObjects = new ArrayList<Object>(jsonArray.length());
				
				Field[] fields = parentCls.getDeclaredFields();
				
				for (int i = 0; i < jsonArray.length(); i++) {
					
					Object itemCls = parentCls.newInstance();
					
					JSONObject itemObj = jsonArray.getJSONObject(i);
					
					for (int j = 0; j < fields.length; j++) {
						Field field = fields[j];
						
						String fieldName = field.getName();
						
						String methodName = "set"+ Character.toUpperCase(fieldName.charAt(0))	+ fieldName.substring(1);
						Method method = parentCls.getMethod(methodName,	new Class[] { field.getType() });
						
						try {
							
							if(itemObj!=null){
								
								ArrayList<Object> childList;
								
								if(fieldName.equals(childKey)){
									
									childList = jsonToList(itemObj.getString(childKey), childCls);
									
									if (null != childList && childList.size() > 0) {
										method.invoke(itemCls, childList);
									}
									
								}else{
									Object valueObj = itemObj.get(fieldName);
									if (null != valueObj) {
										method.invoke(itemCls, valueObj);
									}
									
								}
							}
						} catch (Exception e) {
							
						}
					}
					
					alObjects.add(itemCls);
				}
			}else{
				return null;
			}

		} catch (Exception e) {
		}
		return alObjects;
	}


	/**
	 * {@code 实体数据结构模型}<br>
	 * @param cls 对应的JSON字符串实体对象
	 * @return 实体对象
	 */
	public Object jsonToEntity(String orgJson, Class<?> cls) {
		Object object = null;
		try {
			
			object = cls.newInstance();
				
			JSONObject jsonObject = new JSONObject(orgJson);
			Field[] fields = cls.getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				
				Field field = fields[j];
				String fieldName = field.getName();
				String methodName = "set"+ Character.toUpperCase(fieldName.charAt(0))	+ fieldName.substring(1);
				Method method = cls.getMethod(methodName,	new Class[] { field.getType() });
				Object filedValue = null;
				
				try {
					filedValue = jsonObject.get(fieldName);
					if (null != filedValue) {
						method.invoke(object, filedValue);
					}
				} catch (Exception e) {
				}
				
			}
				
			
		} catch (Exception e) {
			
		}
		return object;
	}
	
	
}


