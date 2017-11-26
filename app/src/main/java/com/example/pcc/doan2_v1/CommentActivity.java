package com.example.pcc.doan2_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.pcc.doan2_v1.adapter.CommentAdapter;
import com.example.pcc.doan2_v1.model.BinhLuan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    ListView lv;
    CommentAdapter adapter;
    List<BinhLuan> list=new ArrayList<>();
    DatabaseReference databaseReference;
    EditText edt_noidungbinhluan;
    ImageButton btn_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        lv=findViewById(R.id.lv_Comment);
        edt_noidungbinhluan=(EditText)findViewById(R.id.edt_noiDungBinhLuan);
        btn_post=(ImageButton)findViewById(R.id.btn_postBinhLuan);
        databaseReference= FirebaseDatabase.getInstance().getReference("Binhluan");
        getBinhLuan();
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postBinhLuan();
            }
        });
    }
    public void getBinhLuan(){

        String tieude=getIntent().getStringExtra("Tieude");
        databaseReference.child(tieude).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    BinhLuan binhLuan=snapshot.getValue(BinhLuan.class);
                    list.add(binhLuan);
                }
                adapter=new CommentAdapter(CommentActivity.this,list);
                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error_getComment",""+databaseError.getMessage());
            }
        });
    }
    public void postBinhLuan(){
        String email=getIntent().getStringExtra("EMAIL");
        String tieude=getIntent().getStringExtra("Tieude");
        String noidung=edt_noidungbinhluan.getText().toString();
        String id=databaseReference.push().getKey();
        BinhLuan binhLuan=new BinhLuan(id,email,noidung);
        databaseReference.child(tieude).child(id).setValue(binhLuan);
        edt_noidungbinhluan.setText("");
        adapter.notifyDataSetChanged();
    }
}
