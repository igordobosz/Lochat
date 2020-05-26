package bpawl.lochat.di;

import javax.inject.Singleton;

import bpawl.lochat.LochatActivity;
import bpawl.lochat.LochatApplication;
import bpawl.lochat.viewmodels.ChatRoomViewModel;
import bpawl.lochat.viewmodels.ProfileViewModel;
import bpawl.lochat.viewmodels.SigningViewModel;
import bpawl.lochat.viewmodels.UsernameChangeViewModel;
import dagger.Component;

@Singleton
@Component(modules = {AndroidToolsModule.class, ServicesModule.class})
public interface AppComponent {
    void inject(LochatApplication application);
    void inject(LochatActivity lochatActivity);
    void inject(UsernameChangeViewModel usernameChangeViewModel);
    void inject(ProfileViewModel profileViewModel);
    void inject(ChatRoomViewModel chatRoomViewModel);
    void inject(SigningViewModel signingViewModel);
}