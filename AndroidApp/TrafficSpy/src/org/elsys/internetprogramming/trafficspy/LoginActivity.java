package org.elsys.internetprogramming.trafficspy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private final String TAG = getClass().getSimpleName();
	private WebView loginWebView;
	private String cookies;
	private String userEmail;
	
	private boolean mapActivityStarted = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.init();
		this.setWebViewSettings();
		
		if (getIntent().getBooleanExtra("logout", false)) {
			Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
			this.loginWebView.loadUrl(URLs.URL_LOGOUT);
			this.loginWebView.clearCache(true);
		} else {
			this.loginWebView.loadUrl("http://ec2-52-28-51-57.eu-central-1.compute.amazonaws.com:8181/markers");	
		}
		
		
	}
	
	private void init() {
		this.loginWebView = (WebView) findViewById(R.id.webView);
		
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void setWebViewSettings() {
		final WebSettings webViewSettings = this.loginWebView.getSettings();
		webViewSettings.setJavaScriptEnabled(true);
		webViewSettings.setDomStorageEnabled(true);
		
		this.loginWebView.addJavascriptInterface(this, "Android");
		this.loginWebView.setWebChromeClient(new WebChromeClient());
		this.loginWebView.setWebViewClient(new WebViewClient() {
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				Log.i(TAG, "onPageStarted(): " + url);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				cookies = CookieManager.getInstance().getCookie(url);
				
				if (url.contains("markers")) {
					Log.i(TAG, cookies);
					Toast.makeText(getApplicationContext(), cookies, Toast.LENGTH_SHORT).show();
					startMapActivity();
				} 
			}
			
		});
	}
	
	private void startMapActivity() {
		if (!this.mapActivityStarted) {
			this.mapActivityStarted = true;
			
			final Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("cookie", this.cookies);
			intent.putExtra("email", this.userEmail);
			startActivity(intent);
			super.finish();
		}
	}
	
	@JavascriptInterface
	public void setUserEmail(String email) {
		this.userEmail = email;
	}
}
