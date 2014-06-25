/*
 * DensityUtil.java
 * classes : com.cloud.app.utils.DensityUtil
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月25日 上午11:31:24
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.utils;

import android.content.Context;

/**
 * com.cloud.app.utils.DensityUtil
 * 
 * @author Andrew Lee <br/>
 *         create at 2014年6月25日 上午11:31:24
 */
public class DensityUtil {
	private static final String TAG = "DensityUtil";

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
