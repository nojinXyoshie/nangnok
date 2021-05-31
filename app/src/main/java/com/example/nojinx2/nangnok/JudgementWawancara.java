package com.example.nojinx2.nangnok;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nojinx2.nangnok.data.DataWawancara;
import com.example.nojinx2.nangnok.fragment.AgamaFragment;
import com.example.nojinx2.nangnok.fragment.BasingFragment;
import com.example.nojinx2.nangnok.fragment.PariwisataFragment;
import com.example.nojinx2.nangnok.fragment.PengUmumFragment;
import com.example.nojinx2.nangnok.fragment.KesenianFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class JudgementWawancara extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    SharedPreferences sharedpreferences;
    DataWawancara data;

    @BindView(R.id.back)
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.judgement_wawancara);
        data = (DataWawancara) getIntent().getSerializableExtra("data");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PariwisataFragment(), "Pariwisata");
        adapter.addFragment(new KesenianFragment(), "Kesenian");
        adapter.addFragment(new PengUmumFragment(), "P.Umum");
        adapter.addFragment(new AgamaFragment(), "Agama");
        adapter.addFragment(new BasingFragment(), "Basing");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_panitia, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(LoginActivity.session_status, false);
            editor.commit();

            Intent intent = new Intent(JudgementWawancara.this.getApplication(), MainActivity.class);
            JudgementWawancara.this.finish();
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
