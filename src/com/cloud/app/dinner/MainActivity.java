/*
 * MainActivity.java
 * classes : com.cloud.app.dinner.MainActivity
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��25�� ����9:27:36
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.dinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	private TextView orderTv0;
	private TextView collectTv1;
	private TextView couponTv2;
	private TextView creditsTv3;
	private TextView accountTv4;
	private String orderStv0;
	private String collectStv1;
	private String couponStv2;
	private String creditsStv3;
	private String accountStv4;
	private LinearLayout line0;
	
	private SharedPreferences mainShared=null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); 
		FragmentManager manage = getSupportFragmentManager();
		setContentView(R.layout.right_content);
		mainShared=getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//		写入sharepreference 操作
		
		
		
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, manage, R.id.realcontent);
		indicator1 = getIndicatorView("优惠信息", R.layout.tab_wid);
		mTabHost.addTab(mTabHost.newTabSpec("tab0").setIndicator(indicator1),
				FragmentCoupon.class, null);
		indicator2 = getIndicatorView("菜品推荐", R.layout.tab_wid);
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator(indicator2),
				FragmentDish.class, null);
		indicator3 = getIndicatorView("餐厅推荐", R.layout.tab_wid);
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator(indicator3),
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
		initLeftMenu();
		line0=(LinearLayout)findViewById(R.id.line0);
		line0.setOnClickListener(this);
		
		
		

	}

	 private View getIndicatorView(String name, int layoutId) {
		View v = getLayoutInflater().inflate(layoutId, null);
		TextView tv = (TextView) v.findViewById(R.id.tabText);
		tv.setText(name);
		return v;
	}
	 
	 
	protected void initLeftMenu(){
		
		this.orderStv0=mainShared.getString("order", "0");
		this.collectStv1=mainShared.getString("collect", "0");
		this.couponStv2=mainShared.getString("coupon", "0");
		this.creditsStv3=mainShared.getString("credits", "0");
		this.accountStv4=mainShared.getString("account", "0");
		this.orderTv0=(TextView)findViewById(R.id.orderTv0);
		this.collectTv1=(TextView)findViewById(R.id.collectTv1);
		this.couponTv2=(TextView)findViewById(R.id.couponTv2);
		this.creditsTv3=(TextView)findViewById(R.id.creditsTv3);
		this.accountTv4=(TextView)findViewById(R.id.accountTv4);
		this.orderTv0.setText(orderStv0);
		this.collectTv1.setText(collectStv1);
		this.couponTv2.setText(couponStv2);
		this.creditsTv3.setText(creditsStv3);
		this.accountTv4.setText(accountStv4);
		
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
			// menu.setMode(SlidingMenu.LEFT);
			break;

		case R.id.right_search_button:
			if (editSearch.getVisibility() == View.GONE) {
				editSearch.setVisibility(View.VISIBLE);
			} else {
				editSearch.setVisibility(View.GONE);
			}
			break;
			
		case R.id.line0:
//			initSlidingMenu();
			menu.toggle();
			break;

		default:
			break;
		}
	}

}