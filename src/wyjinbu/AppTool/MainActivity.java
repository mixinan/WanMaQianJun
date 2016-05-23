package wyjinbu.AppTool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wyjinbu.AppTool.fragment.AboutFragment;
import wyjinbu.AppTool.fragment.AndroidFragment;
import wyjinbu.AppTool.fragment.RandomFragment;
import wyjinbu.AppTool.fragment.WelfareFragment;
import wyjinbu.AppTool.util.ToastUtil;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {
	private ViewPager mViewPager;
	private List<Fragment> mFragments;
	private FragmentPagerAdapter mAdapter;

	private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȥ��������ͼ��
		getActionBar().setDisplayShowHomeEnabled(false);
		setContentView(R.layout.activity_main);
		
		initView();
		initData();

		mViewPager.setAdapter(mAdapter);
		mViewPager.addOnPageChangeListener(this);
		//��������ڵ�Fragment�Զ���������
		mViewPager.setOffscreenPageLimit(3);
	}

	private void initData() {
		mFragments = new ArrayList<Fragment>();
		Collections.addAll(mFragments, 
				WelfareFragment.newInstance(),
				AndroidFragment.newInstance(),
				RandomFragment.newInstance(),
				AboutFragment.newInstance());

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int position) {
				return mFragments.get(position);
			}
		};
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.tab_welfare);
		mTabIndicators.add(one);
		ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.tab_Android);
		mTabIndicators.add(two);
		ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.tab_random);
		mTabIndicators.add(three);
		ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.tab_about);
		mTabIndicators.add(four);

		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);

		one.setIconAlpha(1.0f);
	}



	@Override
	public void onClick(View v) {
		
		resetOtherTabs();

		switch (v.getId()) {
		case R.id.tab_welfare:
			//��ǰ��Fragmentˢ������
//			currentFragmentRefresh();
			mTabIndicators.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0,false);
			break;
		case R.id.tab_Android:
			//��ǰ��Fragmentˢ������
//			currentFragmentRefresh();
			mTabIndicators.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1,false);
			break;
		case R.id.tab_random:
//			currentFragmentRefresh();
			mTabIndicators.get(2).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(2,false);
			break;
		case R.id.tab_about:
			mTabIndicators.get(3).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(3,false);
			break;
		}
	}
	
	
	/**
	 * ������ť��ɫȫ�����
	 */
	private void resetOtherTabs() {
		for (int i = 0; i < mTabIndicators.size(); i++) {
			mTabIndicators.get(i).setIconAlpha(0);
		}
	}
	
	
	/**
	 * ��ǰ��Fragmentˢ������
	 */
//	private void currentFragmentRefresh() {
//		if (mViewPager.getCurrentItem()==0) {
//			WelfareFragment.newInstance().initData();
//		}
//		if (mViewPager.getCurrentItem()==1) {
//			AndroidFragment.newInstance().initData();
//		}
//		if (mViewPager.getCurrentItem()==2) {
//			RandomFragment.newInstance().initData();
//		}
//	}

	
	/**
	 * ��ǰ�б���������
	 */
	private void currentFragmentListViewToTop() {
		if (mViewPager.getCurrentItem()==0) {
			WelfareFragment.newInstance().getListView().smoothScrollToPosition(0);
		}
		if (mViewPager.getCurrentItem()==1) {
			AndroidFragment.newInstance().getListView().smoothScrollToPosition(0);
		}
		if (mViewPager.getCurrentItem()==2) {
			RandomFragment.newInstance().getListView().smoothScrollToPosition(0);
		}
	}

	

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		if (positionOffset > 0) {
			ChangeColorIconWithText left = mTabIndicators.get(position);
			ChangeColorIconWithText right = mTabIndicators.get(position + 1);
			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.action_top:
			//��ǰFragment����б���������
			currentFragmentListViewToTop();
			break;
			
		case R.id.action_android:
			mTabIndicators.get(1).setIconAlpha(1.0f);
			resetTabColor(1);
			mViewPager.setCurrentItem(1,false);
			break;

		case R.id.action_scan:
			//	TODO ɨ���ά��
			ToastUtil.showToast(this, "�������Ƹù���");
			break;
			
		case R.id.action_qq:
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri net = Uri
					.parse("http://wpd.b.qq.com/cgi/get_m_sign.php?uin=2070906030");
			intent.setData(net);
			startActivity(intent);
			break;

		case R.id.action_about:
			mTabIndicators.get(3).setIconAlpha(1.0f);
			resetTabColor(3);
			mViewPager.setCurrentItem(3,false);
			break;
		}
		return true;
	}


	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId==Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	private void resetTabColor(int i) {
		mTabIndicators.get(0).setIconAlpha(0f);
		mTabIndicators.get(1).setIconAlpha(0f);
		mTabIndicators.get(2).setIconAlpha(0f);
		mTabIndicators.get(3).setIconAlpha(0f);
		mTabIndicators.get(i).setIconAlpha(1.0f);
	}

	
	@Override
	protected void onStop() {
		super.onStop();
		GankApplication.getRequestQueue().cancelAll("wmqj");
		//ȥ��Toast�ӳ�Ч��
		ToastUtil.cancelToast();
	}

	
	/**
	 * �ٰ�һ���˳�Ӧ��
	 */
	private long lastBackTime = 0;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - lastBackTime < 1500) {
			finish();
		}
		lastBackTime = System.currentTimeMillis();
		ToastUtil.showToast(this, "�ٰ�һ���˳�");
	}
}
