package com.chadbet.codechallenge;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class CCApplication extends Application {

    public static final String TAG = CCApplication.class.getName();

    private static CCApplication ccApplication = null;
    private boolean tornDown = false;

    private RequestQueue requestQueue;

    public static CCApplication getInstance() {
        if(ccApplication == null) {
            throw new RuntimeException("App not initialized");
        }
        if(ccApplication.isTornDown()) {
            ccApplication.init();
        }
        return ccApplication;
    }

    private void init() {
        ccApplication = this;
        tornDown = false;
    }

    public boolean isTornDown() {
        return tornDown;
    }

    public RequestQueue volleyRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
        }
        return requestQueue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
}
