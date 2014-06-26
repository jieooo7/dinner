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
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * com.cloud.app.dinner.MainActivity
 * 
 * @author Andrew Lee <br/>
 *         create at 2014��6��25�� ����9:27:36
 */
public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private TabHost tabHost;
	private TabSpec tabSpec;

	/**
	 * menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼�֡�
	 */
	private ImageView menuButton;
	/**
	 * �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ�����ء�
	 */
	private SlidingLayout slidingLayout;
	private View mView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.main);
		

		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();

		tabSpec = tabHost
				.newTabSpec("one")
				.setIndicator("one")
					
				.setContent(R.id.onetab);
//						getResources().getDrawable(
//								R.drawable.tab_selector_coupon))
		tabHost.addTab(tabSpec);

		tabSpec = tabHost
				.newTabSpec("two")
				.setIndicator("one")
				.setContent(R.id.twotab);
		tabHost.addTab(tabSpec);

		tabSpec = tabHost
				.newTabSpec("three")
				.setIndicator("one")
				.setContent(R.id.threetab);
		tabHost.addTab(tabSpec);
		
		
		
		 tabHost.setCurrentTab(0);

		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		menuButton = (ImageView) findViewById(R.id.left_button);
		mView = (View) findViewById(R.id.content);
		// // �����������¼�����contentListView��
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
