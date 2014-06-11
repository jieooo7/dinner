/*
 * GsonTools.java
 * classes : com.cloud.app.http.GsonTools
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月5日 下午2:02:32
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * gson 解析接送数据  根据不同的封装调用对应的方法
 * com.cloud.app.http.GsonTools
 * @author Andrew Lee <br/>
 * create at 2014年6月5日 下午2:02:32
 */
public class GsonTools {
	

	    public GsonTools() {
	    
	    }

	    /*
	     * 查看Gson api其中的fromJson(String json, Class<T> classOfT)方法
	     * public <T> T fromJson(String json, Class<T> classOfT)
	     * 用来描述一个特殊的Json对象,使用反射机制可以将JSON字符串中包含的内容赋值给指定的类。这边的T表示的是通过泛型,也就是可以表示任意的类型。
	     * 参数说明：
	     * json : 指定对象解析过的JSON字符串源,用来转换回Java对象。
	     * classOfT ： 泛型   T 的Class对象。
	     */
	    public static <T> T getPerson(String jsonString, Class<T> cls){
	        T t = null;
	        try {
	            Gson gson = new Gson();
	            t = gson.fromJson(jsonString, cls);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return t;
	    }
	    
	    /*
	     * List 我们使用的Gson中的
	     * public <T> T fromJson(String json, Type typeOfT)
	     * 这边我们需要取到List<T>中不同的对象，普通的实现方式就如上一讲中org.Json库来解析JSON的方式一样(读者阅读上一讲内容)，
	     * 这里我们通过 Gson中的 TypeToken类是简便操作：这边typeOfT的用法是通过反射机制把T里面的对象的属性的值映射出来，然后通过将json字符串取得的值赋值上去就可以了。
	     * getType()的意思就是表示将jsonString的字符串解析出来之后封装到List集合中，然后分别从T里面取出类型将其复制。
	     */
	    
	    public static <T> List<T> getPersons(String jsonString, Class<T> cls){
	        List<T> list = new ArrayList<T>();
	        try {
	            Gson gson = new Gson();
	            list = gson.fromJson(jsonString, 
	                    new TypeToken<List<T>>(){}.getType());
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return list;
	    }
	    
	    public static List<String> getList(String jsonString){
	        List<String> list = new ArrayList<String>();
	        try {
	            Gson gson = new Gson();
	            list = gson.fromJson(jsonString, 
	                    new TypeToken<List<String>>(){}.getType());
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return list;
	    }
	    public static Map<String, Object> getMaps(String jsonString){
	        Map<String, Object> map = new HashMap<String,Object>();
	        try {
	            Gson gson = new Gson();
	            map = gson.fromJson(jsonString, 
	                    new TypeToken<Map<String,Object>>(){}.getType());
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return map;
	    }
	    
	    
	    public static List<Map<String, String>> getListMaps(String jsonString){
	        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	        try {
	            Gson gson = new Gson();
	            list = gson.fromJson(jsonString, 
	                    new TypeToken<List<Map<String, String>>>(){}.getType());
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return list;
	    }
	
}
