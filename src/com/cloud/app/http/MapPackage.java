/*
 * MapPackage.java
 * classes : com.cloud.app.http.MapPackage
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��9�� ����2:29:13
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloud.app.debug.DebugUtil;
import com.cloud.app.utils.MD5;
import com.google.gson.Gson;

/**
 * ������ ����������������ݣ�������map����
 * ʹ��ʱ������Ӧ�� head��para��result������Ϣ ������path
 * com.cloud.app.http.MapPackage
 * 
 * @author Andrew Lee <br/>
 *         create at 2014��6��9�� ����2:29:13
 */
public class MapPackage {
	private static final String TAG = "MapPackage";
//	����key
	private final String CODEKEY = "1234";
//	ϵͳ��Ϣ
	private final String OS = "1";
//	����
	private final String PATH = "http://115.29.13.164/";
	private Map<String, Object> headmap = new HashMap<String, Object>();
	private Map<String, Object> paramap = new HashMap<String, Object>();
	private Map<String, Object> resmap = new HashMap<String, Object>();
	private String path = "";



	public String getpath() {

		return PATH + this.path;
	}

	/**
	 * ���÷���·��
	 *@param path·�� ����
	 */
	public void setPath(String path) {

		this.path = path;
	}

	public String getHead(String key) {
		return (String) this.headmap.get(key);

	}

	/**
	 *@param uid �û�id
	 *@param no �ֻ�Ψһ��ʶ��
	 *@param version �汾��Ϣ
	 
	 */
	public void setHead(String uid, String no, String version) {

		this.headmap.put("uid", uid);
		this.headmap.put("no", no);
		this.headmap.put("os", OS);
		this.headmap.put("version", version);
		this.headmap.put(
				"key",
				MD5.getMD5(getHead("uid") + CODEKEY + getHead("no")
						+ getHead("os") + getHead("version")));

	}

	public String getPara(String key) {
		return (String) this.paramap.get(key);

	}

	/**
	 * ����para��Ϣ ��map����
	 *@param key 
	 *@param val
	 */
	public void setPara(String key, String val) {

		this.paramap.put(key, val);
		

	}

	public String getRes(String key) {
		return (String) this.resmap.get(key);

	}

	/**
	 * ���� result��Ϣ ��map����
	 *@param key
	 *@param val
	 */
	public void setRes(String key, String val) {

		this.resmap.put(key, val);

	}



	/**
	 * �������� ������ ���
	 *@return ����ԭʼ�������ݶ���
	 */
	public Map<String, Object> send() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.headmap.put("", "");
		this.paramap.put("","");
		this.resmap.put("","");
		map.put("head", this.headmap);
		map.put("para", this.paramap);
		map.put("result", this.resmap);
		Gson gson = new Gson();
		String backResult = HttpSendRecv.HttpSendRecve(this.getpath(),
				gson.toJson(map));
		// return GsonTools.getMaps(backResult);
		return GsonTools.getMaps(backResult.substring(backResult.indexOf("{")));

	}

	/**
	 *@return ����head map��Ϣ
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> backHead() {

		return (Map<String, String>) this.send().get("head");

	}

	/**
	 *@return ����para map ��Ϣ
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> backPara() {

		return (Map<String, String>) this.send().get("para");

	}

	/**
	 *@return ���� list �����Ϣ
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> backResult() {

		return (List<Map<String, String>>) this.send().get("result");
	}

}
