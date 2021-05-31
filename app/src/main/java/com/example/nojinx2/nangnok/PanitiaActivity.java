package com.example.nojinx2.nangnok;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.nojinx2.nangnok.fragment.BerandaMainFragment;
import com.example.nojinx2.nangnok.fragment.PanitiaAdministrasiFragment;
import com.example.nojinx2.nangnok.fragment.PanitiaPersonalityFragment;
import com.example.nojinx2.nangnok.fragment.PanitiaTesTulisFragment;
import com.example.nojinx2.nangnok.fragment.PanitiaWawancaraFragment;
import com.example.nojinx2.nangnok.fragment.PesertaFragment;
import com.example.nojinx2.nangnok.fragment.PollingFragment;

import butterknife.ButterKnife;

import static com.example.nojinx2.nangnok.LoginActivity.TAG_ALAMAT;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_BANK;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_EMAIL;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_ID;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_KONTAK;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_LEVEL;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_NAMA;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_STATUS;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_VISI;

public class PanitiaActivity extends AppCompatActivity {

    private static final String TAG = ParticipantsActivity.class.getSimpleName();

    private Toolbar toolbar;
    public Toolbar getToolbar() { return toolbar; }

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String TAG_USERNAME = "username";
    public static final String TAG_NAMA = "nama";
    public final static String TAG_LEVEL = "level";
    public final static String TAG_ID = "id";
    public final static String TAG_STATUS = "status";
    public final static String TAG_BANK = "bank_name";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia);
        ButterKnife.bind(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        loadFragment(new PanitiaWawancaraFragment());
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_wawancara:
                    fragment = new PanitiaWawancaraFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_administrasi:
                    fragment = new PanitiaAdministrasiFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_testulis:
                    fragment = new PanitiaTesTulisFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_personality:
                    fragment = new PanitiaPersonalityFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_panitia, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Add your menu entries here
        getMenuInflater().inflate(R.menu.menu_panitia, menu);
        //super.onCreateOptionsMenu(menu, inflater);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
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

            Intent intent = new Intent(PanitiaActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        finish();
        moveTaskToBack(true);
    }

}
