package com.example.nojinx2.nangnok.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.ScanActivity;
import com.example.nojinx2.nangnok.adapter.AdapterAgenda;
import com.example.nojinx2.nangnok.data.DataAgenda;
import com.example.nojinx2.nangnok.util.MySingleton;
import com.example.nojinx2.nangnok.util.Server;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.vividcode.android.zxing.CaptureActivityIntents;

public class BerandaFragment extends Fragment{

    private static final String TAG = BerandaFragment.class.getSimpleName();
    private RecyclerView.LayoutManager mLayoutManager;
    private ShimmerFrameLayout mShimmerViewContainer;
    private List<DataAgenda> agendaList = new ArrayList<DataAgenda>();

    public BerandaFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.btn_scan)
    ImageButton btn_scan;
    @BindView(R.id.txt_agenda)
    TextView txt_agenda;
    @BindView(R.id.rv_schedule)
    RecyclerView rv_schedule;
    AdapterAgenda adapter_agenda;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        ButterKnife.bind(this, view);

        mShimmerViewContainer = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);

        btn_scan = (ImageButton) view.findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent captureIntent = new Intent(getContext(), ScanActivity.class);

                CaptureActivityIntents.setPromptMessage(captureIntent, "Qr scanning...");

                startActivityForResult(captureIntent, 0);
            }
        });

        getAgenda();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        txt_agenda.setVisibility(View.GONE);
//        txt_event.setVisibility(View.GONE);
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
//        txt_agenda.setVisibility(View.VISIBLE);
//        txt_event.setVisibility(View.VISIBLE);
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    public void getAgenda() {
        adapter_agenda = new AdapterAgenda(getActivity());
        rv_schedule.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
        rv_schedule.setAdapter(adapter_agenda);
        mShimmerViewContainer.startShimmerAnimation();
        txt_agenda.setVisibility(View.GONE);
        adapter_agenda.getDatas().clear();
        adapter_agenda.notifyDataSetChanged();

        final StringRequest request = new StringRequest(Request.Method.POST, Server.fetch_agenda, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataAgenda>> token = new TypeToken<List<DataAgenda>>() {
                    };
                    agendaList = gson.fromJson(data.toString(), token.getType());
                    adapter_agenda.setDatas(agendaList);
                    adapter_agenda.notifyDataSetChanged();


                    // stop animating Shimmer and hide the layout
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    txt_agenda.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);
    }

}