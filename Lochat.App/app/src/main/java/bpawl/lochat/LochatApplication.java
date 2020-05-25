package bpawl.lochat;

import android.app.Application;

import javax.inject.Inject;

import bpawl.lochat.di.AndroidToolsModule;
import bpawl.lochat.di.AppComponent;
import bpawl.lochat.di.DaggerAppComponent;
import bpawl.lochat.services.IUserManager;

public class LochatApplication extends Application {
    public AppComponent appComponent;

    @Inject
    public IUserManager _userManager;

    @Override
    public void onCreate() {
        super.onCreate();
        _setupDependencyInjection();
    }

    private void _setupDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
                .androidToolsModule(new AndroidToolsModule(this))
                .build();
        appComponent.inject(this);
    }
}
