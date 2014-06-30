/*
 * MainActivity.java
 * classes : com.cloud.app.dinner.MainActivity
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��25�� ����9:27:36
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.dinner;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloud.app.fragment.FragmentCoupon;
import com.cloud.app.fragment.FragmentDish;
import com.cloud.app.fragment.FragmentRes;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * com.cloud.app.dinner.MainActivity
 * 
 * @author Andrew Lee <br/>
 *         create at 2014年6月27日 下午5:39:18
 */
public class MainActivity extends FragmentActivity implements OnClickListener {
	private static final String TAG = "MainActivity";
	private FragmentTabHost mTabHost;
	private View indicator1 = null;
	private View indicator2 = null;
	private View indicator3 = null;
	private SlidingMenu menu;
	private ImageView ivUser;
	private ImageView ivSearch;
	private EditText editSearch;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.right_content);

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);
		indicator1 = getIndicatorView("优惠信息", R.layout.tab_wid);
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(indicator1),
				FragmentCoupon.class, null);
		indicator2 = getIndicatorView("菜品推荐", R.layout.tab_wid);
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(indicator2),
				FragmentDish.class, null);
		indicator3 = getIndicatorView("餐厅推荐", R.layout.tab_wid);
		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator(indicator3),
				FragmentRes.class, null);

		ivUser = (ImageView) findViewById(R.id.left_button);
		ivUser.setOnClickListener(this);
		ivSearch = (ImageView) findViewById(R.id.right_search_button);
		ivSearch.setOnClickListener(this);
		editSearch = (EditText) findViewById(R.id.right_search_edit);
		
		// initSlidingMenu();
		// SlidingMenu menu = new SlidingMenu(this);
		// menu.setMode(SlidingMenu.LEFT);

	}

	public void initSlidingMenu() {
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		// menu.setShadowDrawable(R.drawable.bt_download);// 设置 SlidingMenu
		// 边缘的阴影效果
		// menu.setShadowWidthRes(R.dimen.SlidingMenu_WidthRes_margin);// 设置

		// menu.setBehindWidth(500);//设置 SlidingMenu 的宽度 和下面的设置二选一
		menu.setBehindOffsetRes(R.dimen.slide);// 设置SlidingMenu主页面的宽度
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);// 允许从屏幕的什么范围滑出SlidingMenu
		menu.setMenu(R.layout.left_menu);// 设置SlidingMenu使用的layout文件
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);// 附加到activity上
		menu.toggle();

	}
	

	private View getIndicatorView(String name, int layoutId) {
		View v = getLayoutInflater().inflate(layoutId, null);
		TextView tv = (TextView) v.findViewById(R.id.tabText);
		tv.setText(name);
		return v;
	}

	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mTabHost = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_button:
			initSlidingMenu();
//			menu.setMode(SlidingMenu.LEFT);
			break;

		case R.id.right_search_button:
			if (editSearch.getVisibility() == View.GONE) {
				editSearch.setVisibility(View.VISIBLE);
			} else {
				editSearch.setVisibility(View.GONE);
			}
			break;

		default:
			break;
		}
	}

}