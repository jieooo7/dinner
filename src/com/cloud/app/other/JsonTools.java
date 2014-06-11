/*
 * JsonTools.java
 * classes : com.cloud.app.http.JsonTools
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��5�� ����2:17:35
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * json ��ȡ��ʽ
 * com.cloud.app.http.JsonTools
 * @author Andrew Lee <br/>
 * create at 2014��6��5�� ����2:17:35
 */
public class JsonTools {
	

	    public JsonTools() {
	        // TODO Auto-generated constructor stub
	    }
	    
	    // ��ʱ�ӷ����ȡ�����������ǣ�{"person":{"address":"XIAMEN","id":23,"name":"AHuier"}}
	    public static CouponData getPerson(String key, String jsonString){
	    	CouponData coupondata = new CouponData();
	        try {
	            // ��Android�ٷ��ĵ��У�org.json ����Android�ṩ�����ǵĽ���json���ݸ�ʽ�İ���
	            // ���ǱȽϳ��õ���JSONArray �� JSONObject���������
	            JSONObject jsonObject = new JSONObject(jsonString);
	            JSONObject personObject = jsonObject.getJSONObject(key);
//	            person.setId(personObject.getInt("id"));
//	            person.setAddress(personObject.getString("address"));
//	            person.setName(personObject.getString("name"));
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return coupondata;
	    }
	    
	    //{"persons":[{"address":"Beijing","id":1001,"name":"AHuier1"},{"address":"shenzheng","id":1002,"name":"AHuier2"}]}
	    public static List<CouponData> getPersons(String key, String jsonString){
	        List<CouponData> list = new ArrayList<CouponData>();
	        try {
	            JSONObject jsonObject = new JSONObject(jsonString);
	            //����json������
	            JSONArray jsonArray = jsonObject.getJSONArray(key);
	            for(int i = 0; i < jsonArray.length(); i++){
	                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//	                CouponData coupondata = new CouponData();
//	                CouponData.setId(jsonObject2.getInt("id"));
//	                coupondata.setName(jsonObject2.getString("name"));
//	                coupondata.setAddress(jsonObject2.getString("address"));
//	                list.add(coupondata);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return list;
	    }
	    
	    //{"listString":["Hello","World","AHuier"]}
	    public static List<String> getListString(String key, String jsonString){
	        List<String> listString = new ArrayList<String>();
	        try {
	            JSONObject jsonObject = new JSONObject(jsonString);
	            //����JSON������
	            JSONArray jsonArray = jsonObject.getJSONArray(key);
	            for(int i = 0; i < jsonArray.length(); i++){
	                String msg = jsonArray.getString(i);
	                listString.add(msg);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return listString;
	    }
	    
	    // ��ʱ�ӷ����ȡ�����������ǣ�{"listMap":[{"id":1,"color":"red","name":"Polu"},{"id":7,"color":"green","name":"Zark"}]}
	    public static List<Map<String, Object>> getListMaps(String key, String jsonString){
	        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	        try {
	            JSONObject jsonObject = new JSONObject(jsonString);
	            JSONArray jsonArray = jsonObject.getJSONArray(key);
	            for(int i = 0; i < jsonArray.length(); i++){
	                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
	                Map<String, Object> map = new HashMap<String, Object>();
	                // ͨ��org.json�еĵ�������ȡMap�е�ֵ��
	                Iterator<String> iterator = jsonObject2.keys();
	                while(iterator.hasNext()) {
	                    String jsonKey = iterator.next();
	                    Object jsonValue = jsonObject2.get(jsonKey);
	                    //JSON��ֵ�ǿ���Ϊ�յģ���������Ҳ��Ҫ��JSON�Ŀ�ֵ�����Խ����жϡ�
	                    if(jsonValue == null){
	                        jsonValue = "";
	                    }
	                    map.put(jsonKey, jsonValue);
	                }
	                listMap.add(map);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return listMap;
	    }    
	


}
