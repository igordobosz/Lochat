package bpawl.lochat.di;

import java.util.Arrays;

import javax.inject.Singleton;
import bpawl.lochat.services.FragmentNavigation;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.ui.ChatMap;
import bpawl.lochat.ui.ChatRoomCreation;
import bpawl.lochat.ui.Profile;
import bpawl.lochat.ui.Signing;
import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {

    @Provides
    @Singleton
    public IFragmentNavigation provideSampleService() {
        return new FragmentNavigation(Arrays.asList(ChatMap.newInstance(), ChatMap.newInstance(),
                ChatMap.newInstance(), ChatRoomCreation.newInstance(),
                Profile.newInstance(), Signing.newInstance()));
    }
}
