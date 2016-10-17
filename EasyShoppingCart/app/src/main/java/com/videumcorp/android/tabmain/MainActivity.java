package com.videumcorp.android.tabmain;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.code.controller.Controller;

import libxml.SlidingTabLayout;

import static com.videumcorp.android.tabmain.R.id.navigation_drawer_layout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    FloatingActionButton cartbutton;
    //--------------------------------------
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Gợi ý", "Điện thoại", "Máy tính", "Khác"};
    int Numboftabs = 4;
    NavigationView navigationView;
    //--------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller.setActivitySave(MainActivity.this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(navigation_drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent();
        }
        setupNavigationDrawerContent();
        navigationView.setVerticalScrollBarEnabled(true);

        //setupNavigationDrawerContent();
        //-------------------------------------------
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setHorizontalScrollBarEnabled(true);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        // tabs.setSelectedIndicatorColors(R.attr.colorPrimary);
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.md_white_1000);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
        //----------------------------------------------
        cartbutton = (FloatingActionButton) findViewById(R.id.main_floatingbutton);
        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controller.getInstance().showCartActivity();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        if (menuItem.isChecked()) menuItem.setChecked(false);
//                        else menuItem.setChecked(true);
                        Fragment fragment = null;
                        Class fragmentClass;
                        FragmentManager fragmentManager;
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_main:
                                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent1);
                                finish();
                                drawerLayout.closeDrawers();
                                return true;
                            case R.id.item_navigation_drawer_advanced:
//                                fragmentClass = demotestfragment.class;
//                                try {
//                                    fragment = (Fragment) fragmentClass.newInstance();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                fragmentManager = getSupportFragmentManager();
//                                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//
//                                // Highlight the selected item, update the title, and close the drawer
//                                menuItem.setChecked(true);
//                                setTitle(menuItem.getTitle());
                                menuItem.setChecked(true);
                                Controller.getInstance().showSearchActivity();
                                drawerLayout.closeDrawers();

                                return true;
                            case R.id.item_navigation_drawer_page:
                                menuItem.setChecked(true);
                                Controller.getInstance().showBroadActivity();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                // return true;
                                return true;
                            case R.id.item_navigation_drawer_history:
                                menuItem.setChecked(true);
                                setTitle(menuItem.getTitle());
                                // Controller.getInstance().showHistoryActivity();
                                fragmentClass = fragment_history_tabmain.class;
                                try {
                                    fragment = (Fragment) fragmentClass.newInstance();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_order:
                                menuItem.setChecked(true);
                                Controller.getInstance().showOrderActivity();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);

                                Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                //   drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                //  return true;
                                return true;
                            case R.id.item_navigation_drawer_infomation:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                //  drawerLayout.closeDrawer(GravityCompat.START);
                                //  return true;
                                return true;
                        }
                        return true;
                    }
                });
    }
    //----------------------------------------------------------


    @Override
    protected void onResume() {
        super.onResume();
//        Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
//        startActivity(intent1);
//        finish();
    }

    //-----------------------------------------------------------
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.item_navigation_drawer_advanced:
                fragmentClass = demotestfragment.class;
                break;
//            case R.id.nav_second_fragment:
//                fragmentClass = SecondFragment.class;
//                break;
//            case R.id.nav_third_fragment:
//                fragmentClass = ThirdFragment.class;
//                break;
            default:
                fragmentClass = demotestfragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

}

