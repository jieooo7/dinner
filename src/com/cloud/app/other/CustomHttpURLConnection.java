/*
 * CustomHttpURLConnection.java
 * classes : com.cloud.app.http.CustomHttpURLConnection
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月6日 下午1:08:42
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
 * create at 2014年6月6日 下午1:08:42
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
			// 设置是否从httpUrlConnection读入，默认情况下是true; 
			httpUrlConnection.setDoInput(true);
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   
			// http正文内，因此需要设为true, 默认情况下是false; 
			httpUrlConnection.setDoOutput(true);
			// 设定请求的方法为"POST"，默认是GET 
			httpUrlConnection.setRequestMethod("POST");
			//设置超时
			httpUrlConnection.setConnectTimeout(3000);
			httpUrlConnection.setReadTimeout(4000);
			// Post 请求不能使用缓存 
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setInstanceFollowRedirects(true);
			// 设定传送的内容类型是可序列化的java对象   
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)  
			httpUrlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
			//conn.connect();
			OutputStream outStrm = httpUrlConnection.getOutputStream(); 
			ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);   
			// 向对象输出流写出数据，这些数据将存到内存缓冲区中   
			objOutputStrm.writeObject(new String("我是测试数据"));   
			// 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）   
			objOutputStrm.flush();   
			// 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,   
			// 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器   
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
			Log.e(TAG,"PostFromWebByHttpURLConnection："+ ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

}
