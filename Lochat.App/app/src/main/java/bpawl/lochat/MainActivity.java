package bpawl.lochat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import bpawl.lochat.ui.Profile;

public class MainActivity extends LochatActivity {
    private Toolbar _toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        _toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(_toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                fragmentNavigation.navigateToFragment(Profile.class.getName());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void hideToolbar() { _toolbar.setVisibility(View.GONE); }
    public void showToolbar() { _toolbar.setVisibility(View.VISIBLE); }
}
