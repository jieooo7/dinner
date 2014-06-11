/*
 * MapPackage.java
 * classes : com.cloud.app.http.MapPackage
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月9日 下午2:29:13
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
 * 公共类 ，向服务器发送数据，并返回map类型
 * 使用时调用相应的 head，para，result发送信息 并设置path
 * com.cloud.app.http.MapPackage
 * 
 * @author Andrew Lee <br/>
 *         create at 2014年6月9日 下午2:29:13
 */
public class MapPackage {
	private static final String TAG = "MapPackage";
//	加密key
	private final String CODEKEY = "1234";
//	系统信息
	private final String OS = "1";
//	域名
	private final String PATH = "http://115.29.13.164/";
	private Map<String, Object> headmap = new HashMap<String, Object>();
	private Map<String, Object> paramap = new HashMap<String, Object>();
	private Map<String, Object> resmap = new HashMap<String, Object>();
	private String path = "";



	public String getpath() {

		return PATH + this.path;
	}

	/**
	 * 设置访问路径
	 *@param path路径 参数
	 */
	public void setPath(String path) {

		this.path = path;
	}

	public String getHead(String key) {
		return (String) this.headmap.get(key);

	}

	/**
	 *@param uid 用户id
	 *@param no 手机唯一标识符
	 *@param version 版本信息
	 
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
	 * 设置para信息 ，map类型
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
	 * 设置 result信息 ，map类型
	 *@param key
	 *@param val
	 */
	public void setRes(String key, String val) {

		this.resmap.put(key, val);

	}



	/**
	 * 发送请求 并返回 结果
	 *@return 返回原始解析数据对象
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
	 *@return 返回head map信息
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> backHead() {

		return (Map<String, String>) this.send().get("head");

	}

	/**
	 *@return 返回para map 信息
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> backPara() {

		return (Map<String, String>) this.send().get("para");

	}

	/**
	 *@return 返回 list 结果信息
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> backResult() {

		return (List<Map<String, String>>) this.send().get("result");
	}

}
