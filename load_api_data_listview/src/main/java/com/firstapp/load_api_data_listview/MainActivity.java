package com.firstapp.load_api_data_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String baseUrl;
    RequestQueue queue;
    ListView listView;
    ListAdapter listAdapter;
    ListModel listModel;
    ProgressDialog progressDialog;
    List<ListModel>listModelList=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list_api);

        queue= Volley.newRequestQueue(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        baseUrl=getResources().getString(R.string.placeholer_api);

        listAdapter=new ListAdapter(this,listModelList);
        listView.setAdapter(listAdapter);


        loadMethod(baseUrl);




    }

    private void loadMethod(String baseUrl) {
        progressDialog.show();
        progressDialog.setMessage("Fetching Data-----");
        //requesting to server
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, baseUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();

                for (int i = 0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        int userId = jsonObject.getInt("userId");
                        int id = jsonObject.getInt("id");
                        String title = jsonObject.getString("title");
                        String body = jsonObject.getString("body");

                        listModel = new ListModel(userId, id, title, body);
                        listModelList.add(listModel);

                        listAdapter.notifyDataSetChanged();

                       if(i==10)
                            break;

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ""+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

    });
        queue.add(jsonArrayRequest);

    }
}