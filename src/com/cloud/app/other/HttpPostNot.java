/*
 * HttpPost.java
 * classes : com.cloud.app.http.HttpPost
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��5�� ����3:25:43
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.other;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * com.cloud.app.http.HttpPost
 * @author Andrew Lee <br/>
 * create at 2014��6��5�� ����3:25:59
 */
public class HttpPostNot {

	// ����������˵�url
	private static String PATH = "http://115.29.13.164/favGame.do?t=YYYYMMDDHHMMSS&z=1";
	private static URL url;

	public HttpPostNot() {
		// TODO Auto-generated constructor stub
	}

	static {
		try {
			url = new URL(PATH);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param params
	 *            ��д��url�Ĳ���
	 * @param encode
	 *            �ֽڱ���
	 * @return
	 */
	public static String sendPostMessage(Map<String, String> params,
			String encode) {
		// ��ΪStringBuffer��ʼ�����ַ���
		StringBuffer buffer = new StringBuffer();
		try {
			if (params != null && !params.isEmpty()) {
				  for (Map.Entry<String, String> entry : params.entrySet()) {
						// ���ת�����
						buffer.append(entry.getKey()).append("=").append(
								URLEncoder.encode(entry.getValue(), encode))
								.append("&");
					}
				buffer.deleteCharAt(buffer.length() - 1);
			}
			// System.out.println(buffer.toString());
			// ɾ��������һ��&
			
			System.out.println("-->>"+buffer.toString());
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(3000);
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);// ��ʾ�ӷ�������ȡ����
			urlConnection.setDoOutput(true);// ��ʾ�������д����
			// ����ϴ���Ϣ���ֽڴ�С�Լ�����
			byte[] mydata = buffer.toString().getBytes();
			// ��ʾ������������������ı�����
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Content-Length",
					String.valueOf(mydata.length));
			// ��������,��������������
			OutputStream outputStream = urlConnection.getOutputStream();
			outputStream.write(mydata,0,mydata.length);
			outputStream.close();
			// ��÷�������Ӧ�Ľ����״̬��
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				return changeInputStream(urlConnection.getInputStream(), encode);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * ��һ��������ת����ָ��������ַ���
	 * 
	 * @param inputStream
	 * @param encode
	 * @return
	 */
	private static String changeInputStream(InputStream inputStream,
			String encode) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if (inputStream != null) {
			try {
				while ((len = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), encode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", "admin");
		params.put("password", "1234");
		String result = HttpPostNot.sendPostMessage(params, "utf-8");
		System.out.println("--result->>" + result);
	}

}