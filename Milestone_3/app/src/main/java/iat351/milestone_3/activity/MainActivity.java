package iat351.milestone_3.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import iat351.milestone_3.R;
import iat351.milestone_3.fragment.CalendarFragment;
import iat351.milestone_3.fragment.CalendarFragment2;
import iat351.milestone_3.fragment.GoalFragment;
import iat351.milestone_3.fragment.SettingCategoriesFragment;
import iat351.milestone_3.fragment.SettingsFragment;
import iat351.milestone_3.fragment.SummaryFragment;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private FloatingActionButton fab1;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_CALENDAR = "Calendar";
    private static final String TAG_GOAL = "Goals";
    private static final String TAG_SUMMARY = "Summary";
    private static final String TAG_SETTINGS = "Settings";
    public static String CURRENT_TAG = TAG_CALENDAR;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                startActivity(new Intent(this,CreateEventActivity.class));
                startActivity(new Intent(view.getContext(), CreateEventActivity.class));
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateGoalActivity.class));
            }
        });

        // initializing navigation menu
        setUpNavigationView();
        navigationView.setItemIconTintList(null);

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_CALENDAR;

            loadHomeFragment();
        }
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            toggleFab();
            toggleFab1();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        toggleFab();
        toggleFab1();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_logout) {
//            navItemIndex = 4;
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // Calendar
                CalendarFragment calendarFragment = new CalendarFragment();
                return calendarFragment;
            case 1:
                // Goal
                GoalFragment goalFragment = new GoalFragment();
                return goalFragment;
            case 2:
                // Summary
                SummaryFragment summaryFragment = new SummaryFragment();
                return summaryFragment;
            case 3:
                // notifications fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;

            case 4:
                // only show events fragment
                SettingCategoriesFragment settingCategoriesFragment = new SettingCategoriesFragment();
                return settingCategoriesFragment;

            default:
                return new CalendarFragment();
        }
    }

    private void setToolbarTitle() {

        TextView title = (TextView)findViewById(R.id.toolbar_title);
        ImageView icon = (ImageView)findViewById(R.id.page_icon);

        Calendar c = Calendar.getInstance();
        String today_date = new SimpleDateFormat("MMM dd").format(c.getTime());

        switch (navItemIndex){
            case 0:
                title.setText(today_date);
                icon.setBackgroundResource(R.mipmap.cal_icon);
                break;
            case 1:
                title.setText("Goals");
                icon.setBackgroundResource(R.mipmap.goal_icon);
                break;
            case 2:
                title.setText("Summary");
                icon.setBackgroundResource(R.mipmap.sum_icon);
                break;
            case 3:
                title.setText("Settings");
                icon.setBackgroundResource(R.mipmap.set_icon);
                break;
            case 4:
                title.setText(today_date);
                icon.setBackgroundResource(R.mipmap.cal_icon);
                break;
        }

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_cal:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_CALENDAR;
                        break;
                    case R.id.nav_goal:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_GOAL;
                        break;
                    case R.id.nav_summary:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SUMMARY;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_CALENDAR;
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        return true;
    }

    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0) {
            fab.show();
        } else{
            fab.hide();
        }
    }

    private void toggleFab1() {
        if (navItemIndex == 1){
            fab1.show();
        } else{
            fab1.hide();
        }
    }

    //button click go back
    public void FinishedGoal(View view){
        Intent intent = new Intent(this, FinshGoalItemActivity.class);
        startActivity(intent);
    }

    public void SettingCategories(View view){
        SettingCategoriesFragment fragment = new SettingCategoriesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
