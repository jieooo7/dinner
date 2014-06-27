/*
 * FragmentRes.java
 * classes : com.cloud.app.fragment.FragmentRes
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月27日 上午11:19:59
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloud.app.dinner.R;

/**
 * com.cloud.app.fragment.FragmentRes
 * @author Andrew Lee <br/>
 * create at 2014年6月27日 上午11:19:59
 */
public class FragmentRes {
	private static final String TAG = "FragmentRes";
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		        View V = inflater.inflate(R.layout.fragment_res, container, false);

		        return V;
	
}
}
