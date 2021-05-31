package com.example.nojinx2.nangnok.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nojinx2.nangnok.Detail_agenda;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.data.DataAgenda;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder> {
    private List<DataAgenda> datas;
    private Context context;
    private List<DataAgenda> orig;

    public AdapterAgenda(Context context) {

        this.context = context;
        datas = new ArrayList<>();
    }

    public void setDatas(List<DataAgenda> datas) {this.datas = datas;}

    public List<DataAgenda> getDatas() {return datas;}

    @Override
    public AdapterAgenda.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_agenda, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterAgenda.ViewHolder holder, int position) {
        holder.agenda_name.setText(datas.get(position).getAgenda_name());
        //holder.harga_awal.setText(FormatData.doubleToRupiah(Double.valueOf(datas.get(position).getHarga_awal())));
        DataAgenda data = datas.get(position);
        //holder.agenda_name.setText(data.getAgenda_name());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                final FilterResults oReturn = new FilterResults();
//                final List<DataPaket> results = new ArrayList<>();
//                if (orig == null) {
//                    orig = datas;
//                }
//                if (charSequence != null) {
//                    if (orig != null && orig.size() > 0) {
//                        for (final DataPaket g : orig) {
//                            if (g.getNamaIkan().toLowerCase().contains(charSequence.toString().toLowerCase())) {
//                                results.add(g);
//                            } else if (g.getHarga_awal().toLowerCase().contains(charSequence.toString().toLowerCase())){
//                                results.add(g);
//                            }
//                        }
//                        oReturn.values = results;
//                    }
//                }
//                return oReturn;
//
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                datas = (ArrayList<DataPaket>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.agenda_name)
        TextView agenda_name;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, Detail_agenda.class)
                            .putExtra("data",datas.get(getAdapterPosition())));
                }
            });
        }
    }
}
