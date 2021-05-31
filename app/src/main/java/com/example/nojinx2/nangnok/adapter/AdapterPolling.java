package com.example.nojinx2.nangnok.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.Detail_peserta;
import com.example.nojinx2.nangnok.LoginActivity;
import com.example.nojinx2.nangnok.MainActivity;
import com.example.nojinx2.nangnok.Polling;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.app.MyApplication;
import com.example.nojinx2.nangnok.data.DataPeserta;
import com.example.nojinx2.nangnok.data.DataPolling;
import com.example.nojinx2.nangnok.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nojinx2.nangnok.LoginActivity.TAG_ID;

public class AdapterPolling extends RecyclerView.Adapter<AdapterPolling.ViewHolder> {
    private List<DataPolling> datas;
    private Context context;
    private List<DataPolling> orig;

    public AdapterPolling(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataPolling> datas) {this.datas = datas;}

    public List<DataPolling> getDatas() {return datas;}

    @Override
    public AdapterPolling.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_polling, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterPolling.ViewHolder holder, int position) {
        holder.nama.setText(datas.get(position).getFull_name());
        holder.profesi.setText(datas.get(position).getWork());
        holder.asal.setText(datas.get(position).getAddress());
        holder.visi.setText(datas.get(position).getVision_mission());
        DecimalFormat digit = new DecimalFormat("0.0");
        DecimalFormat digit2 = new DecimalFormat("0");
        String ya = datas.get(position).getJumlahBean().getJumlah();
        double yas = Double.parseDouble(ya);
        String polling = datas.get(position).getPollingBean().getJumlah();
        double intpolling = Double.parseDouble(polling);
        double yass = yas/intpolling*100;
        Double test = Double.parseDouble(String.valueOf(yass));
        holder.value.setText(digit.format(test)+"% Polling");
        Integer nono = Integer.parseInt(digit2.format(yass));
        holder.bar.setProgress(nono);
        DataPolling data = datas.get(position);
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
                            holder.thumbnail.setImageResource(R.drawable.profile);
                        }
                    });


        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_nama)
        TextView nama;
        @BindView(R.id.txt_pekerjaan)
        TextView profesi;
        @BindView(R.id.txt_visi)
        TextView visi;
        @BindView(R.id.txt_asal)
        TextView asal;
        @BindView(R.id.txt_value)
        TextView value;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.pb_red_progress)
        ProgressBar bar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, Polling.class)
                            .putExtra("data",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}
