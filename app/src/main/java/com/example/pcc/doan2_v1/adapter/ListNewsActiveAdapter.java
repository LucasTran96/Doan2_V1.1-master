package com.example.pcc.doan2_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcc.doan2_v1.R;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pcc on 16/10/2017.
 */

public class ListNewsActiveAdapter extends RecyclerView.Adapter<ListNewsActiveAdapter.ViewHolder> {
    List<Tintuc> listTintuc;
    Context context;
    Tintuc tintuc;
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener{
        ImageView img_thumnail;
        TextView txt_title;
        public ViewHolder(View itemView) {
            super(itemView);
            img_thumnail=(ImageView)itemView.findViewById(R.id.img_thumnail);
            txt_title=(TextView)itemView.findViewById(R.id.txt_title);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem item=menu.add(0,0,0,"Bỏ công khai tin tức");
            item.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int pos=getAdapterPosition();
            if(pos!=RecyclerView.NO_POSITION){
                tintuc=listTintuc.get(pos);
            }
            if(item.getItemId()==0 && tintuc!=null){
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Tintuc");
                databaseReference.child(tintuc.getTitle()).child("active").setValue(0);
            }
            return false;
        }

    }
    public ListNewsActiveAdapter(Context context, List<Tintuc> listTintuc) {
        this.context = context;
        this.listTintuc = listTintuc;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_active, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tintuc tintuc = listTintuc.get(position);
        Picasso.with(context).load(tintuc.getUrl_image()).into(holder.img_thumnail);
        holder.txt_title.setText(tintuc.getTitle());
    }

    @Override
    public int getItemCount() {
        return listTintuc.size();
    }
}