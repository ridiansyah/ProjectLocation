package com.example.andriginting.projectlocation.adapterData;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andriginting.projectlocation.R;

import java.util.List;

/**
 * Created by Andri Ginting on 2/15/2017.
 */

public class settingAdapter extends BaseAdapter {
    Context context;
    List<settingsMenu> settingsMenus;

    public settingAdapter(Context context, List<settingsMenu> settingsMenus) {
        this.context = context;
        this.settingsMenus = settingsMenus;
    }

    @Override
    public int getCount() {
        return settingsMenus.size();
    }

    @Override
    public Object getItem(int position) {
        return settingsMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return settingsMenus.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView==null){
            convertView = inflater.inflate(R.layout.setting_list,null);
            viewHolder = new ViewHolder();

            viewHolder.gambar=(ImageView)convertView.findViewById(R.id.image_setting);
            viewHolder.nama=(TextView)convertView.findViewById(R.id.id_setting);

            settingsMenu item =settingsMenus.get(position);

            viewHolder.gambar.setImageResource(item.getGambar());
            viewHolder.nama.setText(item.getNama());
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView gambar;
        TextView nama;
    }
}
