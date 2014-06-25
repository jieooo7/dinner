/*
 * MainActivity.java
 * classes : com.cloud.app.dinner.MainActivity
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��25�� ����9:27:36
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.dinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * com.cloud.app.dinner.MainActivity
 * @author Andrew Lee <br/>
 * create at 2014��6��25�� ����9:27:36
 */
@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	
	/**
	 * menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼�֡�
	 */
	private ImageView menuButton;
	/**
	 * �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ�����ء�
	 */
	private SlidingLayout slidingLayout;
	private View mView;
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.main);
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		menuButton = (ImageView) findViewById(R.id.left_button);
		mView=(View) findViewById(R.id.content);
//		// �����������¼�����contentListView��
		slidingLayout.setScrollEvent(mView); 
		menuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ʵ�ֵ��һ��menuչʾ��಼�֣��ٵ��һ��������಼�ֵĹ���
				if (slidingLayout.isLeftLayoutVisible()) {
					slidingLayout.scrollToRightLayout();
				} else {
					slidingLayout.scrollToLeftLayout();
				}
			}
		});
		
	}
	

}
