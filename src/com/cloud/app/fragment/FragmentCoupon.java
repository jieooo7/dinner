/*
 * FragmentCoupon.java
 * classes : com.cloud.app.fragment.FragmentCoupon
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014��6��27�� ����11:16:02
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.fragment;

import com.cloud.app.dinner.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * com.cloud.app.fragment.FragmentCoupon
 * @author Andrew Lee <br/>
 * create at 2014��6��27�� ����11:16:02
 */
public class FragmentCoupon extends Fragment{
	private static final String TAG = "FragmentCoupon";
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 return inflater.inflate(R.layout.fragment_coupon, container, false);
	
}
}
