package bpawl.lochat.di;

import android.content.SharedPreferences;

import java.util.Arrays;
import javax.inject.Singleton;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.model.Message;
import bpawl.lochat.model.User;
import bpawl.lochat.services.ChatManager;
import bpawl.lochat.services.ChatRoomCrudService;
import bpawl.lochat.services.FragmentNavigation;
import bpawl.lochat.services.IChatConnection;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.ICrudService;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.services.MessageCrudService;
import bpawl.lochat.services.UserCrudService;
import bpawl.lochat.services.UserManager;
import bpawl.lochat.ui.ChatMap;
import bpawl.lochat.ui.ChatRoomCreation;
import bpawl.lochat.ui.Profile;
import bpawl.lochat.ui.Signing;
import bpawl.lochat.ui.UsernameChange;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = ServicesModule.BindsModule.class)
public class ServicesModule {

    @Provides
    @Singleton
    public IFragmentNavigation provideFragmentNavigation() {
        return new FragmentNavigation(Arrays.asList(Signing.newInstance(), ChatMap.newInstance(),
                bpawl.lochat.ui.ChatRoom.newInstance(), ChatRoomCreation.newInstance(),
                Profile.newInstance(), UsernameChange.newInstance()));
    }

    @Provides
    public ICrudService<User> provideUserCrudService() {
        return new UserCrudService();
    }

    @Provides
    public ICrudService<Message> provideMessageCrudService() {
        return new MessageCrudService();
    }

    @Provides
    public ICrudService<ChatRoom> provideChatRoomCrudService() {
        return new ChatRoomCrudService();
    }

    @Provides
    @Singleton
    public IUserManager provideUserManager(SharedPreferences sp) { return new UserManager(sp); }

    // interface with @Binds
    @Module
    public interface BindsModule {
        @Binds
        IChatManager provideChatManager(ChatManager chatManager);

        @Binds
        IChatConnection provideChatConnection(ChatManager chatManager);
    }
}