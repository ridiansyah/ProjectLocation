package com.example.andriginting.projectlocation.adapterData;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.andriginting.projectlocation.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Andri Ginting on 2/9/2017.
 */

public class ExpandListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listdataHead;
    private HashMap<String,List<String>> listHashMap;

    public ExpandListAdapter(Context context, List<String> listdataHead, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listdataHead = listdataHead;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listdataHead.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listdataHead.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listdataHead.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listdataHead.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String HeadeTittle = (String)getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_faq,null);
        }
        TextView listHeader = (TextView)convertView.findViewById(R.id.list_faqHeader);
        listHeader.setTypeface(null, Typeface.BOLD);
        listHeader.setText(HeadeTittle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childTittle = (String)getChild(groupPosition,childPosition);
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_itemfaq,null);
        }
        TextView txtlistChild = (TextView)convertView.findViewById(R.id.list_faqItem);
        txtlistChild.setText(childTittle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
