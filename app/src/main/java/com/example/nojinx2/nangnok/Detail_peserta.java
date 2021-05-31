package com.example.nojinx2.nangnok;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.adapter.AdapterPenghargaan;
import com.example.nojinx2.nangnok.adapter.AdapterPesertaHome;
import com.example.nojinx2.nangnok.data.DataAgenda;
import com.example.nojinx2.nangnok.data.DataPenghargaan;
import com.example.nojinx2.nangnok.data.DataPeserta;
import com.example.nojinx2.nangnok.util.MySingleton;
import com.example.nojinx2.nangnok.util.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Detail_peserta extends AppCompatActivity {

    @BindView(R.id.nama_lengkap)
    TextView nama_lengkap;
    @BindView(R.id.alamat)
    TextView alamat;
    @BindView(R.id.tempat_lahir)
    TextView tempat_lahir;
    @BindView(R.id.tanggal_lahir)
    TextView tanggal_lahir;
    @BindView(R.id.profesi)
    TextView profesi;
    @BindView(R.id.agama)
    TextView agama;
    @BindView(R.id.reason)
    TextView reason;
    @BindView(R.id.visi)
    TextView visi;
    @BindView(R.id.thumbnail)
    ImageView thumbnail;
    DataPeserta data;

    @BindView(R.id.rv_penghargaan)
    RecyclerView rv_penghargaan;
    AdapterPenghargaan adapter_penghargaan;

    private static final String TAG = Detail_peserta.class.getSimpleName();
    private List<DataPenghargaan> penghargaanList = new ArrayList<DataPenghargaan>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_peserta);
        ButterKnife.bind(this);
        data = (DataPeserta) getIntent().getSerializableExtra("data");

        // date format
        String date = data.getDate_of_birth();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd MMMM yyyy", new Locale("in", "ID"));
            date = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        nama_lengkap.setText(data.getFull_name());
        alamat.setText(data.getAddress());
        tempat_lahir.setText(data.getPlace_of_birth());
        tanggal_lahir.setText(date);
        profesi.setText(data.getWork());
        agama.setText(data.getReligion());
        reason.setText(data.getReason());
        visi.setText(data.getVision_mission());
        try {
            Picasso.with(getApplicationContext()).load(Server.URL_foto_peserta + data.getImageBean().getImage_name())
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .into(thumbnail, new Callback() {
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

        getPeserta();

    }

    public void getPeserta() {
        adapter_penghargaan = new AdapterPenghargaan(Detail_peserta.this);
        rv_penghargaan.setLayoutManager(new GridLayoutManager(Detail_peserta.this, 1, GridLayoutManager.HORIZONTAL, false));
        rv_penghargaan.setAdapter(adapter_penghargaan);
        adapter_penghargaan.getDatas().clear();
        adapter_penghargaan.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.fetch_penghargaan, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataPenghargaan>> token = new TypeToken<List<DataPenghargaan>>() {
                    };
                    penghargaanList = gson.fromJson(data.toString(), token.getType());
                    adapter_penghargaan.setDatas(penghargaanList);
                    adapter_penghargaan.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // stop animating Shimmer and hide the layout
//                mShimmerViewContainer.stopShimmerAnimation();
//                mShimmerViewContainer.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                String id_participants = data.getId_participants();

                params.put("id_participants", id_participants);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(Detail_peserta.this).addToRequestQueue(request);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

