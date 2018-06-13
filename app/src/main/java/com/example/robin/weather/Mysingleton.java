package com.example.robin.weather;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton {

    private static Mysingleton mInstance;
    private RequestQueue rq;
    private static Context mContext;

    private Mysingleton(Context context){
        mContext = context;
        rq =getrq();
    }

    public  RequestQueue getrq(){
        if(rq == null){
            rq = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return rq;
    }

    public static synchronized Mysingleton getInstance(Context context){
        if(mInstance==null){
            mInstance = new Mysingleton(context);
        }
        return mInstance;
    }

    public void addtorq(Request r){
        rq.add(r);
    }
}
