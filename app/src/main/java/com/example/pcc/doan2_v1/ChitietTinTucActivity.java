package com.example.pcc.doan2_v1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcc.doan2_v1.adapter.CommentAdapter;
import com.example.pcc.doan2_v1.adapter.CustomExpandleListView;
import com.example.pcc.doan2_v1.model.BinhLuan;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChitietTinTucActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    ImageView img_anhtintuc;
    TextView txt_tieude;
    TextView txt_thoigian;
    TextView txt_noidung;
    DatabaseReference databaseReference;
    CoordinatorLayout  layout;
    AppBarLayout appBarLayout;
    String tieude,thoigian, urlimage,noidung;
    FloatingActionButton fabComment,fabLike;
    int yeuthich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_tin_tuc);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        txt_noidung=findViewById(R.id.txt_noidung);
        txt_thoigian=findViewById(R.id.txt_thoigian);
        txt_tieude=findViewById(R.id.txt_tieude);
        img_anhtintuc=findViewById(R.id.img_anhtintuc);
        fabComment=findViewById(R.id.fab_comment);
        fabLike=findViewById(R.id.fab_like);
        Tintuc tintuc= (Tintuc) getIntent().getSerializableExtra("TINTUC");
        tieude=tintuc.getTitle();
        thoigian=tintuc.getTime_post();
        urlimage=tintuc.getUrl_image();
        noidung=tintuc.getContent();
        txt_tieude.setText(tieude);
        txt_thoigian.setText(thoigian);
        txt_noidung.setText(noidung);
        yeuthich=tintuc.getYeuthich();
        if(yeuthich==1){
            fabLike.setImageDrawable(getResources().getDrawable(R.drawable.ic_like));
        }
        else {
            if(yeuthich==0)
                fabLike.setImageDrawable(getResources().getDrawable(R.drawable.dislike));
        }
        Picasso.with(this).load(urlimage).into(img_anhtintuc);
        layout=(CoordinatorLayout)findViewById(R.id.activity_main);
        appBarLayout=(AppBarLayout) findViewById(R.id.app_bar_layout);
        databaseReference= FirebaseDatabase.getInstance().getReference("Binhluan");
        setEvent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    public void setEvent(){
        fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yeuthich==1)
                    Toast.makeText(getApplicationContext(),"DisLike",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Like",Toast.LENGTH_SHORT).show();
            }
        });
        fabComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                if(firebaseAuth.getCurrentUser()!=null) {
                    String email = getIntent().getStringExtra("EMAIL");
                    Intent intent = new Intent(ChitietTinTucActivity.this, CommentActivity.class);
                    intent.putExtra("Tieude", txt_tieude.getText().toString());
                    intent.putExtra("EMAIL", email);
                    startActivity(intent);
                }

                else{
                    if(firebaseAuth.getCurrentUser()==null){
                        AlertDialog.Builder mdialog=new AlertDialog.Builder(ChitietTinTucActivity.this);
                        mdialog.setTitle("Thông báo");
                        mdialog.setMessage("Bạn chưa đăng nhập. Để bình luận chọn YES");
                        mdialog.setCancelable(false);
                        mdialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivity(new Intent(ChitietTinTucActivity.this,LoginActivity.class));
                            }
                        });
                        mdialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        mdialog.create();
                        mdialog.show();
                    }
                }

            }
        });

    }
}
