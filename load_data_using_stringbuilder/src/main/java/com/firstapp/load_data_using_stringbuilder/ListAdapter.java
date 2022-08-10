package com.firstapp.load_data_using_stringbuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

public class ListAdapter extends BaseAdapter {
    Context context;
    List<StringModel> stringModelList=new ArrayList<>();
    LayoutInflater layoutInflater;

    public ListAdapter(Context context, List<StringModel> stringModelList) {
        this.context = context;
        this.stringModelList = stringModelList;
    }

    @Override
    public int getCount() {
        return stringModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root=layoutInflater.inflate(R.layout.custom_recyler,null);
        TextView Userid,Id,Title,Body;


        Userid=root.findViewById(R.id.UserId);
        Id=root.findViewById(R.id.Id);
        Title=root.findViewById(R.id.Title);
        Body=root.findViewById(R.id.Body);


        Userid.setText(""+stringModelList.get(position).getUserid());

        Id.setText(""+stringModelList.get(position).getId());

        Title.setText(""+stringModelList.get(position).getTitle());

        Body.setText(""+stringModelList.get(position).getBody());



        return root;


    }
}
