package com.example.nojinx2.nangnok;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.app.MyApplication;
import com.example.nojinx2.nangnok.data.DataPolling;
import com.example.nojinx2.nangnok.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

import static com.example.nojinx2.nangnok.LoginActivity.TAG_ID;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_LEVEL;

public class Polling extends AppCompatActivity {

    DataPolling data;
    private static final String TAG = Polling.class.getSimpleName();
    int success;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";
    String id_user, nama, id_participants;
    SharedPreferences sharedpreferences;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polling);
        ButterKnife.bind(this);
        data = (DataPolling) getIntent().getSerializableExtra("data");
        nama = data.getFull_name();

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        // Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        String level = sharedpreferences.getString(TAG_LEVEL, null);
        id_participants = data.getId_participants();
        id_user = sharedpreferences.getString(TAG_ID, null);

        if (session) {
            if (level.equals("masyarakat")) {
                showDialog();
            }
        } else {
            showDialog2();
        }

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    private void showDialog(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title dialog
        alertDialogBuilder.setTitle("Lakukan polling kepada "+nama+"?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("anda hanya dapat melakukan polling satu kali untuk satu peserta")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setNeutralButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // jika tombol diklik, maka akan menutup activity ini
                        StringRequest strReq = new StringRequest(Request.Method.POST, Server.polling, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, "Response: " + response.toString());

                                try {
                                    JSONObject jObj = new JSONObject(response);
                                    success = jObj.getInt(TAG_SUCCESS);

                                    // Cek error node pada json
                                    if (success == 1) {
                                        Log.d("Add", jObj.toString());

                                        Toast.makeText(Polling.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                        //adapter.notifyDataSetChanged();
                                        finish();

                                    } else {
                                        Toast.makeText(Polling.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                                        finish();
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
                                Toast.makeText(Polling.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }) {

                            @Override
                            protected Map<String, String> getParams() {
                                // Posting parameters ke post url
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("id_users", id_user);
                                params.put("id_participants", id_participants);

                                return params;
                            }

                        };

                        MyApplication.getInstance().addToRequestQueue(strReq, tag_json_obj);
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    private void showDialog2(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title dialog
        alertDialogBuilder.setTitle("Anda belum login!");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Tidak punya akun?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setNegativeButton("sudah punya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Polling.this, LoginActivity.class);
                        finish();
                        startActivity(intent);
                    }
                })
                .setNeutralButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Polling.this, RegisterActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

}
