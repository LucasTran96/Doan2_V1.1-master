package com.example.pcc.doan2_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcc.doan2_v1.CapnhatChudeActivity;
import com.example.pcc.doan2_v1.R;
import com.example.pcc.doan2_v1.model.Chude;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by pcc on 17/10/2017.
 */


public class ChudeAdapter extends RecyclerView.Adapter<ChudeAdapter.ViewHolder2>{
    List<Chude> listChude;
    Context context;
    Chude chude;
    int position;
    class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        TextView txt_tenchude;
        @RequiresApi(api = Build.VERSION_CODES.M)
        public ViewHolder2(View itemView) {
            super(itemView);
            txt_tenchude=(TextView)itemView.findViewById(R.id.txt_chude);
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


            menu.setHeaderTitle("Chọn chức năng");
            MenuItem menuItem=menu.add(0,1,0,"Cập nhật");
            menuItem.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int pos=getAdapterPosition();
            if(pos!=RecyclerView.NO_POSITION){
                chude=listChude.get(pos);
            }
            if(item.getItemId()==1 && chude!=null){
                context.startActivity(new Intent(context, CapnhatChudeActivity.class));
            }
            else {
                if(item.getItemId()==2 && chude!=null)
                {
                    Toast.makeText(context,"Code sau",Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        }
    }
    public ChudeAdapter(Context context,List<Chude> listChude)
    {
        this.context=context;
        this.listChude = listChude;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_recyclerview_dscd,parent,false);
        return new ViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder2 holder, int position) {
        Chude chude=listChude.get(position);
        holder.txt_tenchude.setText(chude.getTenchude());
    }

    @Override
    public int getItemCount() {
        return listChude.size();
    }
}