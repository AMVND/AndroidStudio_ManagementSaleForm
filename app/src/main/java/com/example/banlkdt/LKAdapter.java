package com.example.banlkdt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


 
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LKAdapter extends RecyclerView.Adapter<LKAdapter.MyViewHolder> {
        Activity activity;
        private Context context;
        private ArrayList malk, mansxlk, tenlk, gia, sl;

        public LKAdapter(Activity activity, Context context, ArrayList malk, ArrayList mansxlk, ArrayList tenlk, ArrayList sl, ArrayList gia) {
            this.activity = activity;
            this.context = context;
            this.malk = malk;
            this.mansxlk = mansxlk;
            this.tenlk = tenlk;
            this.sl = sl;
            this.gia = gia;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.item_list_lk,parent,false);
            return new LKAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            holder.tvmansx.setText(String.valueOf(mansxlk.get(position)));
            holder.tvTenLK.setText(String.valueOf(tenlk.get(position)));
            holder.tvGia.setText(String.valueOf(gia.get(position)));
            holder.tvSL.setText(String.valueOf(sl.get(position)));

            holder.item_lk.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Update Chap ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(context, activity_edit_lk.class);
                            intent.putExtra("malk", String.valueOf(malk.get(position)));
                            intent.putExtra("mansxlk", String.valueOf(mansxlk.get(position)));
                            intent.putExtra("tenlk", String.valueOf(tenlk.get(position)));
                            intent.putExtra("gia", String.valueOf(gia.get(position)));
                            intent.putExtra("sl", String.valueOf(sl.get(position)));
                            activity.startActivityForResult(intent, 1);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            return malk.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout item_lk;
            TextView tvmansx, tvTenLK, tvGia, tvSL;
            public MyViewHolder(View itemView) {
                super(itemView);

                tvmansx = itemView.findViewById(R.id.tvNSX);
                tvTenLK = itemView.findViewById(R.id.tvTenLK);
                tvGia = itemView.findViewById(R.id.tvGia);
                tvSL = itemView.findViewById(R.id.tvSL);
                item_lk =itemView.findViewById(R.id.mainLayoutLK);
            }
        }
}
