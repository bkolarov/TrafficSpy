package org.elsys.internetprogramming.trafficspy;

public interface HttpRequesterCallback {
	public void onResponse(String responseMessage);
	public void onError(String responseError);
}
