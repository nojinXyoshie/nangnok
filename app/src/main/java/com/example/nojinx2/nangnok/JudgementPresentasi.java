package com.example.nojinx2.nangnok;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.app.MyApplication;
import com.example.nojinx2.nangnok.data.DataPresentasi;
import com.example.nojinx2.nangnok.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nojinx2.nangnok.JuriActivity.TAG_ID;

public class JudgementPresentasi extends AppCompatActivity {

    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.nilai)
    EditText nilai;
    @BindView(R.id.note)
    EditText note;
    @BindView(R.id.btn_simpan)
    Button btn_simpan;
    @BindView(R.id.btn_simpan_note)
    Button btn_simpan_note;
    @BindView(R.id.spinner_note)
    Spinner spinner_note;
    private static final String DESCRIBABLE_KEY = "data";
    String nilaix, notex, kategorix;
    private static final String TAG = JudgementPresentasi.class.getSimpleName();
    String tag_json_obj = "json_obj_req";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    SharedPreferences sharedPreferences;
    String id_users, id_participants;
    DataPresentasi data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judgement_presentasi);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id_users = sharedPreferences.getString(TAG_ID, null);

        data = (DataPresentasi) getIntent().getSerializableExtra("data");
        nama.setText(data.getFull_name());
        id_participants = data.getId_participants();

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validate()) {
                    return;
                } else {
                    AlertDialog.Builder alertDialoBuilder = new AlertDialog.Builder(JudgementPresentasi.this);
                    alertDialoBuilder.setTitle("Alert!");
                    alertDialoBuilder.setMessage("Apakah anda yakin?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    input();
                                    finish();

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
                    AlertDialog.Builder alertDialoBuilder = new AlertDialog.Builder(JudgementPresentasi.this);
                    alertDialoBuilder.setTitle("Alert!");
                    alertDialoBuilder.setMessage("Apakah anda yakin?")
                            .setCancelable(false)
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    input_note();
                                    finish();
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
            nilaix = data.getPresentasiBean().getValue();

            notex = data.getCatatanBean().getNote();
            kategorix = data.getCatatanBean().getStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (nilaix == null){
            nilai.setText("");
        } else {
            nilai.setText(nilaix);
            nilai.setEnabled(false);
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

        if(nilaix != null){
            btn_simpan.setVisibility(View.GONE);
        }

    }

    public void input(){
        Log.d(TAG, "Sending...");

        String nilaix = nilai.getText().toString();

        checkInput(nilaix);

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

    private void checkInput(final String nilaix) {

        StringRequest strReq = new StringRequest(Request.Method.POST, Server.input_nilai_presentasi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Input Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Input Nilai Sukses!", jObj.toString());

                        Toast.makeText(JudgementPresentasi.this,
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        finish();

                    } else {
                        Toast.makeText(JudgementPresentasi.this,
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
                Toast.makeText(JudgementPresentasi.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nilai", nilaix);
                params.put("id_users", id_users);
                params.put("id_participants", id_participants);

                return params;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void checkInput_note(final String notex, final String kategori) {

        StringRequest strReq = new StringRequest(Request.Method.POST, Server.input_note_presentasi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Input Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Input Note Sukses!", jObj.toString());

                        Toast.makeText(JudgementPresentasi.this,
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        finish();

                    } else {
                        Toast.makeText(JudgementPresentasi.this,
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
                Toast.makeText(JudgementPresentasi.this, error.getMessage(), Toast.LENGTH_LONG).show();
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

        String nilaix = nilai.getText().toString();

        if (nilaix.isEmpty() ) {
            nilai.setError("tidak boleh kosong");
            valid = false;
        } else {
            nilai.setError(null);
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


    @Override
    public void onBackPressed() {
        finish();
    }
}

