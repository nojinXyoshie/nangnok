package com.example.nojinx2.nangnok.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.JuriActivity;
import com.example.nojinx2.nangnok.LoginActivity;
import com.example.nojinx2.nangnok.MainActivity;
import com.example.nojinx2.nangnok.PanitiaActivity;
import com.example.nojinx2.nangnok.ParticipantsActivity;
import com.example.nojinx2.nangnok.R;
import com.example.nojinx2.nangnok.app.MyApplication;
import com.example.nojinx2.nangnok.data.DataPeserta;
import com.example.nojinx2.nangnok.util.Server;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.example.nojinx2.nangnok.LoginActivity.TAG_ALAMAT;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_BANK;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_EMAIL;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_ID;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_ID_USERS;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_KONTAK;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_LEVEL;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_NAMA;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_STATUS;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_VISI;

public class ProfilFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    SharedPreferences sharedpreferences;

    DataPeserta data;
    String id,nama, email, alamat, visi, kontak;

    EditText pw1, pw2, pw3;
    String type, pw1x, pw2x, pw3x;

    AlertDialog.Builder dialog;
    int success;
    LayoutInflater inflater;
    View dialogView;
    String tag_json_obj = "json_obj_req";

    private static final String TAG = ProfilFragment.class.getSimpleName();
    private String url = Server.URL + "change.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @BindView(R.id.change_password) Button change_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        //return inflater.inflate(R.layout.fragment_profil, parent, false);
        View view = inflater.inflate(R.layout.fragment_profil, parent, false);
        sharedpreferences = getActivity().getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);


        id = sharedpreferences.getString(TAG_ID_USERS, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        TextView txt_nama = (TextView) view.findViewById(R.id.nama);
        txt_nama.setText(nama);
        email = sharedpreferences.getString(TAG_EMAIL, null);
        TextView txt_email = (TextView) view.findViewById(R.id.txt_email);
        txt_email.setText(email);
        alamat = sharedpreferences.getString(TAG_ALAMAT, null);
        TextView txt_alamat = (TextView) view.findViewById(R.id.txt_alamat);
        txt_alamat.setText(alamat);
        visi = sharedpreferences.getString(TAG_VISI, null);
        TextView txt_visi = (TextView) view.findViewById(R.id.txt_visi);
        txt_visi.setText(visi);
        kontak = sharedpreferences.getString(TAG_KONTAK, null);
        TextView txt_kontak = (TextView) view.findViewById(R.id.txt_kontak);
        txt_kontak.setText(kontak);
        ImageView profil_image = (ImageView) view.findViewById(R.id.profile_image);
        Picasso.with(getContext()).load(Server.URL_foto_peserta + nama + "_closeup1.jpg")
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(profil_image, new Callback() {
                    @Override
                    public void onSuccess() {
//                        holder.loading_image.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
//                        holder.foto_seller.setImageDrawable(context.getResources().getDrawable(R.drawable.noimage));
//                        holder.loading_image.setVisibility(View.GONE);
                    }
                });
        change_password = view.findViewById(R.id.change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog();
            }
        });

        Button logout = (Button) view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_NAMA, null);
                editor.putString(TAG_LEVEL, null);
                editor.putString(TAG_STATUS, null);
                editor.putString(TAG_BANK, null);
                editor.putString(TAG_EMAIL, null);
                editor.putString(TAG_ALAMAT, null);
                editor.putString(TAG_VISI, null);
                editor.putString(TAG_KONTAK, null);
                editor.commit();

                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    private void Dialog(){
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_change_password, null);
        dialog.setView(dialogView);
        dialog.setTitle("Change Password");

        pw1 = (EditText) dialogView.findViewById(R.id.pw1);
        pw2 = (EditText) dialogView.findViewById(R.id.pw2);
        pw3 = (EditText) dialogView.findViewById(R.id.pw3);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pw1x = pw1.getText().toString();
                pw2x = pw2.getText().toString();
                pw3x = pw3.getText().toString();
                if (!validate()) {
                    return;
                } else {
                    change();
                }

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

    private void change() {

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {


                        Log.e("Successfully!", jObj.toString());

                        Toast.makeText(getContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("pw1", pw1x);
                params.put("pw2", pw2x);
                params.put("pw3", pw3x);

                return params;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    public boolean validate() {
        boolean valid = true;

        if (pw1x.isEmpty() ) {
            Toast.makeText(getContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
            valid = false;
        } else {
            pw1.setError(null);
        }

        if (pw2x.isEmpty() ) {
            Toast.makeText(getContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
            valid = false;
        } else {
            pw2.setError(null);
        }
        if (pw3x.isEmpty() ) {
            Toast.makeText(getContext(), "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
            valid = false;
        } else {
            pw3.setError(null);
        }

        return valid;
    }

}
