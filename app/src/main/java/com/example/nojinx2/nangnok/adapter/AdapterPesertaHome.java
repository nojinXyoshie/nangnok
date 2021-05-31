package com.example.nojinx2.nangnok.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nojinx2.nangnok.Detail_peserta;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.data.DataPeserta;
import com.example.nojinx2.nangnok.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPesertaHome extends RecyclerView.Adapter<AdapterPesertaHome.ViewHolder> {
    private List<DataPeserta> datas;
    private Context context;
    private List<DataPeserta> orig;

    public AdapterPesertaHome(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataPeserta> datas) {this.datas = datas;}

    public List<DataPeserta> getDatas() {return datas;}

    @Override
    public AdapterPesertaHome.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_peserta_home, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterPesertaHome.ViewHolder holder, int position) {
        holder.nama.setText(datas.get(position).getFull_name());
        holder.profesi.setText(datas.get(position).getWork());
        DataPeserta data = datas.get(position);
        try {
            Picasso.with(context).load(Server.URL_foto_peserta + data.getImageBean().getImage_name())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .into(holder.foto, new Callback() {
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
        @BindView(R.id.nama)
        TextView nama;
        @BindView(R.id.profesi)
        TextView profesi;
        @BindView(R.id.foto)
        ImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, Detail_peserta.class)
                            .putExtra("data",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}
