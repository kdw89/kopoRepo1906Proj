package edu.polyedu.stylera_0618;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private SearchFragment searchFragment = new SearchFragment();
    private BookmarkFragment bookmarkFragment = new BookmarkFragment();
    private ChartFragment chartFragment = new ChartFragment();
    private PromotionFragment promotionFragment = new PromotionFragment();
    private SettingFragment settingFragment = new SettingFragment();
    FragmentTransaction transaction = fragmentManager.beginTransaction();


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch(keyCode) {
            case KeyEvent.KEYCODE_BACK:
                BottomNavigationView navView = findViewById(R.id.nav_view);
                transaction.replace(R.id.frame_layout, searchFragment).commitAllowingStateLoss();
                navView.setSelectedItemId(R.id.navigation_home);
                break;
        }
        return false;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.frame_layout, searchFragment).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_bookmark:
                    transaction.replace(R.id.frame_layout, bookmarkFragment).commitAllowingStateLoss();

                    return true;
                case R.id.navigation_chart:
                    transaction.replace(R.id.frame_layout, chartFragment).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_promotion:
                    transaction.replace(R.id.frame_layout, promotionFragment).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_settings:
                    transaction.replace(R.id.frame_layout, settingFragment).commitAllowingStateLoss();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stylera_main);

       BottomNavigationView navView = findViewById(R.id.nav_view);

        transaction.replace(R.id.frame_layout, searchFragment).commitAllowingStateLoss();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
