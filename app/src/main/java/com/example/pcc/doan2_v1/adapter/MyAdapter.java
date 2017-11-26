package com.example.pcc.doan2_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcc.doan2_v1.ChitietTinTucActivity;
import com.example.pcc.doan2_v1.R;
import com.example.pcc.doan2_v1.model.Chude;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by pcc on 15/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    List<Tintuc> listTintuc;
    Context context;
    String email;
    public MyAdapter(Context context,List<Tintuc> listTintuc,String email)
    {
        this.context=context;
        this.listTintuc = listTintuc;
        this.email=email;
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_thumnail;
        TextView txt_title;
        public MyViewHolder(View itemView) {
            super(itemView);
            img_thumnail=(ImageView)itemView.findViewById(R.id.img_thumnail);
            txt_title=(TextView)itemView.findViewById(R.id.txt_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
            if(pos!= RecyclerView.NO_POSITION){
                Tintuc tintuc=listTintuc.get(pos);
                Intent intent=new Intent(context,ChitietTinTucActivity.class);
                intent.putExtra("TINTUC",tintuc);
                intent.putExtra("EMAIL",email);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //v.getContext().startActivity(intent);
            }

        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.item_recyclerview_dstt,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tintuc tintuc=listTintuc.get(position);
        Picasso.with(context).load(tintuc.getUrl_image()).into(holder.img_thumnail);
        holder.txt_title.setText(tintuc.getTitle());
    }

    @Override
    public int getItemCount() {
        return listTintuc.size();
    }
    public void setFilter(ArrayList<Tintuc> tintucs){
        listTintuc=new ArrayList<>();
        listTintuc.addAll(tintucs);
        notifyDataSetChanged();
    }
}
