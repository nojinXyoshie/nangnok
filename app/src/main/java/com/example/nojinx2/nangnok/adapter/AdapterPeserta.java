package com.example.nojinx2.nangnok.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Filter;

import com.example.nojinx2.nangnok.Detail_agenda;
import com.example.nojinx2.nangnok.Detail_peserta;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.data.DataAgenda;
import com.example.nojinx2.nangnok.data.DataPeserta;
import com.example.nojinx2.nangnok.util.Server;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterPeserta extends RecyclerView.Adapter<AdapterPeserta.ViewHolder> {
    private List<DataPeserta> datas;
    private Context context;
    private List<DataPeserta> orig;

    public AdapterPeserta(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataPeserta> datas) {this.datas = datas;}

    public List<DataPeserta> getDatas() {return datas;}

    @Override
    public AdapterPeserta.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_peserta, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterPeserta.ViewHolder holder, int position) {
        holder.nama.setText(datas.get(position).getFull_name());
        holder.profesi.setText(datas.get(position).getWork());
        DataPeserta data = datas.get(position);
        try {
            Picasso.with(context).load(Server.URL_foto_peserta + data.getImageBean().getImage_name())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .into(holder.foto_peserta, new Callback() {
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

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<DataPeserta> results = new ArrayList<>();
                if (orig == null) {
                    orig = datas;
                }
                if (charSequence != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final DataPeserta g : orig) {
                            if (g.getFull_name().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                                results.add(g);
                            } else if (g.getWork().toLowerCase().contains(charSequence.toString().toLowerCase())){
                                results.add(g);
                            }
                        }
                        oReturn.values = results;
                    }
                }
                return oReturn;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                datas = (ArrayList<DataPeserta>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama)
        TextView nama;
        @BindView(R.id.profesi)
        TextView profesi;
        @BindView(R.id.foto_peserta)
        ImageView foto_peserta;
        @BindView(R.id.img_polling)
        ImageButton img_polling;

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