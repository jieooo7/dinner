package com.cloud.app.other;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 从网络获取json数据
 * com.cloud.app.http.HttpPost
 * @author Andrew Lee <br/>
 * create at 2014年6月5日 下午2:11:19
 */
public class HttpGet {
	


	public HttpGet(){
		
	}
	
	//从网络获取数据
	public  static String getJsonContent(String url_path){
		
		try{
			URL url=new URL(url_path);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(3000);//设置超时
			connection.setRequestMethod("GET");//请求方式为get，另一种为post
			connection.setDoInput(true);//设置可以读取网络的数据，通过connection.getInputStream().read()
			
			int code=connection.getResponseCode();//获取状态吗
			if(code==200)
				{
					return changeInputStream(connection.getInputStream());
				//changeInputStream()必须设置为static，此处调用(静态方法内部不能使用this调用)
				//静态方法在访问本类的成员时，只允许访问静态成员
				}
		}catch(Exception e){
			
		}
		
		return "";
		
	}

//讲字节流转换成字符串
	private  static String changeInputStream(InputStream inputStream) {
		// TODO Auto-generated method stub
		String jsonString="";
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();//字节数组输出流
		//ByteArrayOutputStream 中的数据
		//被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。
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
 * read()与read(byte[] b)这两个方法在抽象类InputStream中前者是作为抽象方法存在的，后者不是，JDK API
 * 中是这样描述两者的： 

1：read() : 
从输入流中读取数据的下一个字节，返回0到255范围内的int字节值。如果因为已经到达流末尾而没有可用的字节，则返回-1。
在输入数据可用、检测到流末尾或者抛出异常前，此方法一直阻塞。 

2：read(byte[] b) :  
从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。以整数形式返回实际读取的字节数。在输入数据可用、检测到
文件末尾或者抛出异常前，此方法一直阻塞。如果 b 的长度为 0，则不读取任何字节并返回 0；否则，尝试读取至少一个字节。
如果因为流位于文件末尾而没有可用的字节，则返回值 -1；否则，至少读取一个字节并将其存储在 b 中。将读取的第一个字节
存储在元素 b[0] 中，下一个存储在 b[1] 中，依次类推。读取的字节数最多等于 b 的长度。设 k 为实际读取的字节数；
这些字节将存储在 b[0] 到 b[k-1] 的元素中，不影响 b[k] 到 b[b.length-1] 的元素。 

由帮助文档中的解释可知，read()方法每次只能读取一个字节，所以也只能读取由ASCII码范围内的一些字符。这些字符
主要用于显示现代英语和其他西欧语言。而对于汉字等unicode中的字符则不能正常读取。只能以乱码的形式显示。 

对于read()方法的上述缺点，在read(byte[] b)中则得到了解决，就拿汉字来举例，一个汉字占有两个字节，则可
以把参数数组b定义为大小为2的数组即可正常读取汉字了。当然b也可以定义为更大，比如如果b=new byte[4]的话，则
每次可以读取两个汉字字符了，但是需要注意的是，如果此处定义b 的大小为3或7等奇数，则对于全是汉字的一篇文档则不
能全部正常读写了。 
 * 
 * 
 */
