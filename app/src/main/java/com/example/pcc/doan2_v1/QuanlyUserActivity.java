package com.example.pcc.doan2_v1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pcc.doan2_v1.adapter.MyAdapter;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.example.pcc.doan2_v1.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuanlyUserActivity extends AppCompatActivity {
    ListView lvUser;
    ArrayAdapter<String>arrayAdapter= null;
    ArrayList<String> listUset;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_user);
        lvUser=findViewById(R.id.lvUser);
        listUset=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,listUset);
        lvUser.setAdapter(arrayAdapter);
        getDanhsachTintuc();

    }
    public void getDanhsachTintuc(){
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user =dataSnapshot.getValue(User.class);
                listUset.add("Tên Gmail: "+user.getUsername()+"\n"+"Tên User: "+user.getPassword());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
