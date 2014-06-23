/*
 * Welcome.java
 * classes : com.cloud.app.dinner.Welcome
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月23日 上午10:00:11
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.dinner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

/**
 * com.cloud.app.dinner.Welcome
 * @author Andrew Lee <br/>
 * create at 2014年6月23日 上午10:00:11
 */
public class WelcomeActivity extends Activity {
	private static final String TAG = "WelcomeActivity";
	
	 protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏        
	        setContentView(R.layout.welcome);
	        //启动线程
	        Thread mt = new Thread(mThread);
	        mt.start();
	    }
	    

	    private Handler mHandler = new Handler(){
	        
	        @Override
	        public void handleMessage(Message msg) {
	            // TODO Auto-generated method stub
	            super.handleMessage(msg);

	            if((String)msg.obj == TAG) {
	                //跳转
	                Intent intent = new Intent();
	                intent.setClass(WelcomeActivity.this, CouponActivity.class);
	                WelcomeActivity.this.startActivity(intent); 
	                finish();
	            }
	        }
	    };
	    
	    Runnable mThread = new Runnable() {
	        @Override
	        public void run() {
	            // TODO Auto-generated method stub
	            Message msg = mHandler.obtainMessage();
	            //延时3秒
	            try {
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            msg.obj = TAG;
	            mHandler.sendMessage(msg);
	        }
	        
	    };

}
