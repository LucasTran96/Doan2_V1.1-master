package com.example.pcc.doan2_v1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pcc.doan2_v1.R;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pcc on 16/10/2017.
 */


public class ListNewsNotActiveAdapter extends RecyclerView.Adapter<ListNewsNotActiveAdapter.ViewHolder1> {
    List<Tintuc> listTintuc;
    Context context;
    Tintuc tintuc;
    class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView img_thumnail;
        TextView txt_title;
        public ViewHolder1(View itemView) {
            super(itemView);
            img_thumnail=(ImageView)itemView.findViewById(R.id.img_thumnail);
            txt_title=(TextView)itemView.findViewById(R.id.txt_title);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle("Chọn chức năng");
            MenuItem item=menu.add(0,0,0,"Công khai tin tức");
            MenuItem item1=menu.add(0,1,0,"Xóa tin tức");
            item.setOnMenuItemClickListener(onchange);
            item1.setOnMenuItemClickListener(onchange);
        }

        MenuItem.OnMenuItemClickListener onchange=new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int pos=getAdapterPosition();
                if(pos!=RecyclerView.NO_POSITION){
                    tintuc=listTintuc.get(pos);
                }
                if(item.getItemId()==0 && tintuc!=null){
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Tintuc");
                    databaseReference.child(tintuc.getTitle()).child("active").setValue(1);
                }else if(item.getItemId()==1 && tintuc!=null){
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Tintuc");
                    databaseReference.child(tintuc.getTitle()).removeValue();
                }
                return false;
            }
        };

    }
    public ListNewsNotActiveAdapter(Context context, List<Tintuc> listTintuc) {
        this.context = context;
        this.listTintuc = listTintuc;
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_notactive, parent, false);
        return new ViewHolder1(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position) {
        Tintuc tintuc = listTintuc.get(position);
        Picasso.with(context).load(tintuc.getUrl_image()).into(holder.img_thumnail);
        holder.txt_title.setText(tintuc.getTitle());
    }

    @Override
    public int getItemCount() {
        return listTintuc.size();
    }
}
