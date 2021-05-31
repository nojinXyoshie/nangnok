package com.example.nojinx2.nangnok.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.LoginActivity;
import com.example.nojinx2.nangnok.MainActivity;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.adapter.AdapterWawancara;
import com.example.nojinx2.nangnok.data.DataWawancara;
import com.example.nojinx2.nangnok.util.MySingleton;
import com.example.nojinx2.nangnok.util.Server;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PanitiaWawancaraFragment extends Fragment {

    private static final String TAG = PanitiaWawancaraFragment.class.getSimpleName();
    private RecyclerView.LayoutManager mLayoutManager;
    private ShimmerFrameLayout mShimmerViewContainer;
    SharedPreferences sharedpreferences;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    String type;
    public static final String my_shared_preferences = "my_shared_preferences";
    private List<DataWawancara> wawancaraList = new ArrayList<DataWawancara>();

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public PanitiaWawancaraFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_wawancara)
    RecyclerView rv_wawancara;
    @BindView(R.id.cari)
    EditText cari;
    EditText cari_peserta;
    AdapterWawancara adapter_wawancara;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wawancara, container, false);
        ButterKnife.bind(this, view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        mShimmerViewContainer = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);
        sharedpreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        rv_wawancara.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));

        getWawancara();

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog();
            }
        });

        return view;
    }

    private void Dialog(){
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_cari_peserta, null);
        dialog.setView(dialogView);
        dialog.setTitle("Cari Peserta");

        cari_peserta = (EditText) dialogView.findViewById(R.id.cari_peserta);

        dialog.setPositiveButton("Cari", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                type = cari_peserta.getText().toString();
                cari.setText(type);
                CariPeserta();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void CariPeserta() {
        adapter_wawancara = new AdapterWawancara(getActivity());
        rv_wawancara.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rv_wawancara.setAdapter(adapter_wawancara);
        adapter_wawancara.getDatas().clear();
        adapter_wawancara.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.cari_wawancara, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataWawancara>> token = new TypeToken<List<DataWawancara>>() {
                    };
                    wawancaraList = gson.fromJson(data.toString(), token.getType());
                    adapter_wawancara.setDatas(wawancaraList);
                    adapter_wawancara.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                sharedpreferences = getActivity().getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

                params.put("nama", type);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);

    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }


    public void getWawancara() {
        adapter_wawancara = new AdapterWawancara(getActivity());
        rv_wawancara.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rv_wawancara.setAdapter(adapter_wawancara);
        mShimmerViewContainer.startShimmerAnimation();
        adapter_wawancara.getDatas().clear();
        adapter_wawancara.notifyDataSetChanged();

        final StringRequest request = new StringRequest(Request.Method.POST, Server.fetch_wawancara, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataWawancara>> token = new TypeToken<List<DataWawancara>>() {
                    };
                    wawancaraList = gson.fromJson(data.toString(), token.getType());
                    adapter_wawancara.setDatas(wawancaraList);
                    adapter_wawancara.notifyDataSetChanged();


                    // stop animating Shimmer and hide the layout
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
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