package id.ac.ui.cs.mobileprogramming.ranilasmauli.clouthing;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mToggle;

    private SidebarViewModel sidebarViewModel;

    private ImageButton buttonCreateForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        //
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        // bagian header di-enabling
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sidebarViewModel = ViewModelProviders.of(this).get(SidebarViewModel.class);


        buttonCreateForm = findViewById(R.id.bt_create_form);
        buttonCreateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), FormActivity.class);
                MainActivity.this.startActivity(myIntent);
            };
        });
    }

//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
