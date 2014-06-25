/*
 * MainActivity.java
 * classes : com.cloud.app.dinner.MainActivity
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月25日 上午9:27:36
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
 * create at 2014年6月25日 上午9:27:36
 */
@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	
	/**
	 * menu按钮，点击按钮展示左侧布局，再点击一次隐藏左侧布局。
	 */
	private ImageView menuButton;
	/**
	 * 侧滑布局对象，用于通过手指滑动将左侧的菜单布局进行显示或隐藏。
	 */
	private SlidingLayout slidingLayout;
	private View mView;
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.main);
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		menuButton = (ImageView) findViewById(R.id.left_button);
		mView=(View) findViewById(R.id.content);
//		// 将监听滑动事件绑定在contentListView上
		slidingLayout.setScrollEvent(mView); 
		menuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 实现点击一下menu展示左侧布局，再点击一下隐藏左侧布局的功能
				if (slidingLayout.isLeftLayoutVisible()) {
					slidingLayout.scrollToRightLayout();
				} else {
					slidingLayout.scrollToLeftLayout();
				}
			}
		});
		
	}
	

}
