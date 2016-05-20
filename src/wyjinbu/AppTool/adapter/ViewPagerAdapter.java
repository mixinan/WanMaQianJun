package wyjinbu.AppTool.adapter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import wyjinbu.AppTool.GankApplication;
import wyjinbu.AppTool.ImageGalleryActivity;
import wyjinbu.AppTool.R;
import wyjinbu.AppTool.entity.Girl;
import wyjinbu.AppTool.view.TouchImageView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;


public class ViewPagerAdapter extends PagerAdapter {

	private List<Girl> data;
	private Context mContext;
	private Bitmap mBitmap;
	private final static String PICTURE_PATH = Environment  
			.getExternalStorageDirectory()+"/girls/";  

	public ViewPagerAdapter(Context context, List<Girl> data) {
		this.data = data;
		mContext = context;
	}

	@Override
	public int getCount() {
		if (data != null) {
			return data.size();
		}
		return 0;
	}


	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}


	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.view_pager_item, null);
		final TouchImageView iv = (TouchImageView) view.findViewById(R.id.sdv_view);

		//显示图片
//		Glide.with(mContext).load(girl.getUrl())
//		.placeholder(R.drawable.icon_image_loading).error(R.drawable.icon_image_failure)
//		.diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);

		//第二种显示图片的方式
		//		ImageLoader loader = new ImageLoader(GankApplication.getRequestQueue(), new BitmapCache());
		//		ImageListener listener = ImageLoader.getImageListener(iv, R.drawable.wujiao1, R.drawable.welfare);
		//		loader.get(girl.getUrl(), listener);

		//第三种显示图片的方式
		//		ImageLoader loader = new ImageLoader(GankApplication.getRequestQueue(), new BitmapCache());
		//		iv.setDefaultImageResId(R.drawable.linzhiling);
		//		iv.setErrorImageResId(R.drawable.linzhiling);
		//		iv.setImageUrl(girl.getUrl(),loader);

		//下载图片
		ImageRequest request = new ImageRequest(data.get(position).getUrl(), 
				new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap bitmap) {
				mBitmap = bitmap;
				iv.setImageBitmap(mBitmap);
			}
		}, 0, 0, ScaleType.CENTER_INSIDE, Config.RGB_565, 
		new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				Toast.makeText(mContext, "获取数据失败", Toast.LENGTH_SHORT).show();
				iv.setImageResource(R.drawable.wujiao1);
			}
		});
		request.setTag("wmqj");
		GankApplication.getRequestQueue().add(request);

		
		// 点击图片，返回图片列表
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((ImageGalleryActivity)mContext).finish();
			}
		});
		
		//长按图片，保存
		iv.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				//保存图片到手机
				Thread d = new downLoadImageThread();
				d.start();
				Toast.makeText(mContext, "图片已保存到"+PICTURE_PATH+"文件夹", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		
		container.addView(view);
		return view;
	}


	//下载图片的子线程
	class downLoadImageThread extends Thread{
		@Override
		public void run() {
			try {
				saveMyBitmap(mBitmap, PICTURE_PATH);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//保存图片
	@SuppressLint("SdCardPath")
	public void saveMyBitmap(Bitmap bitmap,String path) throws Exception  {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-HH-mm-ss", Locale.CHINA);
		String name = sdf.format(new Date())+".png";

		File file = new File(path, name);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
		bos.flush();
		bos.close();

		// 把文件插入到系统图库
		try {
			MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
					file.getAbsolutePath(), name, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 最后通知图库更新
		mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
	}
}
