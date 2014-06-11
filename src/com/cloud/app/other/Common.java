/*
 * Common.java
 * classes : com.cloud.app.http.Common
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月9日 下午2:51:06
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.other;

import com.google.gson.Gson;

/**
 * com.cloud.app.http.Common
 * @author Andrew Lee <br/>
 * create at 2014年6月9日 下午2:51:06
 */


public class Common {
	private static final String TAG = "Common";

	/**
	 * 向服务器发送数据并返回信息
	 *@param path 网络url地址
	 *@param arg0 head头
	 *@param arg1 para信息	
	 *@param arg2 result信息对象
	 *@param arg3  需要返回的对象
	 *@return arg3 定义的对象类型
	 *
	 */
//	public static <T> T  sendAndReceive(String path,Object arg0,Object arg1,Object arg2,Class<T> arg3){
//		
//		Gson gson=new Gson();
//		
//		
//		return new Gson().fromJson(HttpSendRecv.HttpSendRecve(path,gson.toJson(MapPackage.MapP(arg0, arg1, arg2))), arg3);
//		
//	}
}
