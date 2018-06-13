package com.example.robin.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button bn;
    TextView result;
    EditText cname;
    String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    String api = "&appid=53985a05a1e869af6b244d1d62c60b4b";
    String URL,info;
    JsonObjectRequest jor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn = findViewById(R.id.button);
        cname = findViewById(R.id.editText);
        result = findViewById(R.id.textView2);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cname.getText().toString() != "") {
                    Log.i("string","NAME " +cname.getText().toString());
                    URL = baseURL + cname.getText().toString() + api;
                    jor = new JsonObjectRequest(Request.Method.GET, URL, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //Toast.makeText(MainActivity.this," Json" + response,Toast.LENGTH_LONG).show();

                                    try {
                                        info = response.getString("weather");
                                        JSONArray ar = new JSONArray(info);
                                        for(int i=0; i< ar.length();++i){
                                            JSONObject obj = ar.getJSONObject(i);
                                            String myWeather = obj.getString("main");
                                            result.setText(myWeather);}

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }
                    );
                    Mysingleton.getInstance(MainActivity.this).addtorq(jor);

                }
                else{
                    Toast.makeText(MainActivity.this,"Please Enter the city name",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
