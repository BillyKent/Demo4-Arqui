package com.software.miedo.demo4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.software.miedo.demo4.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mToolbar.setTitle("Restaurantes");
        setSupportActionBar(mToolbar);

        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.contentHomeFragment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_refresh:

                //Animation rotacion = AnimationUtils.loadAnimation(this, R.anim.rotation);
                //item.getActionView().startAnimation(rotacion);

                if (!homeFragment.isLoading()) {
                    homeFragment.forceLoad();
                }


                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
