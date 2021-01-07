package android.eservices.webrequests.presentation.bookdisplay;

import android.content.Intent;
import android.eservices.webrequests.R;
import android.eservices.webrequests.data.api.model.Departement;
import android.eservices.webrequests.data.api.model.Service;
import android.eservices.webrequests.presentation.bookdisplay.favorite.fragment.FavoriteFragment;
import android.eservices.webrequests.presentation.bookdisplay.search.fragment.SearchFragment;
import android.eservices.webrequests.presentation.bookdisplay.service.fragment.ServicesFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ServiceDisplayActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_DEPARTEMENT = " ";
    private ViewPager viewPager;
    private String depId;
    private String depName;
    private Departement departement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Intent intent = getIntent();
        depId = intent.getStringExtra(ServiceDisplayActivity.EXTRA_MESSAGE);
        depName = intent.getStringExtra(ServiceDisplayActivity.EXTRA_DEPARTEMENT);
        setupViewPagerAndTabs();
    }
    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        final ServicesFragment servicesFragment = new ServicesFragment(depId,depName);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return servicesFragment;
                }
                //return searchFragment;
                return servicesFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return ServicesFragment.TAB_NAME;
                }
                //return SearchFragment.TAB_NAME;
                return FavoriteFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
    }
}
