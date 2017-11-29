package com.example.pcc.doan2_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pcc.doan2_v1.rss.DuLich;
import com.example.pcc.doan2_v1.rss.GiaDinh;
import com.example.pcc.doan2_v1.rss.GiaiTri;
import com.example.pcc.doan2_v1.rss.GiaoDuc;
import com.example.pcc.doan2_v1.rss.KhoaHoc;
import com.example.pcc.doan2_v1.rss.KinhTen;
import com.example.pcc.doan2_v1.rss.PhapLuat;
import com.example.pcc.doan2_v1.rss.ThoiSu;

public class HomeTinTucRSS extends AppCompatActivity {
    Button btnXaHoi,btnKinhTe,btnVanHoa,btnGiaoDuc,btnChinhTri,btnPhapLuat,btnGiaiTri,btnGiaDinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tin_tuc_rss);
        khaibao();
        setEvent();
    }

    private void setEvent() {
        btnChinhTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),DuLich.class);
                startActivity( intent);

            }
        });

        btnXaHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),ThoiSu.class);
                startActivity( intent);

            }
        });

        btnVanHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),KhoaHoc.class);
                startActivity( intent);

            }
        });

        btnPhapLuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),PhapLuat.class);
                startActivity( intent);

            }
        });

        btnKinhTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),KinhTen.class);
                startActivity( intent);

            }
        });

        btnGiaoDuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),GiaoDuc.class);
                startActivity( intent);

            }
        });

        btnGiaiTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),GiaiTri.class);
                startActivity( intent);

            }
        });

        btnGiaDinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),GiaDinh.class);
                startActivity( intent);

            }
        });
    }

    private void khaibao() {
        btnChinhTri= findViewById(R.id.btnChinhTriRSS);
        btnGiaDinh= findViewById(R.id.btnGiaDinhRSS);
        btnGiaiTri= findViewById(R.id.btnGiaiTriRSS);
        btnGiaoDuc= findViewById(R.id.btnGiaoDucRSS);
        btnKinhTe= findViewById(R.id.btnKinhTeRSS);
        btnPhapLuat= findViewById(R.id.btnPhapLuatRSS);
        btnVanHoa= findViewById(R.id.btnVanHoaRSS);
        btnXaHoi= findViewById(R.id.btnXaHoiRSS);


    }


}
