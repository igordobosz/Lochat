package bpawl.lochat.di;

import android.app.Application;

import bpawl.lochat.Services.ISampleService;
import bpawl.lochat.Services.SampleService;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {

    @Binds
    public abstract ISampleService bindSampleService(SampleService service);
}
