package com.cloud.app.dinner;

import java.util.List;
import java.util.Map;

import com.cloud.app.cache.ImageLoader;
import com.cloud.app.debug.DebugUtil;
import com.cloud.app.http.MapPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * com.cloud.app.dinner.LoaderAdapter
 * @author Andrew Lee <br/>
 * create at 2014年7月1日 下午3:47:42
 */
public class LoaderAdapter extends BaseAdapter {

	private static final String TAG = "LoaderAdapter";
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	private ImageLoader mImageLoader;
	private Context mContext;
	private List<Map<String, String>> list;

	public LoaderAdapter(Context context, List<Map<String, String>> list) {
		this.list = list;
		this.mContext = context;
		mImageLoader = new ImageLoader(context);
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}

	@Override
	public int getCount() {
		return this.list.size();
	}

	@Override
	public Object getItem(int position) {
		return this.list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.gcoupon, null);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.textview2);
			viewHolder.mImageView = (ImageView) convertView
					.findViewById(R.id.imageView2);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.mImageView.setImageResource(R.drawable.ic_launcher);
		DebugUtil.i("test....result",
				"44444----" + list.get(position).get("img_url"));
		if (!mBusy) {
			mImageLoader.DisplayImage(
					MapPackage.PATH + list.get(position).get("img_url"),
					viewHolder.mImageView, false);
			viewHolder.mTextView.setText(list.get(position).get("goods_no"));
		} else {
			mImageLoader.DisplayImage(
					MapPackage.PATH + list.get(position).get("img_url"),
					viewHolder.mImageView, false);
			viewHolder.mTextView.setText(list.get(position).get("goods_no"));
		}
		return convertView;
	}

	static class ViewHolder {
		TextView mTextView;
		ImageView mImageView;
	}
}
