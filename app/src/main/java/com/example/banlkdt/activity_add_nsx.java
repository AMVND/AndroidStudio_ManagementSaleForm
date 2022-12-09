package com.example.banlkdt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_add_nsx extends AppCompatActivity {
    EditText edtTenNSX, edtDiachi, edtSDT;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nsx);
        anhXa();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper nsxDB =new DBHelper(activity_add_nsx.this);
                nsxDB.addNSX(edtTenNSX.getText().toString().trim(),
                        edtDiachi.getText().toString().trim(),
                        edtSDT.getText().toString().trim());
                edtTenNSX.setText("");
                edtDiachi.setText("");
                edtSDT.setText("");
            }
        });

    }

    private void anhXa() {
        edtTenNSX = findViewById(R.id.edtTenNSX);
        edtDiachi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSDT);
        btnThem = findViewById(R.id.btnThem);
    }
}