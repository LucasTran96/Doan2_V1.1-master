package com.example.pcc.doan2_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcc.doan2_v1.model.Chude;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThemChudeActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText edt_tenchude;
    Button btn_themchude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_chude);
        edt_tenchude=(EditText)findViewById(R.id.edt_tenchude);
        btn_themchude=(Button)findViewById(R.id.btn_themchude);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Chude");
        btn_themchude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=databaseReference.push().getKey();
                Chude chude=new Chude(key,edt_tenchude.getText().toString());
                databaseReference.child(key).setValue(chude);
                startActivity(new Intent(getApplicationContext(),QuanlyChudeActivity.class));
            }
        });
    }

}
