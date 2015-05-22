package com.gabilheri.octokitten.ui.repo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.gabilheri.octokitten.R;
import com.gabilheri.octokitten.adapters.BaseViewPagerAdapter;
import com.gabilheri.octokitten.ui.repo.code.SourceCodeListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import timber.log.Timber;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 5/10/15.
 */
public class ReposListActivity extends BaseRepoActivity implements MaterialTabListener {

    @InjectView(R.id.materialTabHost)
    protected MaterialTabHost tabHost;

    @InjectView(R.id.pager)
    protected ViewPager pager;

    protected List<Fragment> fragmentList;
    protected List<CharSequence> titleList;
    protected BaseViewPagerAdapter adapter;

    public Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);

        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();

        extras = getIntent().getExtras().getBundle(Intent.EXTRA_INTENT);
        String repoName = extras.getString("title");
        setTitle(repoName);

        SourceCodeListFragment codeFragment = new SourceCodeListFragment();
        codeFragment.setArguments(extras);

        titleList.add("/");
        fragmentList.add(codeFragment);
        initPager();
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

    public void addFragment(String title, Fragment f) {
        String[] t = title.split("/");
        titleList.add(t[t.length -1]);
        fragmentList.add(f);
        pager.invalidate();
        tabHost.addTab(tabHost.newTab().setText(t[t.length -1]).setTabListener(this));
        tabHost.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        setPagerNumber(adapter.getCount() - 1);
    }

    public void initPager() {

        adapter = new BaseViewPagerAdapter(getFragmentManager(), fragmentList, titleList);
        pager.setOffscreenPageLimit(100); // Safe to ensure that no one has code with directories going 100 levels deep...
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                if (position < fragmentList.size() - 1) {

                    for (int i = fragmentList.size() - 1; i > position; i--) {
                        fragmentList.remove(i);
                        titleList.remove(i);
                    }
                    Timber.i("Pager Size: " + fragmentList.size());
                    initTabHost();
                    tabHost.notifyDataSetChanged();
                    tabHost.invalidate();
                    adapter.notifyDataSetChanged();
                    pager.invalidate();

                }
                tabHost.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initTabHost();
    }

    public void initTabHost() {
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
