package com.example.andriginting.projectlocation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andri Ginting on 1/15/2017.
 */

public class List_checkin extends BaseAdapter {

    Activity activity;
    List<user> list_user;
    LayoutInflater inflater;

    public List_checkin(Activity activity, List<user> list_user) {
        this.activity = activity;
        this.list_user = list_user;
    }

    @Override
    public int getCount() {
        return list_user.size();
    }

    @Override
    public Object getItem(int position) {
        return list_user.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=(LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView =inflater.inflate(R.layout.activity_profil,null);

        TextView txtemail =(TextView)itemView.findViewById(R.id.id_emailProfil);
        TextView txtusername=(TextView)itemView.findViewById(R.id.id_namaProfil);

        txtusername.setText(list_user.get(position).getname());
        txtemail.setText(list_user.get(position).getEmail());
        return itemView;
    }
}
