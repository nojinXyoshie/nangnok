package com.example.nojinx2.nangnok.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nojinx2.nangnok.JudgementAdministrasi;
import com.example.nojinx2.nangnok.JudgementKabisa;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.data.DataAdministrasi;
import com.example.nojinx2.nangnok.data.DataKabisa;
import com.example.nojinx2.nangnok.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterKabisa extends RecyclerView.Adapter<AdapterKabisa.ViewHolder> {
    private List<DataKabisa> datas;
    private Context context;
    private List<DataKabisa> orig;

    public AdapterKabisa(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataKabisa> datas) {this.datas = datas;}

    public List<DataKabisa> getDatas() {return datas;}

    @Override
    public AdapterKabisa.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kabisa, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterKabisa.ViewHolder holder, int position) {
        holder.nama.setText(datas.get(position).getFull_name());
        holder.pekerjaan.setText(datas.get(position).getWork());
        holder.alamat.setText(datas.get(position).getAddress());
        holder.visi.setText(datas.get(position).getVision_mission());
        DataKabisa data = datas.get(position);
        try {
            Picasso.with(context).load(Server.URL_foto_peserta + data.getImageBean().getImage_name())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .into(holder.thumbnail, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.txt_nama)
        TextView nama;
        @BindView(R.id.txt_pekerjaan)
        TextView pekerjaan;
        @BindView(R.id.txt_asal)
        TextView alamat;
        @BindView(R.id.txt_visi)
        TextView visi;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, JudgementKabisa.class)
                            .putExtra("data",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}