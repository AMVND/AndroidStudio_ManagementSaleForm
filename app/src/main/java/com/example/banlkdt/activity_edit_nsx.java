package com.example.banlkdt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class activity_edit_nsx extends AppCompatActivity {
        EditText edtTenNSX, edtDiachi, edtSDT;
        Button btnSua, btnXoa;
        String mansx, tennsx, diachi, sdt;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_nsx);
            anhXa();
            getAndSetIntentData();
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setTitle(tennsx);
            }
            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBHelper NSXDB = new DBHelper(activity_edit_nsx.this);
                    tennsx = edtTenNSX.getText().toString().trim();
                    diachi = edtDiachi.getText().toString().trim();
                    sdt = edtSDT.getText().toString().trim();
                    NSXDB.updateNSXData(mansx, tennsx, diachi, sdt);
                }
            });
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    confirmDialog();
                }
            });
        }

        private void getAndSetIntentData() {
            if (getIntent().hasExtra("mansx") && getIntent().hasExtra("tennsx") &&
                    getIntent().hasExtra("diachi")&&
                    getIntent().hasExtra("sdt")) {

                mansx = getIntent().getStringExtra("mansx");
                tennsx = getIntent().getStringExtra("tennsx");
                diachi = getIntent().getStringExtra("diachi");
                sdt = getIntent().getStringExtra("sdt");

                edtTenNSX.setText(tennsx);
                edtDiachi.setText(diachi);
                edtSDT.setText(sdt);

            } else {
                Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
            }
        }

        void confirmDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("bạn muốn xóa " + tennsx + " ?");
            builder.setMessage("Are you sure you want to delete " + tennsx + " ? ");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBHelper myDB = new DBHelper(activity_edit_nsx.this);
                    myDB.deleteNSXOneRow(mansx);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }

        private void anhXa() {
            edtTenNSX = findViewById(R.id.edtTenNSX);
            edtDiachi = findViewById(R.id.edtDiaChi);
            edtSDT = findViewById(R.id.edtSDT);
            btnSua= findViewById(R.id.btnSua);
            btnXoa = findViewById(R.id.btnXoa);
        }
    }