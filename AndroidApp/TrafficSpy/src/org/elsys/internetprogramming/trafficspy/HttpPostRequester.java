package org.elsys.internetprogramming.trafficspy;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import android.util.Log;

public class HttpPostRequester extends HTTPRequester {
	private final String TAG = getClass().getSimpleName();

	protected HttpPostRequester(String url, String body,
			HttpRequesterCallback httpRequesterCallback)
			throws URISyntaxException {

		super(new HttpPost(new URI(url)), httpRequesterCallback);
		addBody(body);
		Log.i(TAG, "===== HttpPostRequest =====");
	}

	public void addBody(String body) {
		try {
			Log.i(TAG, "body: \n" + body);

			((HttpPost) super.getHttpRequestBase()).setEntity(new StringEntity(
					body));
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "new StringEntity(body) ERROR");
			e.printStackTrace();
		}
	}
}
