package com.chadbet.codechallenge.Network;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.chadbet.codechallenge.CCApplication;

import org.json.JSONObject;

import java.io.IOException;

public class NetworkRequest {

    public static void httpGET(final String urlString, final HttpGETCallback callback) throws IOException {
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest
                (Request.Method.GET, urlString, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                        CCApplication.getInstance().volleyRequestQueue().getCache().clear();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        callback.onError(error);
                    }
                });

        CCApplication.getInstance().volleyRequestQueue().add(jsonArrayRequest);

    }

    public interface HttpGETCallback {
        void onSuccess(JSONObject response);
        void onError(Throwable t);
    }
}
