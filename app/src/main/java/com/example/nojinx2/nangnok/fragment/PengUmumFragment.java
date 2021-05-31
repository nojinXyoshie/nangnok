package com.example.nojinx2.nangnok.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.LoginActivity;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.app.MyApplication;
import com.example.nojinx2.nangnok.data.DataWawancara;
import com.example.nojinx2.nangnok.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nojinx2.nangnok.PanitiaActivity.TAG_ID;

public class PengUmumFragment extends Fragment {

    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.interview)
    EditText interview;
    @BindView(R.id.grooming)
    EditText grooming;
    @BindView(R.id.manner)
    EditText manner;
    @BindView(R.id.note)
    EditText note;
    @BindView(R.id.btn_simpan)
    Button btn_simpan;
    @BindView(R.id.btn_simpan_note)
    Button btn_simpan_note;
    @BindView(R.id.spinner_note)
    Spinner spinner_note;
    private static final String DESCRIBABLE_KEY = "data";
    DataWawancara data;
    String interviewx, groomingx, mannerx, notex, kategorix;
    private static final String TAG = KesenianFragment.class.getSimpleName();
    String tag_json_obj = "json_obj_req";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    SharedPreferences sharedPreferences;
    String id_users, id_participants;

    public PengUmumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peng_umum, container, false);
        ButterKnife.bind(this, view);

        sharedPreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id_users = sharedPreferences.getString(TAG_ID, null);

        data = (DataWawancara) getActivity().getIntent().getSerializableExtra("data");
        nama.setText(data.getFull_name());
        id_participants = data.getId_participants();


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validate()) {
                    return;
                } else {
                    AlertDialog.Builder alertDialoBuilder = new AlertDialog.Builder(getContext());
                    alertDialoBuilder.setTitle("Alert!");
                    alertDialoBuilder.setMessage("Apakah anda yakin?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    input();
                                    getActivity().finish();

                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialoBuilder.create();
                    alertDialog.show();
                }
            }
        });

        btn_simpan_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validate_note()) {
                    return;
                } else {
                    AlertDialog.Builder alertDialoBuilder = new AlertDialog.Builder(getContext());
                    alertDialoBuilder.setTitle("Alert!");
                    alertDialoBuilder.setMessage("Apakah anda yakin?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    input_note();
                                    getActivity().finish();
                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialoBuilder.create();
                    alertDialog.show();
                }
            }
        });

        try {
            interviewx = data.getWawancaraBean().getPariwisataBean().getInterviewPengetahuanBean().getValue();
            groomingx = data.getWawancaraBean().getPariwisataBean().getGroomingPengetahuanBean().getValue();
            mannerx = data.getWawancaraBean().getPariwisataBean().getMannerPengetahuanBean().getValue();

            notex = data.getWawancaraBean().getPariwisataBean().getCatatanPengumumBean().getNote();
            kategorix = data.getWawancaraBean().getPariwisataBean().getCatatanPengumumBean().getStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (interviewx == null){
            interview.setText("");
        } else {
            interview.setText(data.getWawancaraBean().getPariwisataBean().getInterviewPengetahuanBean().getValue());
            interview.setEnabled(false);
        }

        if (groomingx == null){
            grooming.setText("");
        } else {
            grooming.setText(data.getWawancaraBean().getPariwisataBean().getGroomingPengetahuanBean().getValue());
            grooming.setEnabled(false);
        }

        if (mannerx == null){
            manner.setText("");
        } else {
            manner.setText(data.getWawancaraBean().getPariwisataBean().getMannerPengetahuanBean().getValue());
            manner.setEnabled(false);
        }

        if (notex == null) {
            note.setText("");
            status.setVisibility(View.GONE);
        } else {
            note.setText(notex);
            note.setEnabled(false);
            status.setText(kategorix);
            spinner_note.setVisibility(View.GONE);
            btn_simpan_note.setVisibility(View.GONE);
        }

        if(interviewx != null && groomingx != null && mannerx != null){
            btn_simpan.setVisibility(View.GONE);
        }

        return view;
    }

    public void input(){
        Log.d(TAG, "Sending...");

        String interviewx = interview.getText().toString();
        String groomingx = grooming.getText().toString();
        String mannerx = manner.getText().toString();

        checkInput(interviewx, groomingx, mannerx);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);

    }

    public void input_note(){
        Log.d(TAG, "Sending...");

        String notex = note.getText().toString();
        String kategori = spinner_note.getSelectedItem().toString();

        checkInput_note(notex, kategori);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
    }

    private void checkInput(final String interviewx, final String groomingx, final String mannerx) {

        StringRequest strReq = new StringRequest(Request.Method.POST, Server.input_nilai_pengumum, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Input Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Input Nilai Sukses!", jObj.toString());

                        Toast.makeText(getActivity(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        getActivity().finish();

                    } else {
                        Toast.makeText(getActivity(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("interview", interviewx);
                params.put("grooming", groomingx);
                params.put("manner", mannerx);
                params.put("id_users", id_users);
                params.put("id_participants", id_participants);

                return params;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void checkInput_note(final String notex, final String kategori) {

        StringRequest strReq = new StringRequest(Request.Method.POST, Server.input_note_pengumum, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Input Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Input Note Sukses!", jObj.toString());

                        Toast.makeText(getActivity(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        getActivity().finish();

                    } else {
                        Toast.makeText(getActivity(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("note", notex);
                params.put("kategori", kategori);
                params.put("id_users", id_users);
                params.put("id_participants", id_participants);

                return params;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    public boolean validate() {
        boolean valid = true;

        String interviewx = interview.getText().toString();
        String groomingx = grooming.getText().toString();
        String mannerx = manner.getText().toString();

        if (interviewx.isEmpty() ) {
            interview.setError("tidak boleh kosong");
            valid = false;
        } else {
            interview.setError(null);
        }

        if (groomingx.isEmpty() ) {
            grooming.setError("tidak boleh kosong");
            valid = false;
        } else {
            interview.setError(null);
        }

        if (mannerx.isEmpty() ) {
            manner.setError("tidak boleh kosong");
            valid = false;
        } else {
            manner.setError(null);
        }

        return valid;
    }

    public boolean validate_note() {
        boolean valid = true;

        String notex = note.getText().toString();

        if (notex.isEmpty()) {
            note.setError("Tidak boleh kosong");
            valid = false;
        } else  {
            note.setError(null);
        }

        return valid;
    }

}
