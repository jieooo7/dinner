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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.gson.Gson;

/**
 * http 请求接收
 * com.cloud.app.http.HttpSendRecv
 * @author Andrew Lee <br/>
 * create at 2014年6月6日 上午11:20:25
 */
public class HttpSendRecv {
	private static final String TAG = "HttpSendRecv";
	
	
    /** 
     *发送 Post请求 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
   
	public static String HttpSendRecve(String path,String st){
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
	             Log.w(TAG, "connect error!!!!!!!!!");
	          }  
	        }  
	        catch (ClientProtocolException e)  
	        {   
	         
	          e.printStackTrace();  
	        }  
	        catch (IOException e)  
	        {   
	         
	          e.printStackTrace();  
	        }  
	        catch (Exception e)  
	        {   
	          
	          e.printStackTrace();   
	        } 
		return strResult;
	}
}
