package com.example.banlkdt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class activity_view_nsx extends AppCompatActivity {
        RecyclerView recyclerView;
        FloatingActionButton btnThem;
        ImageView imgEmpty;
        TextView noData;

        LKAdapter LKAdapter; 
        DBHelper DB;
        ArrayList<String> malk, ma_nsx_lk, tenlk, gia, sl;
        private String mansx, tennsx, diachi,sdt;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_nsx);
            anhXa();
            DB = new DBHelper(activity_view_nsx.this);
            dataTruyen();
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setTitle(tennsx);
            }

            malk = new ArrayList<>();
            ma_nsx_lk = new ArrayList<>();
            tenlk = new ArrayList<>();
            gia = new ArrayList<>();
            sl = new ArrayList<>();
            storeDataInArrays();
            LKAdapter = new LKAdapter(activity_view_nsx.this, this, malk, ma_nsx_lk, tenlk, gia, sl);
            recyclerView.setAdapter(LKAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity_view_nsx.this));

            //
            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity_view_nsx.this, activity_add_lk.class);
                    intent.putExtra("mansx", String.valueOf(mansx));
                    intent.putExtra("tennsx", String.valueOf(tennsx));
                    intent.putExtra("diachi", String.valueOf(diachi));
                    intent.putExtra("sdt", String.valueOf(sdt));
                    startActivity(intent);
                }
            });
        }

        private void dataTruyen() {
            if (getIntent().hasExtra("mansx") && getIntent().hasExtra("tennsx") &&
                    getIntent().hasExtra("diachi")&&
                    getIntent().hasExtra("sdt")) {
                //Getting data from Intent
                mansx = getIntent().getStringExtra("mansx");
                tennsx = getIntent().getStringExtra("tennsx");
                diachi = getIntent().getStringExtra("diachi");
                sdt = getIntent().getStringExtra("sdt");
            } else {
                Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
            }
        }
        void storeDataInArrays(){
            Cursor cursor1 = DB.readAllLKData(mansx.trim());
            if (cursor1.getCount() == 0){
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();

                imgEmpty.setVisibility(View.VISIBLE);
                noData.setVisibility(View.VISIBLE);
            }else {
                while (cursor1.moveToNext()){
                    malk.add(cursor1.getString(0));
                    ma_nsx_lk.add(cursor1.getString(1));
                    tenlk.add(cursor1.getString(2));
                    gia.add(cursor1.getString(3));
                    sl.add(cursor1.getString(4));
                }

                imgEmpty.setVisibility(View.GONE);
                noData.setVisibility(View.GONE);
            }
        }

        private void anhXa() {
            recyclerView = findViewById(R.id.rcview);
            btnThem = findViewById(R.id.btnThem);
            imgEmpty = findViewById(R.id.imgEmpty);
            noData = findViewById(R.id.noData);
        }

}