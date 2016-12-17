package com.beiange.efccp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnClickListener
{
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private LinearLayout mTabHome;
    private LinearLayout mTabCategory;
    private LinearLayout mTabCart;
    private LinearLayout mTabMember;

    private ImageButton mImgHome;
    private ImageButton mImgCategory;
    private ImageButton mImgCart;
    private ImageButton mImgMember;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);



        initView();
        initEvent();

        setSelect(1);
    }

    private void initEvent()
    {
        mTabHome.setOnClickListener(this);
        mTabCategory.setOnClickListener(this);
        mTabCart.setOnClickListener(this);
        mTabMember.setOnClickListener(this);
    }

    private void initView()
    {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabCategory = (LinearLayout) findViewById(R.id.id_tab_category);
        mTabCart = (LinearLayout) findViewById(R.id.id_tab_cart);
        mTabMember = (LinearLayout) findViewById(R.id.id_tab_member);

        mImgHome = (ImageButton) findViewById(R.id.id_tab_home_img);
        mImgCategory = (ImageButton) findViewById(R.id.id_tab_category_img);
        mImgCart = (ImageButton) findViewById(R.id.id_tab_cart_img);
        mImgMember = (ImageButton) findViewById(R.id.id_tab_member_img);

        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new MainFragment();
        Fragment mTab02 = new CategoryFragment();
        Fragment mTab03 = new CartFragment();
        Fragment mTab04 = new MemberFragment();
        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);
        mFragments.add(mTab04);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {

            @Override
            public int getCount()
            {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0)
            {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new OnPageChangeListener()
        {

            @Override
            public void onPageSelected(int arg0)
            {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.id_tab_home:
                setSelect(0);
                break;
            case R.id.id_tab_category:
                setSelect(1);
                break;
            case R.id.id_tab_cart:
                setSelect(2);
                break;
            case R.id.id_tab_member:
                setSelect(3);
                break;

            default:
                break;
        }
    }

    private void setSelect(int i)
    {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    private void setTab(int i)
    {
        resetImgs();
        switch (i)
        {
            case 0:
                mImgHome.setImageResource(R.drawable.tab_home_pressed);
                break;
            case 1:
                mImgCategory.setImageResource(R.drawable.tab_category_pressed);
                break;
            case 2:
                mImgCart.setImageResource(R.drawable.tab_cart_pressed);
                break;
            case 3:
                mImgMember.setImageResource(R.drawable.tab_member_pressed);
                break;
        }
    }

    /**
     * ÇÐ»»Í¼Æ¬ÖÁ°µÉ«
     */
    private void resetImgs()
    {
        mImgHome.setImageResource(R.drawable.tab_home_normal);
        mImgCategory.setImageResource(R.drawable.tab_category_normal);
        mImgCart.setImageResource(R.drawable.tab_cart_normal);
        mImgMember.setImageResource(R.drawable.tab_member_normal);
    }

}

