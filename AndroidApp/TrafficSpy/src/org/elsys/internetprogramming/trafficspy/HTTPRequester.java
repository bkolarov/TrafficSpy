package org.elsys.internetprogramming.trafficspy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class HTTPRequester extends HttpEntityEnclosingRequestBase {
	private final String TAG = getClass().getSimpleName();
	public static final int STATUS_OK = 200;
	
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	
	private HttpRequesterCallback httpRequesterCallback;
	private HttpClient httpClient;
	private HttpRequestBase httpRequestBase;

	private HttpResponse httpResponse;

	private HTTPRequester(HttpRequesterCallback httpRequesterCallback) {
		this.httpClient = new DefaultHttpClient();
		this.httpRequesterCallback = httpRequesterCallback;
	}

	protected HTTPRequester(HttpPost httpPost,
			HttpRequesterCallback httpRequesterCallback)
			throws URISyntaxException {
		
		this(httpRequesterCallback);
		this.httpRequestBase = httpPost;
	}

	protected HTTPRequester(HttpGet httpGet,
			HttpRequesterCallback httpRequesterCallback)
			throws URISyntaxException {
		
		this(httpRequesterCallback);
		this.httpRequestBase = httpGet;
	}

	protected HttpRequestBase getHttpRequestBase() {
		return this.httpRequestBase;
	}

	@Override
	public String getMethod() {
		if (this.httpRequestBase != null) {
			return this.httpRequestBase.getMethod();
		}
		return null;
	}

	public final void addHeader(String name, String value) {
		this.httpRequestBase.addHeader(name, value);
	}

	public final void execute() {
		new HttpRequestExecuter().execute();
	}

	private class HttpRequestExecuter extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				httpResponse = httpClient.execute(httpRequestBase);
				return getResponseBody(httpResponse);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "error";
		}

		@Override
		protected void onPostExecute(String result) {
			if (httpResponse != null) {
				if (httpResponse.getStatusLine().getStatusCode() == HTTPRequester.STATUS_OK && httpRequesterCallback != null) {
					httpRequesterCallback.onResponse(result);
				} else {
					Log.i(TAG, result);
				}
			} else {
				if (httpRequesterCallback != null) {
					httpRequesterCallback.onError(result);
				} else {
					Log.i(TAG, result);
				}
			}
		}

		private String getResponseBody(HttpResponse httpResponse)
				throws IOException {
			final BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(
							httpResponse.getEntity().getContent(), "UTF-8"));
			final StringBuilder stringBuilder = new StringBuilder();

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}

			return stringBuilder.toString();
		}
	}
}