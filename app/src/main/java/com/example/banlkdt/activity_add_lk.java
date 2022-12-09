package com.example.banlkdt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_add_lk extends AppCompatActivity {
        EditText edtmansx, edtTenLK, edtGia, edtSL;
        Button btnThem;
        private String mansx;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_lk);
            anhXa();
            data();
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBHelper LKDB = new DBHelper(activity_add_lk.this);

                    LKDB.addLK(edtmansx.getText().toString().trim(), edtTenLK.getText().toString().trim(),
                            edtGia.getText().toString().trim(), edtSL.getText().toString().trim());
                    edtTenLK.setText("");
                    edtGia.setText("");
                    edtSL.setText("");
                }
            });
        }

        private void data() {
            if (getIntent().hasExtra("mansx")) {
                mansx = getIntent().getStringExtra("mansx");
                edtmansx.setText(mansx);
            } else {
                Toast.makeText(this, "Lấy mã thất bại.", Toast.LENGTH_SHORT).show();
            }
        }


        private void anhXa() {
            edtmansx = findViewById(R.id.edtmansx);
            edtTenLK = findViewById(R.id.edtTenLK);
            edtGia = findViewById(R.id.edtGia);
            edtSL = findViewById(R.id.edtSL);
            btnThem = findViewById(R.id.btnThem);
        }
    }