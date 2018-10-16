package com.example.kike.testgrin.View.Implementation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.kike.testgrin.Adapter.ViewPagerAdapter;
import com.example.kike.testgrin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagerMenuActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);
        initControl();
        initDataControl();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void initControl()
    {
        ButterKnife.bind(this);
    }

    private void initDataControl()
    {
        this.SetupViewPager(viewPager, getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        this.SetupTabBar(tabLayout);
    }

    public void SetupViewPager(ViewPager viewPager, FragmentManager fragmentManager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFragment(new Fragment_Search());
        adapter.addFragment(new Fragment_Registered());
        viewPager.setAdapter(adapter);
    }

    public void SetupTabBar(TabLayout tabLayout) {

        TextView tabOne = this.setTextView(0, true);
        TextView tabTwo = this.setTextView(1, false);

        tabLayout.getTabAt(0).setCustomView(tabOne);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final TextView tabSelected = setTextView( tab.getPosition(), true);
                tab.setCustomView(null);
                tab.setCustomView(tabSelected);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                final TextView tabUnSelected = setTextView(tab.getPosition(), false);
                tab.setCustomView(null);
                tab.setCustomView(tabUnSelected);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                final TextView tabSelected = setTextView( tab.getPosition(), true);
                tab.setCustomView(null);
                tab.setCustomView(tabSelected);
            }
        });
    }

    private TextView setTextView(Integer option, Boolean status)
    {
        TextView resultTextViewSelected = null;
        switch (option)
        {
             case 0:
                if(status)
                {
                    resultTextViewSelected = this.setTextViewTab(R.drawable.ic_bluetooth_on,
                            R.string.text_tab_device,
                            R.color.tab_off);
                }
                else
                {
                    resultTextViewSelected = this.setTextViewTab(R.drawable.ic_bluetooth_off,
                            R.string.text_tab_device,
                            R.color.colorPrimaryDark);
                }
                break;
            case 1:
                if(status)
                {
                    resultTextViewSelected = this.setTextViewTab(R.drawable.ic_list_on,
                            R.string.text_tab_list,
                            R.color.tab_off);
                }
                else
                {
                    resultTextViewSelected = this.setTextViewTab(R.drawable.ic_list_off,
                            R.string.text_tab_list,
                            R.color.colorPrimaryDark);
                }
                break;
        }
        return  resultTextViewSelected;
    }

    private TextView setTextViewTab(Integer image, Integer text, Integer color)
    {
        final TextView tabCustom = (TextView) LayoutInflater.from(this).inflate(R.layout.tab, null);
        tabCustom.setText(text);
        tabCustom.setTextColor(color);
        tabCustom.setCompoundDrawablesWithIntrinsicBounds(0, image, 0, 0);
        return tabCustom;
    }
}
