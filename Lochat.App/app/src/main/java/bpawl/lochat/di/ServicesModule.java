package bpawl.lochat.di;

import java.util.Arrays;
import javax.inject.Singleton;
import bpawl.lochat.api.LochatAPIRetrofitProvider;
import bpawl.lochat.api.GoogleConnection;
import bpawl.lochat.api.IGoogleConnection;
import bpawl.lochat.api.IRetrofitProvider;
import bpawl.lochat.services.ChatConnection;
import bpawl.lochat.services.ChatManager;
import bpawl.lochat.services.FragmentNavigation;
import bpawl.lochat.services.IChatConnection;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.ILocationService;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.services.LocationService;
import bpawl.lochat.services.UserManager;
import bpawl.lochat.ui.ChatMap;
import bpawl.lochat.ui.ChatRoomCreation;
import bpawl.lochat.ui.Profile;
import bpawl.lochat.ui.Signing;
import bpawl.lochat.ui.UsernameChange;
import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    @Singleton
    public IFragmentNavigation provideFragmentNavigation() {
        return new FragmentNavigation(Arrays.asList(Signing.newInstance(), ChatMap.newInstance(),
                bpawl.lochat.ui.ChatRoom.newInstance(), ChatRoomCreation.newInstance(),
                Profile.newInstance(), UsernameChange.newInstance()));
    }

    @Provides
    @Singleton
    public IRetrofitProvider provideRetrofit() { return new LochatAPIRetrofitProvider(); }

    @Provides
    @Singleton
    public IGoogleConnection provideGoogleConnection() { return new GoogleConnection(); }

    @Provides
    @Singleton
    public IUserManager provideUserManager(IRetrofitProvider provider, IGoogleConnection googleConnection) { return new UserManager(provider, googleConnection); }

    @Provides
    @Singleton
    public IChatManager provideChatManager(IRetrofitProvider provider, IUserManager userManager, ILocationService locationService) { return new ChatManager(provider, userManager, locationService); }

    @Provides
    @Singleton
    public IChatConnection provideChatConnection(IRetrofitProvider provider, IUserManager userManager) { return new ChatConnection(provider, userManager); }

    @Provides
    @Singleton
    public ILocationService provideLocationService() { return new LocationService(); }
}