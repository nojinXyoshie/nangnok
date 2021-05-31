package com.example.nojinx2.nangnok;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

//import static com.example.nojinx2.nangnok.LoginActivity.my_shared_preferences;

import com.example.nojinx2.nangnok.fragment.AgendaFragment;
import com.example.nojinx2.nangnok.fragment.BerandaFragment;
import com.example.nojinx2.nangnok.fragment.ProfilFragment;
import com.example.nojinx2.nangnok.util.BottomNavigationBehavior;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.CaptureActivityIntents;

import static com.example.nojinx2.nangnok.LoginActivity.TAG_ALAMAT;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_BANK;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_EMAIL;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_ID;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_KONTAK;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_LEVEL;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_NAMA;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_STATUS;
import static com.example.nojinx2.nangnok.LoginActivity.TAG_VISI;

public class ParticipantsActivity extends AppCompatActivity {

    private static final String TAG = ParticipantsActivity.class.getSimpleName();

    private Toolbar toolbar;
    public Toolbar getToolbar() { return toolbar; }

    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;

    @BindView(R.id.btnLanjut)
    Button btnLanjut;

    SharedPreferences sharedpreferences;
    String status;

    BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());


        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        status = getIntent().getStringExtra(TAG_STATUS);
        if(status.equals("0")){
            sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            ImageButton img_close = (ImageButton) findViewById(R.id.img_close);
            img_close.setOnClickListener(new View.OnClickListener() {
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

                    Intent intent = new Intent(ParticipantsActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            });

            /**
             * bottom sheet state change listener
             * we are changing button text when sheet changed state
             * */
            sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    switch (newState) {
                        case BottomSheetBehavior.STATE_EXPANDED: {
                            //btnBottomSheet.setText("Close Sheet");
                        }
                        break;
                        case BottomSheetBehavior.STATE_COLLAPSED: {
                            //btnBottomSheet.setText("Expand Sheet");
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

                            Intent intent = new Intent(ParticipantsActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        }
                        break;
                        case BottomSheetBehavior.STATE_DRAGGING:
                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            break;

                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        } else if(status.equals("1")) {

            sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
            sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            //sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                finish();
                startActivity(intent);
            }
        });

        loadFragment(new BerandaFragment());
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
//                case R.id.navigation_riwayat:
//                    fragment = new AgendaFragment();
//                    loadFragment(fragment);
//                    return true;
                case R.id.navigation_beranda:
                    fragment = new BerandaFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profil:
                    fragment = new ProfilFragment();
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
        transaction.replace(R.id.content_participants, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        finish();
        moveTaskToBack(true);
    }
}
