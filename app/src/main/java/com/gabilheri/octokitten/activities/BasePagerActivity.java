package com.gabilheri.octokitten.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.adapters.BaseViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class BasePagerActivity extends BaseActivity implements MaterialTabListener {

    @InjectView(R.id.materialTabHost)
    protected MaterialTabHost tabHost;

    @InjectView(R.id.pager)
    protected ViewPager pager;

    protected List<Fragment> fragmentList;
    protected List<CharSequence> titleList;
    protected BaseViewPagerAdapter adapter;
    protected static String LOG_TAG = BasePagerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        pager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    public void initPager() {

        adapter = new BaseViewPagerAdapter(getFragmentManager(), fragmentList, titleList);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for(int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(tabHost.newTab().setText(adapter.getPageTitle(i)).setTabListener(this));
        }
    }

    public void setPagerNumber(int number) {
        pager.setCurrentItem(number);
        tabHost.setSelectedNavigationItem(number);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_base_pager;
    }
}
