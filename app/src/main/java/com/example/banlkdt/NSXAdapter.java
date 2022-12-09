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


public class NSXAdapter extends RecyclerView.Adapter<NSXAdapter.MyViewHolder> {
    Activity activity;
    private Context context;
    private ArrayList mansx, tennsx, diachi, sdt;

    public NSXAdapter(Activity activity, Context context, ArrayList mansx, ArrayList tennsx, ArrayList diachi, ArrayList sdt) {
        this.activity = activity;
        this.context = context;
        this.mansx = mansx;
        this.tennsx = tennsx;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_nsx, parent, false);
        return new NSXAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvTenNSX.setText(String.valueOf(tennsx.get(position)));
        holder.tvDiaChi.setText(String.valueOf(diachi.get(position)));
        holder.tvSDT.setText(String.valueOf(sdt.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, activity_view_nsx.class);
                intent.putExtra("mansx", String.valueOf(mansx.get(position)));
                intent.putExtra("tennsx", String.valueOf(tennsx.get(position)));
                intent.putExtra("diachi", String.valueOf(diachi.get(position)));
                intent.putExtra("sdt", String.valueOf(sdt.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Update NSX ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, activity_edit_nsx.class);
                        intent.putExtra("mansx", String.valueOf(mansx.get(position)));
                        intent.putExtra("tennsx", String.valueOf(tennsx.get(position)));
                        intent.putExtra("diachi", String.valueOf(diachi.get(position)));
                        intent.putExtra("sdt", String.valueOf(sdt.get(position)));
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
        return mansx.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  tvMaNSX, tvTenNSX, tvDiaChi, tvSDT;
        LinearLayout mainLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
//            tvMaNSX = itemView.findViewById(R.id.tvMaNSX);
            tvTenNSX = itemView.findViewById(R.id.tvTenNSX);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            mainLayout = itemView.findViewById(R.id.mainLayoutNSX);
        }
    }
}
