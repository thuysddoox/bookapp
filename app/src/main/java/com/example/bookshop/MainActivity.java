package com.example.bookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.bookshop.adapter.SliderAdapter;
import com.example.bookshop.adapter.ViewPagerAdapter;
import com.example.bookshop.api.ApiService;
import com.example.bookshop.model.ListBookResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SearchView.OnQueryTextListener {
//    SliderView sliderView;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private SearchView btnSearch;
    private ImageView btnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        bottomNavigationView = findViewById(R.id.bottomNav);
        btnSearch = findViewById(R.id.btnSearch);
        btnCart = findViewById(R.id.btnCart);
        viewPager = findViewById(R.id.viewPager);
        btnCart.setOnClickListener(this);
        btnSearch.setOnQueryTextListener(this);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menuHome).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menuFavorite).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menuHistory).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menuProfile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuHome:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menuFavorite:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menuHistory:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menuProfile:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCart:{
                Intent signupIntent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(signupIntent);
                break;
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
        intent.putExtra("key",s);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return false;
    }
}