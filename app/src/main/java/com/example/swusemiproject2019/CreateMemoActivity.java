package com.example.swusemiproject2019;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class CreateMemoActivity extends AppCompatActivity {

    private TabLayout tabLayout3;
    private ViewPager viewPager3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memo);

        // 객체 바인딩
        tabLayout3 = findViewById(R.id.tabLayout3);
        viewPager3 = findViewById(R.id.viewPager3);

        // 탭 생성
        tabLayout3.addTab(tabLayout3.newTab().setText("글쓰기"));
        tabLayout3.addTab(tabLayout3.newTab().setText("사진찍기"));
        tabLayout3.setTabGravity(TabLayout.GRAVITY_FILL);

        // ViewPager 생성
        MyPagerAdapter3 adapter3 = new MyPagerAdapter3(getSupportFragmentManager(), tabLayout3.getTabCount());
        viewPager3.setAdapter(adapter3);
        viewPager3.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout3));

        tabLayout3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager3.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    class MyPagerAdapter3 extends FragmentPagerAdapter {
        int tabSize;

        public MyPagerAdapter3(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment_create();
                case 1:
                    return new Fragment_picture();
            }
            return null;
        }

        @Override
        public int getCount() {
            return this.tabSize;
        }
    }

}
