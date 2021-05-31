package com.example.nojinx2.nangnok.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.LoginActivity;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.adapter.AdapterPesertaHome;
import com.example.nojinx2.nangnok.data.DataPeserta;
import com.example.nojinx2.nangnok.util.MySingleton;
import com.example.nojinx2.nangnok.util.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BerandaMainFragment extends Fragment {

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.nongnak, R.drawable.noknang2, R.drawable.noknang3};

    @BindView(R.id.rv_peserta)
    RecyclerView rv_peserta;
    AdapterPesertaHome adapter_peserta;

    private static final String TAG = BerandaMainFragment.class.getSimpleName();
    private List<DataPeserta> pesertaList = new ArrayList<DataPeserta>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        //return inflater.inflate(R.layout.fragment_profil, parent, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda_main, parent, false);
        ButterKnife.bind(this, view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        getPeserta();

        return view;
    }

    public void getPeserta() {
        adapter_peserta = new AdapterPesertaHome(getActivity());
        rv_peserta.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        rv_peserta.setAdapter(adapter_peserta);
        adapter_peserta.getDatas().clear();
        adapter_peserta.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.fetch_peserta, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataPeserta>> token = new TypeToken<List<DataPeserta>>() {
                    };
                    pesertaList = gson.fromJson(data.toString(), token.getType());
                    adapter_peserta.setDatas(pesertaList);
                    adapter_peserta.notifyDataSetChanged();
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
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }


}
