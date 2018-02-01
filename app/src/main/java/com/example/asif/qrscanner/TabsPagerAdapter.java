package com.example.asif.qrscanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by asif on 01-Feb-18.
 */

class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CodeFragment codeFragment = new CodeFragment();
                return codeFragment;
            case 1:
                ParticipantsFragment participantsFragment = new ParticipantsFragment();
                return participantsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Code";
            case 1:
                return "Participants";
            default:
                return null;
        }
    }
}
