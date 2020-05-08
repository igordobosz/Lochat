package bpawl.lochat.di;

import bpawl.lochat.services.ISampleService;
import bpawl.lochat.services.SampleService;
import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {

    @Provides
    public ISampleService provideSampleService() {
        return new SampleService();
    }
}
