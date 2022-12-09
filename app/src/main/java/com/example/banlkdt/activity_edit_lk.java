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


public class activity_edit_lk extends AppCompatActivity {
        EditText edtmansx, edtTenLK, edtGia, edtSL;
        Button btnSua, btnXoa;
        String malk, mansx,tenlk, gia, sl;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_lk);
            anhXa();
            getAndSetIntentData();
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setTitle(tenlk);
            }
            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBHelper LKDB = new DBHelper(activity_edit_lk.this);
                    mansx = edtmansx.getText().toString().trim();
                    tenlk = edtTenLK.getText().toString().trim();
                    gia = edtGia.getText().toString().trim();
                    sl = edtSL.getText().toString().trim();

                    LKDB.updateLKData(malk, mansx, tenlk, gia, sl);
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
            if (getIntent().hasExtra("malk") && getIntent().hasExtra("mansxlk") &&
                    getIntent().hasExtra("tenlk")&& getIntent().hasExtra("gia")
                    && getIntent().hasExtra("sl")) {

                malk = getIntent().getStringExtra("malk");
                mansx = getIntent().getStringExtra("mansxlk");
                tenlk = getIntent().getStringExtra("tenlk");
                gia = getIntent().getStringExtra("gia");
                sl = getIntent().getStringExtra("sl");

                edtmansx.setText(mansx);
                edtTenLK.setText(tenlk);
                edtGia.setText(gia);
                edtSL.setText(sl);

            } else {
                Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
            }
        }

        void confirmDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete " + tenlk + " ?");
            builder.setMessage("Are you sure you want to delete " + tenlk + " ? ");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBHelper myDB = new DBHelper(activity_edit_lk.this);
                    myDB.deleteLKOneRow(malk);
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
            edtmansx = findViewById(R.id.edtmansx);
            edtTenLK = findViewById(R.id.edtTenLK);
            edtGia = findViewById(R.id.edtGia);
            edtSL = findViewById(R.id.edtSL);
            btnSua = findViewById(R.id.btnSua);
            btnXoa = findViewById(R.id.btnXoa);
        }
}
