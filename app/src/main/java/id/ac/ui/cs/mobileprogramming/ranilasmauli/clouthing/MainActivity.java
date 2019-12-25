package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;

    private SidebarViewModel sidebarViewModel;

    private TimerFragment timerFragment;

    private final String TIMER_FRAGMENT_TAG = "timerfragmenttag";
    private final String ABOUT_ME_FRAGMENT_TAG = "aboutmefragmenttag";
    private final String LAUNDRY_FRAGMENT_TAG = "laundryfragmenttag";
    private final String LANGUAGE_FRAGMENT_TAG = "languagefragmenttag";
    private final String HOME_FRAGMENT_TAG = "homefragmenttag";

    private String ACTIVE_FRAGMENT_TAG = HOME_FRAGMENT_TAG; // default
    private Fragment ACTIVE_FRAGMENT = new HomeFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);


        // bagian header di-enabling
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sidebarViewModel = ViewModelProviders.of(this).get(SidebarViewModel.class);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        // default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ACTIVE_FRAGMENT).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        } else if (ACTIVE_FRAGMENT_TAG.equals(TIMER_FRAGMENT_TAG)) {
            timerFragment = (TimerFragment) getSupportFragmentManager().findFragmentByTag(TIMER_FRAGMENT_TAG);

        }

    }

//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        setFragment(menuItem);
        // close drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_home:
                ACTIVE_FRAGMENT = new HomeFragment();
                ACTIVE_FRAGMENT_TAG = HOME_FRAGMENT_TAG;

                break;
            case R.id.nav_laundry:
                ACTIVE_FRAGMENT = new LaundryFragment();
                ACTIVE_FRAGMENT_TAG = LAUNDRY_FRAGMENT_TAG;
                break;
            case R.id.nav_language:
                ACTIVE_FRAGMENT = new LanguageFragment();
                ACTIVE_FRAGMENT_TAG = LANGUAGE_FRAGMENT_TAG;
                break;
            case R.id.nav_about_me:
                ACTIVE_FRAGMENT = new AboutMeFragment();
                ACTIVE_FRAGMENT_TAG = ABOUT_ME_FRAGMENT_TAG;
                break;
            case R.id.nav_timer:
                ACTIVE_FRAGMENT = new TimerFragment();
                ACTIVE_FRAGMENT_TAG = TIMER_FRAGMENT_TAG;
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                // do logout
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ACTIVE_FRAGMENT, ACTIVE_FRAGMENT_TAG).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}
