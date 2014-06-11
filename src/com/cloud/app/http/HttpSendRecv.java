/*
 * HttpSendRecv.java
 * classes : com.cloud.app.http.HttpSendRecv
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��6�� ����11:20:25
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
 * http �������
 * com.cloud.app.http.HttpSendRecv
 * @author Andrew Lee <br/>
 * create at 2014��6��6�� ����11:20:25
 */
public class HttpSendRecv {
	private static final String TAG = "HttpSendRecv";
	
	
    /** 
     *���� Post���� 
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
	          /* �������������������*/  
	          httpRequest.setEntity(se); ;  
	          /*�������󲢵ȴ���Ӧ*/  
	          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);  
	          /*��״̬��Ϊ200 ok*/  
	          if(httpResponse.getStatusLine().getStatusCode() == 200)   
	          {  
	            /*����������*/  
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
