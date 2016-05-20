package wyjinbu.AppTool.adapter;

import java.util.List;

import wyjinbu.AppTool.R;
import wyjinbu.AppTool.entity.Girl;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.ViewHolder> {
	private Context mContext;
	private List<Girl> data;

	public WelfareAdapter(Context context, List<Girl> data) {
		mContext = context;
		this.data = data;
	}

	@Override
	public WelfareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.item_welfare, parent, false);
		return new ViewHolder(view);
	}


	@Override
	public void onBindViewHolder(final WelfareAdapter.ViewHolder holder, final int position) {
		Girl girl = data.get(position);

		holder.tvTime.setText(girl.getCreatedAt().trim().substring(0, 10));

		String url = girl.getUrl();
		Glide.with(mContext).load(url).placeholder(R.color.stay_color).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(holder.mImageView);
		
		
		final int pos = holder.getLayoutPosition();
		if (listener != null) {
			holder.itemView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					listener.onItemClick(holder.itemView, pos);
				}

			});

			holder.itemView.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					listener.onItemLongClick(holder.itemView, pos);
					return false;
				}
			});
		}
	}

	
	@Override
	public int getItemCount() {
		return data.size();
	}


	class ViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.imageView) ImageView mImageView;
		@Bind(R.id.girl_tv_time) TextView tvTime;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}


	/**
	 * 点击事件接口
	 */
	private OnItemClickListener listener;

	public interface OnItemClickListener{
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}

	public void setOnItemClickListener(OnItemClickListener listener){
		this.listener = listener;
	}
	
	
	public void addData(int position){
		//添加 林志玲图片
        data.add(position,new Girl("https://raw.githubusercontent.com/mixinan/GankPractice/master/screenshot/linzhiling.jpg", (position+1)+"我们都爱林志玲"+"--"));
        notifyItemInserted(position);
    }

	
    public void removeData(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
}
