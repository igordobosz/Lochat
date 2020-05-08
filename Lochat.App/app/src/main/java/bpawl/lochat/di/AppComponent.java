package bpawl.lochat.di;

import bpawl.lochat.LochatApplication;
import bpawl.lochat.viewmodels.SigningViewModel;
import dagger.Component;

@Component(modules = {AndroidToolsModule.class, ServicesModule.class})
public interface AppComponent {
    void inject(LochatApplication application);
    void inject(SigningViewModel signingViewModel);
}