/*
 * FragmentDish.java
 * classes : com.cloud.app.fragment.FragmentDish
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月27日 上午11:18:55
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloud.app.dinner.R;

/**
 * com.cloud.app.fragment.FragmentDish
 * @author Andrew Lee <br/>
 * create at 2014年6月27日 上午11:18:55
 */
public class FragmentDish {
	private static final String TAG = "FragmentDish";
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		        View V = inflater.inflate(R.layout.fragment_dish, container, false);

		        return V;
	
}
}
