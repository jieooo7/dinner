/*
 * MapPackage.java
 * classes : com.cloud.app.http.MapPackage
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��9�� ����2:29:13
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
 * ������ ����������������ݣ�������map����
 * ʹ��ʱ������Ӧ�� head��para��result������Ϣ ������path
 * com.cloud.app.http.MapPackage
 * 
 * @author Andrew Lee <br/>
 *         create at 2014��6��9�� ����2:29:13
 */
/**
 * com.cloud.app.http.MapPackage
 * @author Andrew Lee <br/>
 * create at 2014��6��16�� ����5:08:56
 */
/**
 * com.cloud.app.http.MapPackage
 * @author Andrew Lee <br/>
 * create at 2014��6��16�� ����5:09:00
 */
public class MapPackage {
	
//	����key
	private final String CODEKEY = "1234";
//	ϵͳ��Ϣ
	private final String OS = "1";
//	�汾��
	private static final String VERSION="1.1.2";
//	����
	public static final String PATH = "http://115.29.13.164/";
	private Map<String, Object> headmap = new HashMap<String, Object>();
	private Map<String, Object> paramap = new HashMap<String, Object>();
	private Map<String, Object> resmap = new HashMap<String, Object>();
//	���緵��ֵ
	private Map<String, Object> backResult=new HashMap<String, Object>();
	private String path = "";
	private String uid="-1";
	
	
	
	public Map<String, Object> getResult(){
		return this.backResult;
	}
	
	public String getUid(){
		return this.uid;
	}
//	Ĭ��Ϊ-1
	public void setUid(Context context){
//		�ӱ��ش洢��ȡ
	}
	public String getpath() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss",Locale.CHINA);
		return PATH + this.path+format.format(date);
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
	 * ����head
	 *@param uid �û�id
	 *@param no �ֻ�Ψһ��ʶ��
	 */
	public void setHead(Context context) {
//		��ȡ�ֻ�Ψһ��ʾ
		TelephonyManager telephonyManager=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//		�û�id
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
	 * @return 
	 *@return ����ԭʼ�������ݶ���
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public void send() throws InterruptedException, ExecutionException {
		Map<String, Object> map = new HashMap<String, Object>();
//		��ʼ������ֹ�쳣
		this.headmap.put("", "");
		this.paramap.put("","");
		this.resmap.put("","");
		map.put("head", this.headmap);
		map.put("para", this.paramap);
		map.put("result", this.resmap);
		Gson gson = new Gson();
//		�����첽���紫��
		HttpSendRecv task=new HttpSendRecv(this.getpath(),gson.toJson(map));
//		����ȡ�÷�����Ϣ
		String backResult =task.execute().get();
		DebugUtil.i("all", backResult);
		this.backResult=GsonTools.getMaps(backResult.substring(backResult.indexOf("{")));

	}

	/**
	 *@return ����head map��Ϣ
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
	 *@return ����para map ��Ϣ
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
	 *@return ���� list �����Ϣ
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getBackResult() {
		try{
//			��һ��object���͵ģ�castΪlist
		return (List<Map<String, String>>) this.backResult.get("result");
		}catch(Exception e){
			return null;
		}
	}

}