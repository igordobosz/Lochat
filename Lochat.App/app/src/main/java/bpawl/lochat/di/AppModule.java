package bpawl.lochat;

import android.app.Application;

import bpawl.lochat.Services.ISampleService;
import bpawl.lochat.Services.SampleService;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public ISampleService provideSampleService() {
        return new SampleService();
    }
}
