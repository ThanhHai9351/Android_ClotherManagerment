package com.example.clotherapp.ADAPTER;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.clotherapp.UI.DashboardFragment.DashboardFragment;
import com.example.clotherapp.UI.FavouriteFragment.FavouriteFragment;
import com.example.clotherapp.UI.InfoFragment.InfoFragment;
import com.example.clotherapp.UI.ProductFragment.ProductFragment;
import com.example.clotherapp.UI.ShoppingCartFragment.ShoppingCartFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new DashboardFragment();
            case 1:
                return new ProductFragment();
            case 2:
                return new FavouriteFragment();
            case 3:
                return new ShoppingCartFragment();
            case 4:
                return new InfoFragment();
            default:  return new DashboardFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
