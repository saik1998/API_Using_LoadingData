package com.firstapp.load_api_data_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    Context context;
    List<ListModel> listModelList=new ArrayList<>();
    LayoutInflater layoutInflater;

    public ListAdapter(Context context, List<ListModel> listModelList) {
        this.context = context;
        this.listModelList = listModelList;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listModelList.size();
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
        View root=layoutInflater.inflate(R.layout.custom_list,null);
        TextView Userid,Id,Title,Body;


        Userid=root.findViewById(R.id.UserId);
        Id=root.findViewById(R.id.Id);
        Title=root.findViewById(R.id.Title);
        Body=root.findViewById(R.id.Body);


        Userid.setText(""+listModelList.get(position).getUserid());

        Id.setText(""+listModelList.get(position).getId());

        Title.setText(""+listModelList.get(position).getTitle());

        Body.setText(""+listModelList.get(position).getBody());



        return root;
    }
}
