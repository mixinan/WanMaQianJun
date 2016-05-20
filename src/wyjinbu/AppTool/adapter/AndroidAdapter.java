package wyjinbu.AppTool.adapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import wyjinbu.AppTool.R;
import wyjinbu.AppTool.adapter.AndroidAdapter.AndroidViewHolder;
import wyjinbu.AppTool.entity.Android;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class AndroidAdapter extends Adapter<AndroidViewHolder> {
	private Context context;
	private ArrayList<Android> data;

	
	public AndroidAdapter(Context context, ArrayList<Android> data) {
		super();
		this.context = context;
		if (data==null) data = new ArrayList<Android>();
		this.data = data;
	}


	@Override
	public int getItemCount() {
		return data==null ? 0 : data.size();
	}


	@Override
	public AndroidViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		View view = LayoutInflater.from(context).inflate(R.layout.item_android, parent, false);
		return new AndroidViewHolder(view);
	}


	@Override
	public void onBindViewHolder(final AndroidViewHolder holder, int position) {
		Android a = data.get(position);
		holder.tvTime.setText(a.getTime().trim().substring(0, 10));
		holder.tvDesc.setText(a.getDesc());

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

	class AndroidViewHolder extends RecyclerView.ViewHolder{
		@Bind(R.id.tv_android_item_time) TextView tvTime;
		@Bind(R.id.tv_android_item_desc) TextView tvDesc;

		public AndroidViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	/**
	 * ����¼��ӿ�
	 */
	private OnItemClickListener listener;

	public interface OnItemClickListener{
		void onItemClick(View view, int position);
		void onItemLongClick(View view, int position);
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener){
		this.listener = onItemClickListener;
	}
	
	
	public void addData(int position){
        data.add(position,new Android("2016-01-01","http://guoxingnan.cc","ʱ�䱦��-ѡ�����Ҫ"));        
        notifyItemInserted(position);
    }

    public void removeData(int position){
    	data.remove(position);
        notifyItemRemoved(position);
    }

}
