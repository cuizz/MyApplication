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
import android.util.Log;

import com.example.administrator.myapplication.util.FragmengOne;
import com.example.administrator.myapplication.util.FragmengThree;
import com.example.administrator.myapplication.util.FragmengTwo;
import com.itheima.loopviewpager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;


public class ThirdActivity extends AppCompatActivity {
    LoopViewPager loopViewPager;
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

        loopViewPager = (LoopViewPager) findViewById(R.id.lvp_pager);
        loopViewPager.setImgData(imgListString());
        loopViewPager.setTitleData(titleListString());
        loopViewPager.start();
    }
    private List<String> imgListString() {
        List<String> imageData = new ArrayList<>();
        imageData.add("http://d.hiphotos.baidu.com/image/h%3D200/sign=72b32dc4b719ebc4df787199b227cf79/58ee3d6d55fbb2fb48944ab34b4a20a44723dcd7.jpg");
        imageData.add("http://pic.4j4j.cn/upload/pic/20130815/31e652fe2d.jpg");
        imageData.add("http://pic.4j4j.cn/upload/pic/20130815/5e604404fe.jpg");
        imageData.add("http://pic.4j4j.cn/upload/pic/20130909/681ebf9d64.jpg");
        imageData.add("http://d.hiphotos.baidu.com/image/pic/item/54fbb2fb43166d22dc28839a442309f79052d265.jpg");
        return imageData;
       // Log.e("zzz","zzzz");
    }

    private List<String> titleListString() {
        List<String> titleData = new ArrayList<>();
        titleData.add("1、在这里等着你");
        titleData.add("2、在你身边");
        titleData.add("3、打电话给你就是想说声“嗨”");
        titleData.add("4、不介意你对我大喊大叫");
        titleData.add("5、期待你总是尽全力");
        return titleData;
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
