/*
 * MapPackage.java
 * classes : com.cloud.app.http.MapPackage
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月9日 下午2:29:13
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.http;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.telephony.TelephonyManager;

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
/**
 * com.cloud.app.http.MapPackage
 * @author Andrew Lee <br/>
 * create at 2014年6月16日 下午5:08:56
 */
/**
 * com.cloud.app.http.MapPackage
 * @author Andrew Lee <br/>
 * create at 2014年6月16日 下午5:09:00
 */
public class MapPackage {
	
//	加密key
	private final String CODEKEY = "1234";
//	系统信息
	private final String OS = "1";
//	版本号
	private static final String VERSION="1.1.2";
//	域名
	public static final String PATH = "http://115.29.13.164/";
	private Map<String, Object> headmap = new HashMap<String, Object>();
	private Map<String, Object> paramap = new HashMap<String, Object>();
	private Map<String, Object> resmap = new HashMap<String, Object>();
//	网络返回值
	private Map<String, Object> backResult=new HashMap<String, Object>();
	private String path = "";
	private String uid="-1";
	
	
	
	public Map<String, Object> getResult(){
		return this.backResult;
	}
	
	public String getUid(){
		return this.uid;
	}
//	默认为-1
	public void setUid(Context context){
//		从本地存储获取
	}
	public String getpath() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		return PATH + this.path+format.format(date);
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
	 * 设置head
	 *@param uid 用户id
	 *@param no 手机唯一标识符
	 */
	public void setHead(Context context) {
//		获取手机唯一标示
		TelephonyManager telephonyManager=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//		用户id
		setUid(context);
		this.headmap.put("uid", this.getUid());
		this.headmap.put("no", telephonyManager.getDeviceId());
		this.headmap.put("os", OS);
		this.headmap.put("version",VERSION);
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
	 * @return 
	 *@return 返回原始解析数据对象
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void send() throws InterruptedException, ExecutionException {
		Map<String, Object> map = new HashMap<String, Object>();
//		初始化，防止异常
		this.headmap.put("", "");
		this.paramap.put("","");
		this.resmap.put("","");
		map.put("head", this.headmap);
		map.put("para", this.paramap);
		map.put("result", this.resmap);
		Gson gson = new Gson();
//		调用异步网络传输
		HttpSendRecv task=new HttpSendRecv(this.getpath(),gson.toJson(map));
//		网络取得返回信息
		String backResult =task.execute().get();
		DebugUtil.i("all", backResult);
		this.backResult=GsonTools.getMaps(backResult.substring(backResult.indexOf("{")));

	}

	/**
	 *@return 返回head map信息
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getBackHead() {
		try{
		return (Map<String, String>) this.backResult.get("head");
		}catch(Exception e){
			return null;
		}
	}

	/**
	 *@return 返回para map 信息
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getBackPara() {
		try{
		return (Map<String, String>) this.backResult.get("para");
		}catch(Exception e){
		return null;
		}
	}

	/**
	 *@return 返回 list 结果信息
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getBackResult() {
		try{
//			是一个object类型的，cast为list
		return (List<Map<String, String>>) this.backResult.get("result");
		}catch(Exception e){
			return null;
		}
	}

}
