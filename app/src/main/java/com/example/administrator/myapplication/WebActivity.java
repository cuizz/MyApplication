package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 需要加载网页的共通Activity<br/>
 * 跳转此页面的时候需要提供一个访问的web地址，具体跳转方法如下：<br/>
 * Intent intent = new Intent();<br/>
 * intent.setClass(this, WebActivity.class);<br/>
 * Bundle bundle = new Bundle();<br/>
 * bundle.putString(WebActivity.KEY_URL, "http://www.google.com");<br/>
 * intent.putExtras(bundle);<br/>
 * startActivity(intent);<br/>
 * 将"http://www.google.com"地址替换为自己需要访问的web地址就可以使用了,不需要再作其它任何操作。
 *
 * @author yang-chen
 */
public class WebActivity extends Activity {
	public static final String KEY_URL = "KEY_URL";
	public static final String KEY_TITLE = "title";

	private String mLinkUrl = "";

	private WebView mWebView = null;
	/**
	 * 消息：网络连接失败
	 */
	private final int MSG_LINK_NET_ERROR = 0;
	/**
	 * 消息：生成等待对话框
	 */
	private final int MSG_PROGRESS_DLG = 1;
	/**
	 * 消息：关闭对话框
	 */
	private final int MSG_DLG_CLOSE = 2;
	/**
	 * 对话框
	 */
	private Dialog mDialog = null;
	ProgressDialog mpDialog;
	RelativeLayout relativeLayout;
	boolean iscui = true;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//取消标题
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//取消状态栏
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		//	WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_web);
		//修改状态栏颜色
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(this.getResources().getColor(R.color.zhuangtailan));
			//底部导航栏
			//window.setNavigationBarColor(activity.getResources().getColor(colorResId));
		}
		init();
		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.

	}

	private void init() {
		//	TitleBarView titleBarView = (TitleBarView) findViewById(R.id.title_bar);
		//titleBarView.setListener(this);
		mWebView = (WebView) findViewById(R.id.web_view);
		relativeLayout = (RelativeLayout) findViewById(R.id.rela);
		//获取状态栏高度
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		//	WindowManager wm = (WindowManager) this
		//		.getSystemService(Context.WINDOW_SERVICE);
		//int width = wm.getDefaultDisplay().getWidth();
		//int height = wm.getDefaultDisplay().getHeight();
		//RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		//lp.setMargins(-result, 0, 0, 0);
		//relativeLayout.setLayoutParams(lp);
		//RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)mWebView.getLayoutParams();
		//layoutParams.setMargins(-result,0,0,0);
		//relativeLayout.setLayoutParams(layoutParams);
		mWebView.getSettings().setJavaScriptEnabled(true);
		// 设置WebView属性，能够执行Javascript脚本
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.setBackgroundColor(0);

//		mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			mLinkUrl = bundle.getString(KEY_URL);
			if (bundle.getString(KEY_TITLE) != null) {
				//titleBarView.setTitle(bundle.getString(KEY_TITLE));
			}
			// mLinkUrl = bundle.getString(Constants.KEY_PIC_DETAIL);
		}

		if (mLinkUrl.equals("")) {
			return;
		}

		//String mISNoPicState = SharedPreferencesUtil.getFromFileByDefault(WebActivity.this,Constants.KEY_NO_IMG, "0");
		// 判断是否打开了WIFI
//		if (mISNoPicState.equals("0") || Util.isWifiConnected(WebActivity.this))
		if (iscui) {

			try {
				// 打开等待对话框
				mHandler.sendEmptyMessage(MSG_PROGRESS_DLG);
				// 加载需要显示的网页
				mWebView.loadUrl(mLinkUrl);
				mWebView.setWebViewClient(new WebViewClient() {

					//在页面开始加载时调用
					@Override
					public void onPageStarted(WebView view, String url, Bitmap favicon) {
						mpDialog = new ProgressDialog(WebActivity.this, ProgressDialog.STYLE_SPINNER);
						//mpDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//设置风格为圆形进度条
						mpDialog.setMessage("正在加载，请稍后。。。");
						mpDialog.setCancelable(true);

						mpDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
							@Override
							public void onCancel(DialogInterface dialog) {
								dialog.dismiss();
								//cancel(true);
							}
						});
						mpDialog.show();
						super.onPageStarted(view, url, favicon);
					}

					//页面加载结束时调用
					@Override
					public void onPageFinished(WebView view, String url) {

						mpDialog.dismiss();
						super.onPageFinished(view, url);
					}

					//在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
					@Override
					public boolean shouldOverrideUrlLoading(WebView view,
															String url) {

						view.loadUrl(url);
						//调用拨号程序
						if (url.startsWith("tel:")) {
							Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
							startActivity(intent);
							view.goBack();
						}
						return true;
					}


				});
			} catch (Exception e) {
				mHandler.sendEmptyMessageDelayed(MSG_LINK_NET_ERROR, 100);
				e.printStackTrace();
			}

		} else {
			mHandler.sendEmptyMessageDelayed(MSG_DLG_CLOSE, 100);

			// 对话框提示用户是否去打开WIFI
			mHandler.sendEmptyMessageDelayed(MSG_LINK_NET_ERROR, 100);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//return super.onKeyDown(keyCode, event);
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		} else {
			finish();
			return false;
		}
	}

	/**
	 * 消息处理的handler
	 */
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case MSG_LINK_NET_ERROR:
					Toast.makeText(
							WebActivity.this,
							"",
							Toast.LENGTH_LONG).show();
					//	Util.closeDialog();
					break;
				case MSG_PROGRESS_DLG:
					//Util.showProgressDialog(WebActivity.this, (WebActivity.this)
					//	.getResources().getString(R.string.data_loading));
					break;
				case MSG_DLG_CLOSE:
					//	Util.closeDialog();
					break;
				default:
					break;
			}
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onDestroy() {
		destoryWebView();
		super.onDestroy();
	}

	/**
	 * 释放webiew
	 */
	private void destoryWebView() {
		CookieSyncManager.createInstance(this);
		CookieSyncManager.getInstance().startSync();
		CookieManager.getInstance().removeSessionCookie();
		//Util.closeDialog();
		//清理webview的缓存
		mWebView.clearCache(true);
		mWebView.destroyDrawingCache();
		mWebView = null;
	}

}
