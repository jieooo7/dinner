/*
 * HttpSendRecv.java
 * classes : com.cloud.app.http.HttpSendRecv
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月6日 上午11:20:25
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.http;

import java.io.IOException;





import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;




/**
 * 异步 http 请求接收
 * com.cloud.app.http.HttpSendRecv
 * @author Andrew Lee <br/>
 * create at 2014年6月6日 上午11:20:25
 */
public class HttpSendRecv extends AsyncTask<Void, Void, String>{
	private static final String TAG = "HttpSendRecv";
	public static boolean netStat=false;
	private String path;
	private String st;
//	构建函数，传递参数
	public HttpSendRecv(String path,String st){
		this.path=path;
		this.st=st;
	}
	

    /** 
     *发送 Post请求 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
   
	public synchronized String HttpSendRecve(String path,String st){
		HttpPost httpRequest = new HttpPost(path);
		httpRequest.setHeader("content-type", "application/json");
		String strResult="";
		 try  
	        {  
			 
		         
		      StringEntity se = new StringEntity(st);
	          /* 添加请求参数到请求对象*/  
	          httpRequest.setEntity(se); ;  
	          /*发送请求并等待响应*/  
	          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);  
	          /*若状态码为200 ok*/  
	          if(httpResponse.getStatusLine().getStatusCode() == 200)   
	          {  
	            /*读返回数据*/  
	             strResult = EntityUtils.toString(httpResponse.getEntity());  
	             
	          }  
	          else  
	          {  
	            netStat=true;
	          }  
	        }  
	        catch (ClientProtocolException e)  
	        {   
	          netStat=true;
	          e.printStackTrace();  
	        }  
	        catch (IOException e)  
	        {   
	          netStat=true;
	          e.printStackTrace();  
	        }  
	        catch (Exception e)  
	        {   
	          netStat=true;
	          e.printStackTrace();   
	        } 
		return strResult;
	}
	
	/* 
	 * 后台线程执行的方法
	 * (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected String doInBackground(Void... params) {
		String s;
		s=HttpSendRecve(path,st);
		return s;
	}
	

}
