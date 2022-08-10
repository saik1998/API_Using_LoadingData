package com.firstapp.load_data_using_stringbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String baseUrl;
    RequestQueue queue;
    ProgressDialog progressDialog;
    ListView listView;
    StringModel stringModel;
    ListAdapter listAdapter;
    List<StringModel> stringModelList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list_api);
//        listView.setHasFixedSize(true);
//        listView.setLayoutManager(new LinearLayoutManager(this));

        queue= Volley.newRequestQueue(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);




//        stringAdapter=new StringAdapter(this,stringModelList);
//        listView.setAdapter(stringAdapter);

        listAdapter=new com.firstapp.load_data_using_stringbuilder.ListAdapter(this,stringModelList);
        listView.setAdapter(listAdapter);

        baseUrl=getResources().getString(R.string.placeholer_api);
        loadMethod(baseUrl);

    }

    private void loadMethod(String baseUrl) {

        progressDialog.show();
        progressDialog.setMessage("Fetching Data!----");


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.POST, baseUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Welcome"+response, Toast.LENGTH_SHORT).show();
                for (int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject jsonObject=response.getJSONObject(i);

//                        StringBuilder userid= new StringBuilder(jsonObject.getString("userId"));
//
//                        StringBuilder id= new StringBuilder(jsonObject.getString("id"));
//
//                        StringBuilder title= new StringBuilder(jsonObject.getString("title"));
//
//                        StringBuilder body= new StringBuilder(jsonObject.getString("body"));



                        StringBuilder stringBuilder=new StringBuilder("userid");
                        StringBuilder stringBuilder1=new StringBuilder("id");
                        StringBuilder stringBuilder2=new StringBuilder("title");
                        StringBuilder stringBuilder3=new StringBuilder("body");





//                        StringBuilder stringBuilder=new StringBuilder();
//                        StringBuilder stringBuilder1=new StringBuilder();
//                        StringBuilder stringBuilder2=new StringBuilder();
//                        StringBuilder stringBuilder3=new StringBuilder();


//                        StringBuilder stringBuilder=new StringBuilder(jsonObject.getString("userid"));
//                        StringBuilder stringBuilder1=new StringBuilder(jsonObject.getString("id"));
//                        StringBuilder stringBuilder2=new StringBuilder(jsonObject.getString("title"));
//                        StringBuilder stringBuilder3=new StringBuilder(jsonObject.getString("body"));


                        String userid=stringBuilder.toString();
                        String id=stringBuilder1.toString();
                        String title=stringBuilder2.toString();
                        String body=stringBuilder3.toString();

                        stringModel=new StringModel(userid,id,title,body);
                        stringModelList.add(stringModel);

//                        stringAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ""+error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
        queue.add(jsonArrayRequest);


    }
}