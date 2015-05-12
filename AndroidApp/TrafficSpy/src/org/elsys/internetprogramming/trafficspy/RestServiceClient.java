package org.elsys.internetprogramming.trafficspy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;
import android.util.Log;

public class RestServiceClient extends AsyncTask<String, Void, String> {
	public final static String GET = "GET";
	public final static String POST = "POST";
	public final static String DELETE = "DELETE";
	
	public static String COOKIES; 
	public static String USER_EMAIL;
	
	private final String TAG = getClass().getSimpleName();
	private RestClientCallback callback;
	private String method;
	
	private boolean isExecuting = false;
	
	@Override
	protected void onPreExecute() {
		this.isExecuting = true;
	}

	public RestServiceClient(RestClientCallback callback) {
		this.callback = callback;
	}
	
	public void doGet(String url) {
		Log.i(TAG, "doGet(" + url + ")");
		this.method = RestServiceClient.GET;
		this.execute(url);
	}
	
	public void doPost(String url, String data) {
		Log.i(TAG, "doPost(" + url + ")");
		Log.i(TAG, data);
		this.method = RestServiceClient.POST;
		final String[] params = {url, data};
		this.execute(params);
	}
	
	public void doDelete(String url, String id) {
		url = url.replace("<id>", id);
		Log.i(TAG, "doDelete(" + url + ")");
		this.method = RestServiceClient.DELETE;
		this.execute(url);
	}
	
	@Override
	protected String doInBackground(String... params) {
		final RestTemplate restTemplate = new RestTemplate();
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Cookie", COOKIES);
		
		
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		ResponseEntity<String> responseEntity;
		switch (this.method) {
		case RestServiceClient.GET:
			final HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
			
			responseEntity = restTemplate.exchange(params[0], HttpMethod.GET, httpEntity, String.class);
			
			Log.i(TAG, responseEntity.getBody());
			return responseEntity.getBody();
		case RestServiceClient.POST:
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			final HttpEntity<String> httpEntityPost = new HttpEntity<String>(params[1], httpHeaders);
			responseEntity = restTemplate.exchange(params[0], HttpMethod.POST, httpEntityPost, String.class);
			
			return responseEntity.getBody();
		case RestServiceClient.DELETE:
			final HttpEntity<String> httpEntityDelete = new HttpEntity<String>(null, httpHeaders);
			
			responseEntity = restTemplate.exchange(params[0], HttpMethod.DELETE, httpEntityDelete, String.class);
			return responseEntity.getStatusCode().toString();
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(String response) {
		this.isExecuting = false;
		this.callback.onResponse(response);
	}
	
	public boolean isExecuting() {
		return this.isExecuting;
	}

}
