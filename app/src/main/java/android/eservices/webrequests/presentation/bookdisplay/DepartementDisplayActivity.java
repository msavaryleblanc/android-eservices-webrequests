package android.eservices.webrequests.presentation.bookdisplay;

import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.bookdisplay.favorite.fragment.FavoriteFragment;
import android.eservices.webrequests.presentation.bookdisplay.search.fragment.SearchFragment;
import android.eservices.webrequests.presentation.bookdisplay.service.fragment.ServicesFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DepartementDisplayActivity extends AppCompatActivity {

    private ViewPager viewPager;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        final SearchFragment searchFragment = SearchFragment.newInstance();
        final FavoriteFragment fragmentTwo = FavoriteFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return searchFragment;
                }
                //return searchFragment;
                return fragmentTwo;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return SearchFragment.TAB_NAME;
                }
                //return SearchFragment.TAB_NAME;
                return FavoriteFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }


}
