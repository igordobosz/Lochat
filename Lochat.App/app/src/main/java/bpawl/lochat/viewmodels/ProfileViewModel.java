package bpawl.lochat.viewmodels;

import java.util.Collection;

import javax.inject.Inject;

import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.services.IChatConnection;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.ui.ChatRoomCreation;
import bpawl.lochat.ui.UsernameChange;

public class ProfileViewModel extends LochatViewModel {
    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IUserManager userManager;

    @Inject
    public IChatManager chatManager;

    @Inject
    public IChatConnection chatConnection;

    public String getUsername() {
        return userManager.getUser().Username;
    }

    public Collection<ChatRoom> getUserCreatedChatRooms() {
        return chatManager.getUserCreated(userManager.getUser().Id);
    }

    public void createChatRoom() {
        fragmentNavigation.navigateToFragment(ChatRoomCreation.class.getName());
    }

    public void changeUsername() {
        fragmentNavigation.navigateToFragment(UsernameChange.class.getName());
    }

    public boolean deleteChatRoom(String id) {
        return chatManager.deleteChatRoom(id);
    }

    public void selectChatRoom(String id) {
        if (chatConnection.connectToChat(id)) {
            fragmentNavigation.navigateToFragment(bpawl.lochat.ui.ChatRoom.class.getName());
        }
    }
}