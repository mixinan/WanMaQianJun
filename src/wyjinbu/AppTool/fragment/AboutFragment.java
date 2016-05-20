package wyjinbu.AppTool.fragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import wyjinbu.AppTool.R;
import wyjinbu.AppTool.WebViewActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment implements View.OnClickListener {

    private static AboutFragment mAboutFragment;
    private Context mContext;
    
    @Bind(R.id.tv_version) TextView tvVersion;
    @Bind(R.id.tv_daimajia) TextView tvDaiMaJia;
    @Bind(R.id.tv_gank) TextView tvGank;
    @Bind(R.id.tv_satan) TextView tvSatan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        mContext = getActivity();
        
        ButterKnife.bind(this,view);
        
        initListener();
        initData();
        return view;
    }


    private void initData() {
        try {
            String version = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            tvVersion.setText("Version " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        tvDaiMaJia.setOnClickListener(this);
        tvGank.setOnClickListener(this);
        tvSatan.setOnClickListener(this);
    }

    public static AboutFragment newInstance() {
        if (mAboutFragment == null) {
            mAboutFragment = new AboutFragment();
        }
        return mAboutFragment;
    }


    @Override
    public void onClick(View v) {
    	String desc = null;
    	String url = null;
    	
        switch (v.getId()) {
            case R.id.tv_daimajia:
            	desc = mContext.getResources().getString(R.string.daimajia);
            	url = "https://github.com/daimajia";
                break;
            case R.id.tv_gank:
            	desc = mContext.getResources().getString(R.string.gank);
            	url = "http://gank.io";
                break;
            case R.id.tv_satan:
            	desc = mContext.getResources().getString(R.string.satan);
            	url = "https://github.com/wenjue";
                break;
        }
        
        Intent intent = new Intent();
        intent.putExtra("click", url);
        intent.putExtra("title", desc);
        intent.setClass(mContext, WebViewActivity.class);
        startActivity(intent);
    }
}
