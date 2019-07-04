package com.example.swusemiproject2019;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.swusemiproject2019.model.MemberModel;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;

    Intent intent;
    MemberModel member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        // 객체 바인딩
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // 탭 생성
        tabLayout.addTab(tabLayout.newTab().setText("메모"));
        tabLayout.addTab(tabLayout.newTab().setText("회원정보"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // ViewPager 생성
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        intent = getIntent();
        member = (MemberModel)intent.getSerializableExtra("MEMBER");
        if(member == null) {
            Log.e(TAG, "전달받은 객체 : NULL");
        } else {
            Log.e(TAG, "전달받은 객체 : " + member.toString());
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        int tabSize;

        public MyPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.tabSize = count;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new Fragment_memo();
                case 1:
                    return new Fragment_info(member);
            }
            return null;
        }

        @Override
        public int getCount() {
            return this.tabSize;
        }
    }
}
