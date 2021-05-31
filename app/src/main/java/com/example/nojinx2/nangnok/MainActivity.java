package com.example.nojinx2.nangnok;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nojinx2.nangnok.fragment.BerandaFragment;
import com.example.nojinx2.nangnok.fragment.BerandaMainFragment;
import com.example.nojinx2.nangnok.fragment.ChartFragment;
import com.example.nojinx2.nangnok.fragment.PesertaFragment;
import com.example.nojinx2.nangnok.fragment.PollingFragment;
import com.example.nojinx2.nangnok.fragment.ProfilFragment;
import com.example.nojinx2.nangnok.util.BottomNavigationBehavior;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nojinx2.nangnok.LoginActivity.TAG_ID_USERS;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = ParticipantsActivity.class.getSimpleName();

    private Toolbar toolbar;
    public Toolbar getToolbar() { return toolbar; }

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String TAG_USERNAME = "username";
    public static final String TAG_NAMA = "nama";
    public final static String TAG_LEVEL = "level";
    public final static String TAG_ID = "id";
    public final static String TAG_ID_AGENDA = "id_agenda";
    public final static String TAG_STATUS = "status";
    public final static String TAG_BANK = "bank_name";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    String id, nama, username, status, level, id_agenda, id_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());


        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        // Cek session login jika TRUE maka langsung buka MainActivity
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        id = sharedpreferences.getString(TAG_ID, null);
        id_users = sharedpreferences.getString(TAG_ID_USERS, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        level = sharedpreferences.getString(TAG_LEVEL, null);
        status = sharedpreferences.getString(TAG_STATUS, null);
        id_agenda = sharedpreferences.getString(TAG_ID_AGENDA, null);


        if (session) {
            if (level.equals("panitia")) {
                Intent intent = new Intent(MainActivity.this, PanitiaActivity.class);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_LEVEL, level);
                intent.putExtra(TAG_STATUS, status);
                finish();
                startActivity(intent);
            } else if (level.equals("peserta")) {
                Intent intent = new Intent(MainActivity.this, ParticipantsActivity.class);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_ID_USERS, id_users);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_NAMA, nama);
                intent.putExtra(TAG_LEVEL, level);
                intent.putExtra(TAG_STATUS, status);
                finish();
                startActivity(intent);
            } else if (level.equals("masyarakat")) {
                //Intent intent = new Intent(MainActivity.this, MainActivity.class);
                //intent.putExtra(TAG_ID, id);
                //finish();
                //startActivity(intent);
            } else if (level.equals("juri")){
                Intent intent = new Intent(MainActivity.this, JuriActivity.class);
                intent.putExtra(TAG_ID, id);
                intent.putExtra(TAG_ID_AGENDA, id_agenda);
                intent.putExtra(TAG_USERNAME, username);
                intent.putExtra(TAG_LEVEL, level);
                intent.putExtra(TAG_STATUS, status);
                finish();
                startActivity(intent);
            }

        }

        // load the store fragment by default
//        TextView toolbar_text = findViewById(R.id.toolbar_text);
//        toolbar_text.setText("Polling");
        loadFragment(new PollingFragment());
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_polling:
                    fragment = new PollingFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_beranda:
                    fragment = new BerandaMainFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_peserta:
                    fragment = new PesertaFragment();
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
        transaction.replace(R.id.content_general, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (session){
            if (level.equals("masyarakat")){
                getMenuInflater().inflate(R.menu.menu_panitia, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        } else {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_logout) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(LoginActivity.session_status, false);
            editor.putString(TAG_ID, null);
            editor.putString(TAG_ID_USERS, null);
            editor.putString(TAG_LEVEL, null);
            editor.commit();

            MainActivity.this.finish();
            return true;
        } else if (id == R.id.action_chart) {
            Intent intent = new Intent(MainActivity.this, ChartActivity.class);
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
