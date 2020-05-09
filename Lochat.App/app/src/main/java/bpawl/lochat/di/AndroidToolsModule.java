package bpawl.lochat.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;

@Module
public class AndroidToolsModule {
    private Application _app;

    private static final String SP_KEY = "lochat-user-data";

    public AndroidToolsModule(Application app) {
        _app = app;
    }

    @Provides
    public SharedPreferences provideSharedPreferences() {
        return _app.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
    }
}
