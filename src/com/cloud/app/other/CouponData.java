package com.cloud.app.other;

/**
 * json封装的格式
 * com.cloud.app.http.CouponData
 * @author Andrew Lee <br/>
 * create at 2014年6月5日 下午2:11:57
 */
public class CouponData {
	
	private String imageSrc;
	private String textDesc;
	
	
	
	
	public CouponData(){
		
	}
	
	
	public CouponData(String imageSrc,String textDesc){
		
		this.imageSrc=imageSrc;
		this.textDesc=textDesc;
		
	}


	
	
	
	public String toString(){
		
		return "CouponData [imageSrc="+imageSrc+",textDesc="+textDesc+"]";
		
	}
	
	public String getImageSrc(){
		
		return imageSrc;
	}
	
	
	public String getTextDesc(){
		
		return textDesc;
	}
	
	public void setImageSrc(String imageSrc){
		
		this.imageSrc=imageSrc;
	}
	
	public void setTextDesc(String textDesc){
		
		this.textDesc=textDesc;
	}

}
