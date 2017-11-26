package com.example.pcc.doan2_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.pcc.doan2_v1.R;
import com.example.pcc.doan2_v1.model.BinhLuan;

import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 11/23/2017.
 */

public class CustomExpandleListView extends BaseExpandableListAdapter {
    Context context;
    List<BinhLuan> listDataHeader;
    HashMap<String,List<BinhLuan>> listHashMap;

    public CustomExpandleListView(Context context, List<BinhLuan> listDataHeader, HashMap<String, List<BinhLuan>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
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
        BinhLuan comment=(BinhLuan) getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_listview,null);
        }
        TextView txtEmailHeader=(TextView)convertView.findViewById(R.id.txt_username);
        TextView txtContentHeader=(TextView)convertView.findViewById(R.id.txt_noidungbinhluan);
        txtEmailHeader.setText(comment.getUsername());
        txtContentHeader.setText(comment.getNoidungbinhluan());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        BinhLuan comment=(BinhLuan) getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_listview,null);
        }
        TextView txtEmailHeader=(TextView)convertView.findViewById(R.id.txt_username);
        TextView txtContentHeader=(TextView)convertView.findViewById(R.id.txt_noidungbinhluan);
        txtEmailHeader.setText(comment.getUsername());
        txtContentHeader.setText(comment.getNoidungbinhluan());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
