package bpawl.lochat;

import bpawl.lochat.ui.Signing;
import bpawl.lochat.viewmodels.SigningViewModel;
import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(Signing signing);
    void inject(SigningViewModel signingViewModel);
}
