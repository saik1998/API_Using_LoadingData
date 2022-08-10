package com.firstapp.api_usinglag_lon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    EditText editText;

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String baseUrl, key, mainUrl;

    String cityUrl, cityStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_info);
        editText = findViewById(R.id.cityEdt);
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);


    }

    private void cityMethod(){
        cityUrl = "https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";

    }

    public void locApi(View view) {

//        progressDialog.show();
//
//        textView.setText(" ");
//
//        baseUrl = getResources().getString(R.string.weather_api);
//        key = getResources().getString(R.string.weather_key);
//        mainUrl = baseUrl + "lat=16.577293461116167&lon=82.0029394157981&appid=" + key;
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, mainUrl, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                progressDialog.dismiss();
//
//
//                try {
//
//
//                    JSONObject jsonObject = response.getJSONObject("coord");
//                    double lon = jsonObject.getDouble("lon");
//                    double lat = jsonObject.getDouble("lat");
//
//                    JSONArray jsonArray = response.getJSONArray("weather");
//                    JSONObject weatherObj = jsonArray.getJSONObject(0);
//
//                    int id = weatherObj.getInt("id");
//                    String main = weatherObj.getString("main");
//                    String description = weatherObj.getString("description");
//
//                    long visibility = response.getLong("visibility");
//
//                    JSONObject wind = response.getJSONObject("wind");
//                    double speed = wind.getDouble("speed");
//                    double gust = wind.getDouble("gust");
//
//                    String name = response.getString("name");
//
//                    textView.setText("" + lat + "\n" + lon + "\n" + id +
//                            "\n" + main + "\n" + description + "\n" + visibility + "\n" + speed + "\n" + gust + "\n" + name);
//
//                    Log.d("WeatherValues\n", "" + lat + "\n" + lon + "\n" + id +
//                            "\n" + main + "\n" + description + "\n" + visibility);
//
//                    Toast.makeText(MainActivity.this, "" +
//                            "" + lat + "\n" + lon + "\n" + id +
//                            "\n" + main + "\n" + description + "\n" + visibility + "\n" + speed + "\n" + gust + "\n" + name, Toast.LENGTH_SHORT).show();
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                    Toast.makeText(MainActivity.this, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Toast.makeText(MainActivity.this, "" + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        })/*{
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return super.getParams();
//            }
//        }*/;
//
//        requestQueue.add(jsonObjectRequest);

    }



    public void cityname(View view) {
        progressDialog.show();
        cityStr=editText.getText().toString();
        key=getResources().getString(R.string.weather_key);

        cityUrl="https://api.openweathermap.org/data/2.5/weather?q="+cityStr+"&appid="+key;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, cityUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();

                try {


                    JSONObject jsonObject=response.getJSONObject("coord");
                    double lon= jsonObject.getDouble("lon");
                    double lat= jsonObject.getDouble("lat");

                    JSONArray jsonArray=response.getJSONArray("weather");
                    JSONObject weatherObj=jsonArray.getJSONObject(0);

                    int id=weatherObj.getInt("id");
                    String main=weatherObj.getString("main");
                    String description=weatherObj.getString("description");

                    long visibility=response.getLong("visibility");

                    JSONObject wind=response.getJSONObject("wind");
                    double speed=wind.getDouble("speed");
                    //  double gust=wind.getDouble("gust");

                    String name=response.getString("name");

                    textView.setText(""+lat+"\n"+lon+"\n"+id+
                            "\n"+main+"\n"+description+"\n"+visibility+"\n"+speed+"\n"+name);

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(MainActivity.this, "kk"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                    Toast.makeText(getApplicationContext(), "Time out or no connection  error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "AuthFailureError error", Toast.LENGTH_SHORT).show();

                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "ServerError error", Toast.LENGTH_SHORT).show();

                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "NetworkError error", Toast.LENGTH_SHORT).show();

                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "ParseError error", Toast.LENGTH_SHORT).show();

                }

            }
        })/*{
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        }*/;

        requestQueue.add(jsonObjectRequest);
    }


    }
