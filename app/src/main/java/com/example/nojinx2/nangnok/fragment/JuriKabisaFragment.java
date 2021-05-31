package com.example.nojinx2.nangnok.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.LoginActivity;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.adapter.AdapterKabisa;
import com.example.nojinx2.nangnok.data.DataKabisa;
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

import static com.example.nojinx2.nangnok.LoginActivity.TAG_ID;
import static com.example.nojinx2.nangnok.LoginActivity.my_shared_preferences;

public class JuriKabisaFragment extends Fragment {

    private static final String TAG = JuriKabisaFragment.class.getSimpleName();
    private RecyclerView.LayoutManager mLayoutManager;
    private ShimmerFrameLayout mShimmerViewContainer;
    SharedPreferences sharedpreferences;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    String type;
    private List<DataKabisa> kabisaList = new ArrayList<DataKabisa>();
    String id_users;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public JuriKabisaFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_kabisa)
    RecyclerView rv_kabisa;
    @BindView(R.id.cari)
    EditText cari;
    EditText cari_peserta;
    AdapterKabisa adapter_kabisa;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kabisa, container, false);
        ButterKnife.bind(this, view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        sharedpreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id_users = sharedpreferences.getString(TAG_ID, null);

        mShimmerViewContainer = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);
        sharedpreferences = getActivity().getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);

        rv_kabisa.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));

        getKabisa();

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
        adapter_kabisa = new AdapterKabisa(getActivity());
        rv_kabisa.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rv_kabisa.setAdapter(adapter_kabisa);
        adapter_kabisa.getDatas().clear();
        adapter_kabisa.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.cari_kabisa, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataKabisa>> token = new TypeToken<List<DataKabisa>>() {
                    };
                    kabisaList = gson.fromJson(data.toString(), token.getType());
                    adapter_kabisa.setDatas(kabisaList);
                    adapter_kabisa.notifyDataSetChanged();
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


    public void getKabisa() {
        adapter_kabisa = new AdapterKabisa(getActivity());
        rv_kabisa.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rv_kabisa.setAdapter(adapter_kabisa);
        mShimmerViewContainer.startShimmerAnimation();
        adapter_kabisa.getDatas().clear();
        adapter_kabisa.notifyDataSetChanged();

        final StringRequest request = new StringRequest(Request.Method.POST, Server.fetch_kabisa, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataKabisa>> token = new TypeToken<List<DataKabisa>>() {
                    };
                    kabisaList = gson.fromJson(data.toString(), token.getType());
                    adapter_kabisa.setDatas(kabisaList);
                    adapter_kabisa.notifyDataSetChanged();


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
        }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("id_users", id_users);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);
    }

}
