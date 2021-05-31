package com.example.nojinx2.nangnok.Asessment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.LoginActivity;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.adapter.AdapterWawancaraSession;
import com.example.nojinx2.nangnok.data.DataWawancara;
import com.example.nojinx2.nangnok.data.DataWawancaraSession;
import com.example.nojinx2.nangnok.util.MySingleton;
import com.example.nojinx2.nangnok.util.Server;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Wawancara_asessment extends AppCompatActivity {

    DataWawancara data;

    SharedPreferences sharedPreferences;
    public static final String my_shared_preferences = "my_shared_preferences";
    //private ShimmerFrameLayout mShimmerViewContainer;
    private List<DataWawancaraSession> wawancaraList = new ArrayList<DataWawancaraSession>();
    @BindView(R.id.rv_assesment)
    RecyclerView rv_assesment;
    AdapterWawancaraSession adapter_wawancara_session;
    private static final String TAG = Wawancara_asessment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asessment_wawancara);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        data = (DataWawancara) getIntent().getSerializableExtra("data");
        getAssesment();

    }


    @Override
    public void onResume() {
        super.onResume();
        //mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        //mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }


    public void getAssesment() {
        adapter_wawancara_session = new AdapterWawancaraSession(Wawancara_asessment.this);
        rv_assesment.setLayoutManager(new GridLayoutManager(Wawancara_asessment.this, 2));
        rv_assesment.setAdapter(adapter_wawancara_session);
        adapter_wawancara_session.getDatas().clear();
        adapter_wawancara_session.notifyDataSetChanged();
        final StringRequest request = new StringRequest(Request.Method.POST, Server.fetch_wawancara_session, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    Gson gson = new Gson();
                    TypeToken<List<DataWawancaraSession>> token = new TypeToken<List<DataWawancaraSession>>() {
                    };
                    wawancaraList = gson.fromJson(data.toString(), token.getType());
                    adapter_wawancara_session.setDatas(wawancaraList);
                    adapter_wawancara_session.notifyDataSetChanged();
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
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(
                Server.TIMEOUT_ACCESS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(Wawancara_asessment.this).addToRequestQueue(request);
    }

}
