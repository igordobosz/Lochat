package bpawl.lochat.di;

import javax.inject.Singleton;

import bpawl.lochat.LochatActivity;
import bpawl.lochat.LochatApplication;
import bpawl.lochat.viewmodels.SigningViewModel;
import dagger.Component;

@Singleton
@Component(modules = {AndroidToolsModule.class, ServicesModule.class})
public interface AppComponent {
    void inject(LochatApplication application);
    void inject(LochatActivity lochatActivity);
    void inject(SigningViewModel signingViewModel);
}