package com.example.nojinx2.nangnok;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nojinx2.nangnok.fragment.JuriInterviewFragment;
import com.example.nojinx2.nangnok.fragment.JuriKabisaFragment;
import com.example.nojinx2.nangnok.fragment.JuriPresentasiFragment;
import com.example.nojinx2.nangnok.fragment.JuriTop7Fragment;

import butterknife.ButterKnife;

public class JuriActivity extends AppCompatActivity {

    private static final String TAG = JuriActivity.class.getSimpleName();

    private Toolbar toolbar;
    public Toolbar getToolbar() { return toolbar; }

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public static final String TAG_USERNAME = "username";
    public static final String TAG_NAMA = "nama";
    public final static String TAG_LEVEL = "level";
    public final static String TAG_ID = "id";
    public final static String TAG_STATUS = "status";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_BANK = "bank_name";

    SharedPreferences sharedpreferences;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juri);
        ButterKnife.bind(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        loadFragment(new JuriKabisaFragment());
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_kabisa:
                    fragment = new JuriKabisaFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_presentasi:
                    fragment = new JuriPresentasiFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_interview:
                    fragment = new JuriInterviewFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_top7:
                    fragment = new JuriTop7Fragment();
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
        transaction.replace(R.id.content_juri, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Add your menu entries here
        getMenuInflater().inflate(R.menu.menu_juri, menu);
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
            editor.putString(TAG_EMAIL, null);
            editor.commit();

            Intent intent = new Intent(JuriActivity.this, MainActivity.class);
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
