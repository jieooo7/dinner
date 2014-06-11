/*
 * CustomHttpURLConnection.java
 * classes : com.cloud.app.http.CustomHttpURLConnection
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��6�� ����1:08:42
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.NameValuePair;

import android.util.Log;
/**
 * com.cloud.app.http.CustomHttpURLConnection
 * @author Andrew Lee <br/>
 * create at 2014��6��6�� ����1:08:42
 */


public class CustomHttpURLConnection {
	private static String TAG = "CustomHttpUrlConnection";
	private static HttpURLConnection conn;
	
	public CustomHttpURLConnection() {
	}

	public static String GetFromWebByHttpUrlConnection(String strUrl,
			NameValuePair... nameValuePairs) {
		String result="";
		try {
			URL url = new URL(strUrl);
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(4000);
			conn.setRequestProperty("accept", "*/*");
//			int resCode=conn.getResponseCode();
			conn.connect();
			InputStream stream=conn.getInputStream();
			InputStreamReader inReader=new InputStreamReader(stream);
			BufferedReader buffer=new BufferedReader(inReader);
			String strLine=null;
			while((strLine=buffer.readLine())!=null)
			{
				result+=strLine;
			}
			inReader.close();
			conn.disconnect();
			return result;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "getFromWebByHttpUrlCOnnection:"+e.getMessage());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "getFromWebByHttpUrlCOnnection:"+e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public static String PostFromWebByHttpURLConnection(String strUrl,
			NameValuePair... nameValuePairs) {
		String result="";
		try {
			/*URL url = new URL(strUrl);
			conn = (HttpURLConnection) url
					.openConnection();*/
			URL url = new URL("http://115.29.13.164/favGame.do?t=YYYYMMDDHHMMSS&z=1");
			URLConnection rulConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
			// �����Ƿ��httpUrlConnection���룬Ĭ���������true; 
			httpUrlConnection.setDoInput(true);
			// �����Ƿ���httpUrlConnection�������Ϊ�����post���󣬲���Ҫ����   
			// http�����ڣ������Ҫ��Ϊtrue, Ĭ���������false; 
			httpUrlConnection.setDoOutput(true);
			// �趨����ķ���Ϊ"POST"��Ĭ����GET 
			httpUrlConnection.setRequestMethod("POST");
			//���ó�ʱ
			httpUrlConnection.setConnectTimeout(3000);
			httpUrlConnection.setReadTimeout(4000);
			// Post ������ʹ�û��� 
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setInstanceFollowRedirects(true);
			// �趨���͵����������ǿ����л���java����   
			// (����������,�ڴ������л�����ʱ,��WEB����Ĭ�ϵĲ�����������ʱ������java.io.EOFException)  
			httpUrlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// ���ӣ���������2����url.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�
			//conn.connect();
			OutputStream outStrm = httpUrlConnection.getOutputStream(); 
			ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);   
			// ����������д�����ݣ���Щ���ݽ��浽�ڴ滺������   
			objOutputStrm.writeObject(new String("���ǲ�������"));   
			// ˢ�¶�������������κ��ֽڶ�д��Ǳ�ڵ����У�Щ��ΪObjectOutputStream��   
			objOutputStrm.flush();   
			// �ر������󡣴�ʱ������������������д���κ����ݣ���ǰд������ݴ������ڴ滺������,   
			// �ڵ����±ߵ�getInputStream()����ʱ�Ű�׼���õ�http������ʽ���͵�������   
			objOutputStrm.close();   
			
			InputStream in = httpUrlConnection.getInputStream();
			InputStreamReader inStream=new InputStreamReader(in);
			BufferedReader buffer=new BufferedReader(inStream);
			String strLine=null;
			while((strLine=buffer.readLine())!=null)
			{
				result+=strLine;
			}
			return result;
		} catch (IOException ex) {
			Log.e(TAG,"PostFromWebByHttpURLConnection��"+ ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

}
