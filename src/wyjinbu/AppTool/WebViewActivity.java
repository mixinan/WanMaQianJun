package wyjinbu.AppTool;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;


public class WebViewActivity extends Activity {

	private String url,title;

	@Bind(R.id.ib_chrome) ImageButton ibChrome;
	@Bind(R.id.wb_view) WebView mWebView;
	@Bind(R.id.pb_view) ProgressBar mProgressBar;
	@Bind(R.id.tv_webview_title) TextView tvTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_web_view);

		ButterKnife.bind(this);
		setWebview();
		initData();
		initListener();
	}


	private void initData() {
		Intent intent = getIntent();
		url = intent.getStringExtra("click");
		title = intent.getStringExtra("title");
		tvTitle.setText(title);
		mWebView.loadUrl(url);
	}

	
	@SuppressLint("SetJavaScriptEnabled")
	private void setWebview() {
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				mProgressBar.setProgress(newProgress);
				if (newProgress == 100) {
					mProgressBar.setVisibility(View.GONE);
				} else {
					mProgressBar.setVisibility(View.VISIBLE);
				}
			}

		});
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}


	private void initListener() {
		ibChrome.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse(url);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mWebView.canGoBack()) {
				mWebView.goBack();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	
	@Override
	protected void onPause() {
		super.onPause();
		mWebView.onPause();
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		mWebView.onResume();
	}

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWebView.destroy();
		ButterKnife.unbind(this);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
