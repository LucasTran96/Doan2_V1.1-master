package com.example.pcc.doan2_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pcc.doan2_v1.model.Chude;
import com.example.pcc.doan2_v1.model.Tintuc;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DangTinActivity extends AppCompatActivity {

    public static final int REQ_CODE=1211;
    Uri uri;
    ImageView img;
    EditText edt_tieude,edt_tomtat,edt_noidung;
    Button btn_luu;
    RadioButton rad_kichhoat,rad_khongkichhoat;
    Spinner spn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    List<String> listChude=new ArrayList<>();

    String tenchude="";
    public void setWidget(){
        img=findViewById(R.id.img_tintuc);
        edt_tieude=findViewById(R.id.edt_title);
        edt_tomtat=findViewById(R.id.edt_tomtat);
        edt_noidung=findViewById(R.id.edt_noidung);
        btn_luu=(Button)findViewById(R.id.btn_posttin);
        rad_kichhoat=findViewById(R.id.rad_active);
        rad_khongkichhoat=findViewById(R.id.rad_notactive);
        spn=findViewById(R.id.spin_dscd);
    }
    public void setEvent(){
        img.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Chọn ảnh"),REQ_CODE);
            }
        });
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenchude= (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "bạn chưa chọn chủ đề", Toast.LENGTH_SHORT).show();
            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guiTin();
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_tin);
        setWidget();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Tintuc");
        storageReference= FirebaseStorage.getInstance().getReference();
        setDataForSpinner();
        setEvent();
    }
    public void guiTin(){
        if(uri!=null){
            StorageReference ref=storageReference.child(uri+ UUID.randomUUID().toString());
            ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String url_img=taskSnapshot.getDownloadUrl().toString();
                    String tieude=edt_tieude.getText().toString();
                    String tomtat=edt_tomtat.getText().toString();
                    String noidung=edt_noidung.getText().toString();

                    Date date=Calendar.getInstance().getTime();
                    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String thoigian=format.format(date);
                    int trangthai=0;
                    if(rad_kichhoat.isChecked()){
                        trangthai=1;
                    }else{
                        if(rad_khongkichhoat.isChecked())
                        trangthai=0;
                    }
                      //  Log.d("data",""+chude);
                        Tintuc tintuc = new Tintuc(url_img, tieude, tomtat, noidung, thoigian, trangthai,0, tenchude);
                        databaseReference.child(tieude).setValue(tintuc);
                        Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void setDataForSpinner(){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Chude");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Chude chude=snapshot.getValue(Chude.class);
                    String tenchude=chude.getTenchude();
                    listChude.add(tenchude);
                }
                ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listChude);
                spn.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE && resultCode==RESULT_OK && data!=null){
            uri=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
