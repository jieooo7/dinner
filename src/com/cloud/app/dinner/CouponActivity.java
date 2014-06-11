package com.cloud.app.dinner;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;











import com.cloud.app.debug.DebugUtil;
import com.cloud.app.http.HttpSendRecv;
import com.cloud.app.http.MapPackage;
import com.cloud.app.other.CouponData;
import com.cloud.app.utils.MD5;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;




public class CouponActivity extends Activity{
	private GridView lv;
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		
//		String url="http://115.29.13.164/recommend.do?t=309343089034";
//		MapPackage.setHead("-1","11111","1","1.1.2");
//		MapPackage.setPara("category_id", "0");
//		MapPackage.setRes("start", "1");
//		MapPackage.setRes("count", "3");
		
		
		MapPackage mp=new MapPackage();
		mp.setPath("recommend.do?t=309343089034");
		mp.setHead("-1", "11111","1.1.2");
		mp.setPara("category_id","0");
		mp.setRes("start", "1");
		mp.setRes("count", "3");
		

		DebugUtil.i("test....head",mp.backHead().get("code"));
		DebugUtil.i("test....para",mp.backPara().get("total"));
		DebugUtil.i("test....result",mp.backResult().get(1).get("goods_no"));
		lv=(GridView)findViewById(R.id.gridView1);

		
		
		String[] item=new String[]{"image1","text1"};
		int[] itemId=new int[]{R.id.imageView2,R.id.textview2};
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(int i=0;i<50;i++){

			map=new HashMap<String,Object>();
			map.put("image1", R.drawable.ic_launcher);
			map.put("text1",getString(R.string.first));
			
			list.add(map);
			
		}
		SimpleAdapter adapter=new SimpleAdapter(this, list, R.layout.gcoupon, item, itemId);
		
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener(){
			
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
                    long arg3){
				
				Intent intent=new Intent();
				intent.setClass(CouponActivity.this, ShowDetailActivity.class);
				startActivity(intent);
				
			}
		} 
		);
		
	}
	
	
	
	
	
//	private static final String SEP1 = "#";  
//    private static final String SEP2 = "|"; 
//    private static final String SEP3 = "="; 
//    /** 
//     * List转换String 
//     *  
//     * @param list 
//     *            :需要转换的List 
//     * @return String转换后的字符串 
//     */ 
//    public static String ListToString(List<?> list) {  
//        StringBuffer sb = new StringBuffer();  
//        if (list != null && list.size() > 0) {  
//            for (int i = 0; i < list.size(); i++) {  
//                if (list.get(i) == null || list.get(i) == "") {  
//                    continue;  
//                }  
//                // 如果值是list类型则调用自己  
//                if (list.get(i) instanceof List) {  
//                    sb.append(ListToString((List<?>) list.get(i)));  
//                    sb.append(SEP1);  
//                } else if (list.get(i) instanceof Map) {  
//                    sb.append(MapToString((Map<?, ?>) list.get(i)));  
//                    sb.append(SEP1);  
//                } else {  
//                    sb.append(list.get(i));  
//                    sb.append(SEP1);  
//                }  
//            }  
//        }  
//        return "L" + sb.toString();  
//    }  
//	  
//	  
//	  
//	  public static String MapToString(Map<?, ?> map) {  
//          StringBuffer sb = new StringBuffer();  
//          // 遍历map  
//          for (Object obj : map.keySet()) {  
//              if (obj == null) {  
//                  continue;  
//              }  
//              Object key = obj;  
//              Object value = map.get(key);  
//              if (value instanceof List<?>) {  
//                  sb.append(key.toString() + SEP1 + ListToString((List<?>) value));  
//                  sb.append(SEP2);  
//              } else if (value instanceof Map<?, ?>) {  
//                  sb.append(key.toString() + SEP1  
//                          + MapToString((Map<?, ?>) value));  
//                  sb.append(SEP2);  
//              } else {  
//                  sb.append(key.toString() + SEP3 + value.toString());  
//                  sb.append(SEP2);  
//              }  
//          }  
//          return "M" + sb.toString();  
//      }  
     
	
}


