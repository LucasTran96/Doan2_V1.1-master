package com.example.pcc.doan2_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pcc.doan2_v1.R;
import com.example.pcc.doan2_v1.model.BinhLuan;

import java.util.List;

/**
 * Created by Admin on 11/16/2017.
 */

public class CommentAdapter extends BaseAdapter {
    Context context;
    List<BinhLuan> binhLuanList;

    public CommentAdapter(Context context, List<BinhLuan> binhLuanList) {
        this.context = context;
        this.binhLuanList = binhLuanList;
    }

    @Override
    public int getCount() {
        return binhLuanList.size();
    }

    @Override
    public Object getItem(int position) {
        return binhLuanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            v=inflater.inflate(R.layout.item_listview,null);
        }
        TextView txt_username=(TextView)v.findViewById(R.id.txt_username);
        txt_username.setText(binhLuanList.get(position).getUsername());
        TextView txt_noidung=(TextView)v.findViewById(R.id.txt_noidungbinhluan);
        txt_noidung.setText(binhLuanList.get(position).getNoidungbinhluan());
        return v;
    }
}
