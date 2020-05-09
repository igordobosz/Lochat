package bpawl.lochat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.ui.Signing;

public abstract class LochatActivity extends AppCompatActivity {
    protected LochatApplication _lochatApp;

    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _lochatApp = (LochatApplication) getApplication();
        _lochatApp.appComponent.inject(this);

        _initFragmentNavigation();
        if (savedInstanceState == null) {
            fragmentNavigation.navigateToFragment(Signing.class.getName());
        }
    }

    @Override
    public void onBackPressed(){
        if(!fragmentNavigation.back()) {
            super.onBackPressed();
        }
    }

    private void _initFragmentNavigation(){
        fragmentNavigation.setContainer(R.id.container);
        fragmentNavigation.setFragmentManager(getSupportFragmentManager());
    }
}
