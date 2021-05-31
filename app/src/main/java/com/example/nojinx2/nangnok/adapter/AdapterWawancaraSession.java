package com.example.nojinx2.nangnok.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.data.DataWawancara;
import com.example.nojinx2.nangnok.data.DataWawancaraSession;
import com.example.nojinx2.nangnok.util.Server;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterWawancaraSession extends RecyclerView.Adapter<AdapterWawancaraSession.ViewHolder> {
    private List<DataWawancaraSession> datas;
    private Context context;
    private List<DataWawancaraSession> orig;

    public AdapterWawancaraSession(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataWawancaraSession> datas) {this.datas = datas;}

    public List<DataWawancaraSession> getDatas() {return datas;}

    @Override
    public AdapterWawancaraSession.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wawancara_session, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterWawancaraSession.ViewHolder holder, int position) {
        holder.sesi.setText(datas.get(position).getSessionBean().getAssesment_type());
        holder.interview.setText(datas.get(position).getValue());
        holder.grooming.setText(datas.get(position).getValue());
        holder.manner.setText(datas.get(position).getValue());
        DataWawancaraSession data = datas.get(position);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sesi)
        TextView sesi;
        @BindView(R.id.interview)
        EditText interview;
        @BindView(R.id.grooming)
        EditText grooming;
        @BindView(R.id.manner)
        EditText manner;
        @BindView(R.id.btn_simpan)
        Button btn_simpan;

        public ViewHolder(View btn_simpan) {
            super(btn_simpan);
            ButterKnife.bind(this, btn_simpan);
            btn_simpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    context.startActivity(new Intent(context, Wawancara_asessment.class)
//                            .putExtra("data",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}
