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
 * Created by Andri Ginting on 2/7/2017.
 */

public class KategoriAdapter extends BaseAdapter {

    Context context;
    List<Itemkategori> itemkategoris;

    public KategoriAdapter(Context context,List<Itemkategori>itemkategoris){
        this.context=context;
        this.itemkategoris=itemkategoris;
    }

    @Override
    public int getCount() {
        return itemkategoris.size();
    }

    @Override
    public Object getItem(int position) {
        return itemkategoris.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemkategoris.indexOf(getItem(position));
    }

    private class ViewHolder{
        ImageView gambar;
        TextView menu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView==null){
            convertView = inflater.inflate(R.layout.baris_kategori,null);
            holder = new ViewHolder();

            holder.gambar=(ImageView)convertView.findViewById(R.id.kategori_gmbr);
            holder.menu=(TextView)convertView.findViewById(R.id.nama_kategori);

            Itemkategori item = itemkategoris.get(position);

            holder.gambar.setImageResource(item.getGambar());
            holder.menu.setText(item.getNama_menu());
        }
        return convertView;
    }
}
