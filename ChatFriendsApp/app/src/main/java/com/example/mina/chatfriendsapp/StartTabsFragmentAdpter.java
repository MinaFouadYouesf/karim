package com.example.mina.chatfriendsapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class StartTabsFragmentAdpter extends FragmentPagerAdapter {
    public StartTabsFragmentAdpter(FragmentManager fm)
    {
        super(fm);
    }

    // set data on every fragment
    @Override
    public Fragment getItem(int index) {
        Log.d("index", "" + index);
        // TODO Auto-generated method stub
        if (index == 0)
           return new Friends();
        if (index == 1)
           return new chat();
        if (index == 2)
            return new requests();


        return null;
    }

      @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Friends";
        if (position == 1)
            return"Chat";
        if (position == 2)
            return "Requests";


        return null;

    }
    // fragments(tabs) num
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }
}
