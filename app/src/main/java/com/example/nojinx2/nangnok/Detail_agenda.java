package com.example.nojinx2.nangnok;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.adapter.AdapterAgenda;
import com.example.nojinx2.nangnok.data.DataAgenda;
import com.example.nojinx2.nangnok.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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

public class Detail_agenda extends AppCompatActivity {

    @BindView(R.id.sesi)
    TextView sesi;
    @BindView(R.id.penanggung)
    TextView penanggung;
    @BindView(R.id.mulai)
    TextView mulai;
    @BindView(R.id.selesai)
    TextView selesai;
    @BindView(R.id.informasi)
    TextView informasi;
    @BindView(R.id.nama_agenda)
    TextView nama_agenda;
    @BindView(R.id.vanue)
    TextView vanue;
    @BindView(R.id.date)
    TextView date_agenda;
    DataAgenda data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agenda);
        ButterKnife.bind(this);
        data = (DataAgenda) getIntent().getSerializableExtra("data");
        // date format
        String date = data.getAgenda_date();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd MMMM yyyy", new Locale("in", "ID"));
            date = spf.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            sesi.setText(data.getDetail_agenda().getAgenda_session());
            penanggung.setText(data.getDetail_agenda().getPerson_in_charge());
            mulai.setText(data.getDetail_agenda().getTime_start() + " WIB");
            selesai.setText(data.getDetail_agenda().getTime_end() + " WIB");
            informasi.setText(data.getDetail_agenda().getInformation());
            nama_agenda.setText(data.getAgenda_name());
            vanue.setText(data.getVanue());
            date_agenda.setText(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

