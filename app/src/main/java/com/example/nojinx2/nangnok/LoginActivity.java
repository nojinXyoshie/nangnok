package com.example.nojinx2.nangnok;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.BottomSheetBehavior;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nojinx2.nangnok.app.MyApplication;
import com.example.nojinx2.nangnok.session.Session;
import com.example.nojinx2.nangnok.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    ProgressDialog pDialog;
    Button btn_login;
    EditText input_username, input_password;

    @BindView(R.id.back)
    ImageButton backButton;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "login.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String TAG_USERNAME = "username";
    public static final String TAG_NAMA = "nama";
    public final static String TAG_LEVEL = "level";
    public final static String TAG_ID = "id";
    public final static String TAG_ID_USERS = "id_users";
    public final static String TAG_ID_AGENDA = "id_agenda";
    public final static String TAG_STATUS = "status";
    public final static String TAG_BANK = "bank_name";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_VISI = "visi";
    public final static String TAG_KONTAK = "kontak";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    //String id, username, nama, level, status, bank_name;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet",
                        Toast.LENGTH_LONG).show();
            }
        }


        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        btn_login = findViewById(R.id.login);
        input_username = (EditText) findViewById(R.id.input_username);
        input_password = (EditText) findViewById(R.id.input_password);

        // Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        btn_login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        //showDialog();

        String username = input_username.getText().toString().trim();
        String password = input_password.getText().toString().trim();

        // TODO: Implement your own authentication logic here.

        // mengecek kolom yang kosong
        if (username.trim().length() > 0 && password.trim().length() > 0) {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
                checkLogin(username, password);
            } else {
                Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
            }
        } else {
            // Prompt user to enter credentials
            Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        //onLoginFailed();
                        pDialog.dismiss();
                    }
                }, 3000);
    }

    private void checkLogin(final String username, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        String id = jObj.getString(TAG_ID);
                        String level = jObj.getString(TAG_LEVEL);

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        if(level.equals("peserta")) {
                            String id_users = jObj.getString(TAG_ID_USERS);
                            String nama = jObj.getString(TAG_NAMA);
                            String status = jObj.getString(TAG_STATUS);
                            String bank_name = jObj.getString(TAG_BANK);
                            String email = jObj.getString(TAG_EMAIL);
                            String alamat = jObj.getString(TAG_ALAMAT);
                            String visi = jObj.getString(TAG_VISI);
                            String kontak = jObj.getString(TAG_KONTAK);
                            // menyimpan login ke session
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean(session_status, true);
                            editor.putString(TAG_ID, id);
                            editor.putString(TAG_ID_USERS, id_users);
                            editor.putString(TAG_LEVEL, level);
                            editor.putString(TAG_NAMA, nama);
                            editor.putString(TAG_STATUS, status);
                            editor.putString(TAG_BANK, bank_name);
                            editor.putString(TAG_EMAIL, email);
                            editor.putString(TAG_ALAMAT, alamat);
                            editor.putString(TAG_VISI, visi);
                            editor.putString(TAG_KONTAK, kontak);
                            editor.commit();
                            // Memanggil main activity
                            Intent intent = new Intent(LoginActivity.this, ParticipantsActivity.class);
                            intent.putExtra(TAG_ID, id);
                            intent.putExtra(TAG_NAMA, nama);
                            intent.putExtra(TAG_LEVEL, level);
                            intent.putExtra(TAG_STATUS, status);
                            intent.putExtra(TAG_BANK, bank_name);
                            finish();
                            startActivity(intent);
                        } else if (level.equals("panitia")){
                            String nama = jObj.getString(TAG_NAMA);
                            String status = jObj.getString(TAG_STATUS);
                            String email = jObj.getString(TAG_EMAIL);
                            //menyimpan ke session
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean(session_status, true);
                            editor.putString(TAG_ID, id);
                            editor.putString(TAG_LEVEL, level);
                            editor.putString(TAG_NAMA, nama);
                            editor.putString(TAG_STATUS, status);
                            editor.putString(TAG_EMAIL, email);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, PanitiaActivity.class);
                            intent.putExtra(TAG_ID, id);
                            intent.putExtra(TAG_LEVEL, level);
                            intent.putExtra(TAG_NAMA, nama);
                            intent.putExtra(TAG_STATUS, status);
                            intent.putExtra(TAG_EMAIL, email);
                            finish();
                            startActivity(intent);
                        } else if (level.equals("masyarakat")){
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean(session_status, true);
                            editor.putString(TAG_ID, id);
                            editor.putString(TAG_LEVEL, level);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(TAG_ID, id);
                            intent.putExtra(TAG_LEVEL, level);
                            finish();
                            startActivity(intent);
                        } else if (level.equals("juri")){
                            String nama = jObj.getString(TAG_NAMA);
                            String status = jObj.getString(TAG_STATUS);
                            String email = jObj.getString(TAG_EMAIL);
                            String id_agenda = jObj.getString(TAG_ID_AGENDA);
                            //menyimpan ke session
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean(session_status, true);
                            editor.putString(TAG_ID, id);
                            editor.putString(TAG_LEVEL, level);
                            editor.putString(TAG_NAMA, nama);
                            editor.putString(TAG_STATUS, status);
                            editor.putString(TAG_EMAIL, email);
                            editor.putString(TAG_ID_AGENDA, id_agenda);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, JuriActivity.class);
                            intent.putExtra(TAG_ID, id);
                            intent.putExtra(TAG_LEVEL, level);
                            intent.putExtra(TAG_NAMA, nama);
                            intent.putExtra(TAG_STATUS, status);
                            intent.putExtra(TAG_EMAIL, email);
                            finish();
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("username", username);
                data.put("password", password);
                return data;
            }
        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public  void onDestroy() {
        isDestroyed();
        super.onDestroy();
    }

    public void onLoginSuccess() {
        btn_login.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        //Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_login.setEnabled(true);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public boolean validate() {
        boolean valid = true;

        String email = input_username.getText().toString();
        String password = input_password.getText().toString();

        if (email.isEmpty() ) {
            input_username.setError("username tidak boleh kosong");
            valid = false;
        } else {
            input_username.setError(null);
        }

        if (password.isEmpty() ) {
            input_password.setError("password tidak boleh kosong");
            valid = false;
        } else {
            input_password.setError(null);

        }

        return valid;
    }
}
