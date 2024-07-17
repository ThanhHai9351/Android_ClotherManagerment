package com.example.clotherapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.clotherapp.ADAPTER.ViewPagerAdapter;
import com.example.clotherapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.navigationBar);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        navigationView.getMenu().findItem(R.id.item_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.item_product).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.item_favourite).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.item_cart).setChecked(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.item_user).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case  R.id.item_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_product:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_favourite:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.item_cart:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.item_user:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }
}