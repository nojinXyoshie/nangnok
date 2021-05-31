package com.example.nojinx2.nangnok.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.data.DataPenghargaan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPenghargaan extends RecyclerView.Adapter<AdapterPenghargaan.ViewHolder> {
    private List<DataPenghargaan> datas;
    private Context context;
    private List<DataPenghargaan> orig;

    public AdapterPenghargaan(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataPenghargaan> datas) {this.datas = datas;}

    public List<DataPenghargaan> getDatas() {return datas;}

    @Override
    public AdapterPenghargaan.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_penghargaan, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterPenghargaan.ViewHolder holder, int position) {
        holder.nama.setText(datas.get(position).getName_achievement());
        DataPenghargaan data = datas.get(position);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama)
        TextView nama;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
