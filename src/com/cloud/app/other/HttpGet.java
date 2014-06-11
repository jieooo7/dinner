package com.cloud.app.other;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * �������ȡjson����
 * com.cloud.app.http.HttpPost
 * @author Andrew Lee <br/>
 * create at 2014��6��5�� ����2:11:19
 */
public class HttpGet {
	


	public HttpGet(){
		
	}
	
	//�������ȡ����
	public  static String getJsonContent(String url_path){
		
		try{
			URL url=new URL(url_path);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(3000);//���ó�ʱ
			connection.setRequestMethod("GET");//����ʽΪget����һ��Ϊpost
			connection.setDoInput(true);//���ÿ��Զ�ȡ��������ݣ�ͨ��connection.getInputStream().read()
			
			int code=connection.getResponseCode();//��ȡ״̬��
			if(code==200)
				{
					return changeInputStream(connection.getInputStream());
				//changeInputStream()��������Ϊstatic���˴�����(��̬�����ڲ�����ʹ��this����)
				//��̬�����ڷ��ʱ���ĳ�Աʱ��ֻ������ʾ�̬��Ա
				}
		}catch(Exception e){
			
		}
		
		return "";
		
	}

//���ֽ���ת�����ַ���
	private  static String changeInputStream(InputStream inputStream) {
		// TODO Auto-generated method stub
		String jsonString="";
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();//�ֽ����������
		//ByteArrayOutputStream �е�����
		//��д��һ�� byte ���顣���������������ݵĲ���д����Զ���������ʹ�� toByteArray() �� toString() ��ȡ���ݡ�
		int length=0;
		byte[] data=new byte[1024];
		try{
			while(-1!=(length=inputStream.read(data)))//
			{
				outputStream.write(data, 0, length);
			}
			jsonString=new String(outputStream.toByteArray());
			
		}catch(Exception e){
			
			
		}
		
		return jsonString;
	}

}
/*
 * read()��read(byte[] b)�����������ڳ�����InputStream��ǰ������Ϊ���󷽷����ڵģ����߲��ǣ�JDK API
 * ���������������ߵģ� 

1��read() : 
���������ж�ȡ���ݵ���һ���ֽڣ�����0��255��Χ�ڵ�int�ֽ�ֵ�������Ϊ�Ѿ�������ĩβ��û�п��õ��ֽڣ��򷵻�-1��
���������ݿ��á���⵽��ĩβ�����׳��쳣ǰ���˷���һֱ������ 

2��read(byte[] b) :  
���������ж�ȡһ���������ֽڣ�������洢�ڻ��������� b �С���������ʽ����ʵ�ʶ�ȡ���ֽ��������������ݿ��á���⵽
�ļ�ĩβ�����׳��쳣ǰ���˷���һֱ��������� b �ĳ���Ϊ 0���򲻶�ȡ�κ��ֽڲ����� 0�����򣬳��Զ�ȡ����һ���ֽڡ�
�����Ϊ��λ���ļ�ĩβ��û�п��õ��ֽڣ��򷵻�ֵ -1���������ٶ�ȡһ���ֽڲ�����洢�� b �С�����ȡ�ĵ�һ���ֽ�
�洢��Ԫ�� b[0] �У���һ���洢�� b[1] �У��������ơ���ȡ���ֽ��������� b �ĳ��ȡ��� k Ϊʵ�ʶ�ȡ���ֽ�����
��Щ�ֽڽ��洢�� b[0] �� b[k-1] ��Ԫ���У���Ӱ�� b[k] �� b[b.length-1] ��Ԫ�ء� 

�ɰ����ĵ��еĽ��Ϳ�֪��read()����ÿ��ֻ�ܶ�ȡһ���ֽڣ�����Ҳֻ�ܶ�ȡ��ASCII�뷶Χ�ڵ�һЩ�ַ�����Щ�ַ�
��Ҫ������ʾ�ִ�Ӣ���������ŷ���ԡ������ں��ֵ�unicode�е��ַ�����������ȡ��ֻ�����������ʽ��ʾ�� 

����read()����������ȱ�㣬��read(byte[] b)����õ��˽�������ú�����������һ������ռ�������ֽڣ����
�԰Ѳ�������b����Ϊ��СΪ2�����鼴��������ȡ�����ˡ���ȻbҲ���Զ���Ϊ���󣬱������b=new byte[4]�Ļ�����
ÿ�ο��Զ�ȡ���������ַ��ˣ�������Ҫע����ǣ�����˴�����b �Ĵ�СΪ3��7�������������ȫ�Ǻ��ֵ�һƪ�ĵ���
��ȫ��������д�ˡ� 
 * 
 * 
 */
