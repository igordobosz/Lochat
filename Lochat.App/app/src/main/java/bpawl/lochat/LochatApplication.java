package bpawl.lochat;

import android.app.Application;
import bpawl.lochat.di.AndroidToolsModule;
import bpawl.lochat.di.AppComponent;
import bpawl.lochat.di.DaggerAppComponent;

public class LochatApplication extends Application {
    public AppComponent appComponent;

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
        _injectServices();
    }

    private void _injectServices() {

    }
}
