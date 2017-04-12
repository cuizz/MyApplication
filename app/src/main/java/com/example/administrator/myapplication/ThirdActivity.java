package com.example.administrator.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myapplication.util.FragmengOne;
import com.example.administrator.myapplication.util.FragmengThree;
import com.example.administrator.myapplication.util.FragmengTwo;

import java.util.ArrayList;
import java.util.List;


public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        setSupportActionBar(mToolbar);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
       // MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
       // viewPagerAdapter.addFragment(new FragmengOne(), "TabOne");//添加Fragment
      //  viewPagerAdapter.addFragment(new FragmengTwo(), "TabTwo");
      //  viewPagerAdapter.addFragment(new FragmengThree(), "TabThree");
       // mViewPager.setAdapter(viewPagerAdapter);//设置适配器

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("TabOne"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("TabTwo"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TabThree"));
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题
    }
    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();//添加的Fragment的集合
        private final List<String> mFragmentsTitles = new ArrayList<>();//每个Fragment对应的title的集合
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        /**
         * @param fragment      添加Fragment
         * @param fragmentTitle Fragment的标题，即TabLayout中对应Tab的标题
         */
        public void addFragment(Fragment fragment, String fragmentTitle) {
            mFragments.add(fragment);
            mFragmentsTitles.add(fragmentTitle);
        }

        @Override
        public Fragment getItem(int position) {
            //得到对应position的Fragment
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            //返回Fragment的数量
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //得到对应position的Fragment的title
            return mFragmentsTitles.get(position);
        }
    }
}
