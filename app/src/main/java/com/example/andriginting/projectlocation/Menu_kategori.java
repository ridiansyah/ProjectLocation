package com.example.andriginting.projectlocation;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andriginting.projectlocation.adapterData.Itemkategori;
import com.example.andriginting.projectlocation.adapterData.KategoriAdapter;

import java.util.ArrayList;
import java.util.List;


public class Menu_kategori extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    List<Itemkategori> itemkategoris;
    TypedArray gambarnya;
    String[]menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kategori);
        listView=(ListView)findViewById(R.id.list_viewkategori);
        itemkategoris = new ArrayList<Itemkategori>();
        gambarnya= getResources().obtainTypedArray(R.array.gambar_menu);
        menu=getResources().getStringArray(R.array.menu);

        for (int i =0;i<menu.length;i++){
            Itemkategori item = new Itemkategori(menu[i],gambarnya.getResourceId(i, -1));
            itemkategoris.add(item);
        }
        KategoriAdapter adapter = new KategoriAdapter(this,itemkategoris);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String nama_menu = itemkategoris.get(position).getNama_menu();
        Toast.makeText(getApplicationContext(),""+nama_menu,Toast.LENGTH_SHORT).show();
    }
}



