package com.tengfei.fairy.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Description :viewpage 适配器
 * @ Author 李腾飞
 * @ Time 2020-09-08   16:19
 * @ Version :
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    public void addFragment(Fragment fragment,String title,int index){
        mFragments.add(index,fragment);
        mFragmentTitles.add(index,title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
    }

    public void remove(int index) {
        mFragmentTitles.remove(index);
        mFragments.remove(index);
        notifyDataSetChanged();
    }

    public void removeAll(){
        mFragmentTitles.clear();
        mFragments.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public long getItemId(int position) {
        // 获取当前数据的hashCode
        int hashCode = mFragments.get(position).hashCode();
        return hashCode;
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }
}
