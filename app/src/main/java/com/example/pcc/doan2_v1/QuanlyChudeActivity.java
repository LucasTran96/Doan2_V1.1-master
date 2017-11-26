package com.example.pcc.doan2_v1;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pcc.doan2_v1.adapter.ChudeAdapter;
import com.example.pcc.doan2_v1.model.Chude;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanlyChudeActivity extends AppCompatActivity {

    Toolbar toolbar;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    List<Chude> listChude=new ArrayList<>();
    Button btn_themchude;
    int clickedItemPosition;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_chude);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView_Dscd);
        btn_themchude=findViewById(R.id.btn_themchude);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        databaseReference= FirebaseDatabase.getInstance().getReference("Chude");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listChude.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Chude chude=snapshot.getValue(Chude.class);
                    listChude.add(chude);
                }
                ChudeAdapter adapter=new ChudeAdapter(getApplicationContext(),listChude);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),""+databaseError.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        setEvent();

    }
    public void setEvent(){
        btn_themchude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ThemChudeActivity.class));
            }
        });
    }

}
