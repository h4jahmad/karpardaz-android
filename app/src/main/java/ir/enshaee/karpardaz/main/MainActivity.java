package ir.enshaee.karpardaz.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ir.enshaee.karpardaz.R;
import ir.enshaee.karpardaz.main.adapter.MainViewPagerAdapter;
import ir.enshaee.karpardaz.main.report.ReportFragment;
import ir.enshaee.karpardaz.main.tankhah.TankhahFragment;
import ir.enshaee.karpardaz.main.tojibi.TojibiFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        final TabLayout tabs = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.vp_main);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(2);
        tabs.getTabAt(2);

    }


    private void setupViewPager(ViewPager viewPager) {
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ReportFragment(), getString(R.string.reports));
        adapter.addFragment(new TankhahFragment(), getString(R.string.tankhah));
        adapter.addFragment(new TojibiFragment(), getString(R.string.tojibi));
        viewPager.setAdapter(adapter);
    }


}
