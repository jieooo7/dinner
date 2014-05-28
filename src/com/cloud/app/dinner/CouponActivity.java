package com.cloud.app.dinner;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;




public class CouponActivity extends Activity{
	private GridView lv;
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		lv=(GridView)findViewById(R.id.gridView1);
//		from data value
//		String[] item=new String[]{"image1","text1","image2","text2","image3","text3","image4","text4"};
//		to data view
//		int[] itemId=new int[]{R.id.imageView2,R.id.textview2,R.id.imageView3,R.id.textview3,R.id.imageView4,R.id.textview4,R.id.imageView5,R.id.textview5};
//		key,value  array
		String[] item=new String[]{"image1","text1"};
		int[] itemId=new int[]{R.id.imageView2,R.id.textview2};
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(int i=0;i<50;i++){
//			(key,value) set
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
	
}
/*public class Coupon extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

   /* public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}*/

