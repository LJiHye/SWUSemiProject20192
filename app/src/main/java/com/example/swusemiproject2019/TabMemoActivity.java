package com.example.swusemiproject2019;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TabMemoActivity extends AppCompatActivity {

    private TabLayout tabLayout2;
    private ViewPager viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_memo);

        // 객체 바인딩
        tabLayout2 = findViewById(R.id.tabLayout2);
        viewPager2 = findViewById(R.id.viewPager2);

        // 탭 생성
        tabLayout2.addTab(tabLayout2.newTab().setText("글쓰기"));
        tabLayout2.addTab(tabLayout2.newTab().setText("사진찍기"));
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);

        // ViewPager 생성
        MyPagerAdapter2 adapter2 = new MyPagerAdapter2(getSupportFragmentManager(), tabLayout2.getTabCount());
        viewPager2.setAdapter(adapter2);
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));

        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class MyPagerAdapter2 extends FragmentPagerAdapter {
        int tabSize;

        public MyPagerAdapter2(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment_modify();
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
