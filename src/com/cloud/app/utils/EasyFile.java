/*
 * EasyFile.java
 * classes : com.cloud.app.utils.EasyFile
 * author Andrew Lee
 * V 1.0.0
 * Create at 2014年6月18日 下午5:47:09
 * Copyright: 2014 Interstellar Cloud Inc. All rights reserved.
 */
package com.cloud.app.utils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.cloud.app.debug.DebugUtil;
import com.cloud.app.http.GsonTools;
import com.google.gson.Gson;

import android.content.Context;
import android.net.Uri;

/**
 * 文件读写 com.cloud.app.utils.EasyFile
 * 
 * @author Andrew Lee <br/>
 *         create at 2014年6月18日 下午5:47:09
 */
public class EasyFile {
	private static final String TAG = "EasyFile";

	/**
	 * 写入list数据到文件
	 * 
	 * @param filePath
	 *            文件名
	 * @param list
	 *            list数据
	 * @return 写入或者不写入
	 */
	public static boolean writeFile(String filePath,
			List<Map<String, String>> list) {
		String fileContent = "";
		// 创建文件目录，没有则创建
		if (FileHelper.createDirectory(FileManager.getSaveFilePath() + "json/")) {
			Gson gson = new Gson();
			fileContent = gson.toJson(list);

			// 判断文件内容是否相等，否，则写入
			if ((fileContent.equals(readFileStr(filePath))) != true) {
				DebugUtil.i("file", "write file");
				return FileHelper.writeFile(FileManager.getSaveFilePath()
						+ "json/" + filePath, fileContent, false);
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 读取数据到list
	 * 
	 * @param filePath
	 *            文件名
	 * @return list数据
	 */
	public static List<Map<String, String>> readFile(String filePath) {

		InputStream is = FileHelper.readFile(FileManager.getSaveFilePath()
				+ "json/" + filePath);
		String st = "";
		try {
			st = new String(FileHelper.readAll(is));
		} catch (Exception e) {

		}
		List<Map<String, String>> list = GsonTools.getListMaps(st);
		return list;
	}

	/**
	 * 读取文件String类型
	 * 
	 * @param filePath
	 *            文件名
	 * @return String数据
	 */
	public static String readFileStr(String filePath) {
		InputStream is = FileHelper.readFile(FileManager.getSaveFilePath()
				+ "json/" + filePath);
		String st = "";
		try {
			st = new String(FileHelper.readAll(is));
		} catch (Exception e) {

		}
		return st;
	}
}
