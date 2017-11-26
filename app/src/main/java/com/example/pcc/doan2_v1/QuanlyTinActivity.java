package com.example.pcc.doan2_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pcc.doan2_v1.adapter.ListNewsActiveAdapter;
import com.example.pcc.doan2_v1.adapter.ListNewsNotActiveAdapter;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanlyTinActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    List<Tintuc> listActive=new ArrayList<>();
    List<Tintuc> listNotActive=new ArrayList<>();
    RecyclerView recyclerView_active;
    RecyclerView recyclerView_notactive;
    Button btn_dangtin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_tin);
        recyclerView_active=findViewById(R.id.recyclerView_Active);
        recyclerView_notactive=findViewById(R.id.recyclerView_NotActive);
        btn_dangtin=(Button)findViewById(R.id.btn_DangTin);
        btn_dangtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DangTinActivity.class));
            }
        });
        recyclerView_active.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView_notactive.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getDanhsachTin();

    }
    public void getDanhsachTin(){

        databaseReference= FirebaseDatabase.getInstance().getReference("Tintuc");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listActive.clear();
                listNotActive.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                    Tintuc tintuc= snapshot.getValue(Tintuc.class);
                    if(tintuc.getActive()==1){
                        listActive.add(tintuc);
                    }else
                        listNotActive.add(tintuc);
                }
                ListNewsActiveAdapter adapterActive=new ListNewsActiveAdapter(getApplicationContext(),listActive);
                recyclerView_active.setAdapter(adapterActive);
                ListNewsNotActiveAdapter adapterNotActive=new ListNewsNotActiveAdapter(getApplicationContext(),listNotActive);
                recyclerView_notactive.setAdapter(adapterNotActive);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
