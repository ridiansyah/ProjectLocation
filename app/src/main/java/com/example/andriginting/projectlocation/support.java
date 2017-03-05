package com.example.andriginting.projectlocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.andriginting.projectlocation.adapterData.ExpandListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class support extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandListAdapter expandListAdapter;
    private List<String> listdataHeader;
    private HashMap<String,List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        expandableListView = (ExpandableListView)findViewById(R.id.listExp);
        initData();
        expandListAdapter = new ExpandListAdapter(this,listdataHeader,listHashMap);
        expandableListView.setAdapter(expandListAdapter);
    }

    private void initData() {

        listdataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listdataHeader.add("Bagaimana Cara Menggunakan Aplikasi ini?");
        listdataHeader.add("Apa Kelebihan Aplikasi ini?");
        listdataHeader.add("Aplikasi ini Dapat Dipakai Dimana saja?");
        listdataHeader.add("Dimana saya harus melaporkan error/bug?");

        List<String> pertama = new ArrayList<>();
        pertama.add("Aplikasi ini dapat dipakai dengan mudah, pertama anda harus mendaftar dahulu, lalu anda bisa menggunakan aplikasi ini untuk mencari tempat tempat yang ada disekitar anda.");

        List<String> kedua = new ArrayList<>();
        kedua.add("Kelebihan yang pertama adalah sangat mudah digunakan, lalu sangat membantu anda mencari tempat disekitar anda yang sangat spesifik yang sesuai kemauan anda");

        List<String> ketiga = new ArrayList<>();
        ketiga.add("untuk saat ini aplikasi ini baru bisa digunakan di kota malang");

        List<String> keempat = new ArrayList<>();
        keempat.add("Anda bisa melaporkannya bug nya ke support.roundmeapps@gmail.com");


        listHashMap.put(listdataHeader.get(0),pertama);
        listHashMap.put(listdataHeader.get(1),kedua);
        listHashMap.put(listdataHeader.get(2),ketiga);
        listHashMap.put(listdataHeader.get(3),keempat);


    }
}
