package com.example.banlkdt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        EditText edtTim;
        ImageView imgEmpty;
        TextView noData;
        Button btnTim;
        RecyclerView recyclerView;
        FloatingActionButton btnThem;

        DBHelper DB;
        ArrayList<String> mansx, tennsx, diachi, sdt;
        NSXAdapter nsxadapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            anhXa();
            DB = new DBHelper(MainActivity.this);

            mansx = new ArrayList<>();
            tennsx = new ArrayList<>();
            diachi = new ArrayList<>();
            sdt = new ArrayList<>();

            storeDataInArrays();

            nsxadapter = new NSXAdapter(MainActivity.this, this, mansx, tennsx, diachi,sdt);
            recyclerView.setAdapter(nsxadapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            btnThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, activity_add_nsx.class);
                    startActivity(intent);
                }
            });
            btnTim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DB = new DBHelper(MainActivity.this);
                    mansx = new ArrayList<>();
                    tennsx = new ArrayList<>();
                    diachi = new ArrayList<>();
                    sdt = new ArrayList<>();
                    searchData();
                    nsxadapter = new NSXAdapter(MainActivity.this, MainActivity.this, mansx, tennsx, diachi,sdt);
                    recyclerView.setAdapter(nsxadapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                }
            });
        }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    private void anhXa() {
            edtTim = findViewById(R.id.edtTim);
            btnTim = findViewById(R.id.btnTim);
            recyclerView = findViewById(R.id.rcview);
            imgEmpty = findViewById(R.id.imgEmpty);
            noData = findViewById(R.id.noData);
            btnThem = findViewById(R.id.btnThem);
        }

        void storeDataInArrays() {
            Cursor cursor = DB.readAllNSXData();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                imgEmpty.setVisibility(View.VISIBLE);
                noData.setVisibility(View.VISIBLE);
            } else {
                while (cursor.moveToNext()) {
                    mansx.add(cursor.getString(0));
                    tennsx.add(cursor.getString(1));
                    diachi.add(cursor.getString(2));
                    sdt.add(cursor.getString(3));
                }

                imgEmpty.setVisibility(View.GONE);
                noData.setVisibility(View.GONE);
            }
        }
    void searchData(){
        Cursor cursor = DB.timKiemData(edtTim.getText().toString().toUpperCase());
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            imgEmpty.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                mansx.add(cursor.getString(0));
                tennsx.add(cursor.getString(1));
                diachi.add(cursor.getString(2));
                sdt.add(cursor.getString(3));
            }

            imgEmpty.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }
}