package bpawl.lochat.viewmodels;

import java.util.Collection;

import javax.inject.Inject;

import bpawl.lochat.model.ChatRoom;
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

    public String getUsername() {
        return userManager.getUser().Username;
    }

    public Collection<ChatRoom> getUserCreatedChatRooms() { return chatManager.getUserCreated(userManager.getUser().Id); }

    public void createChatRoom() {
        fragmentNavigation.navigateToFragment(ChatRoomCreation.class.getName());
    }

    public void changeUsername() {
        fragmentNavigation.navigateToFragment(UsernameChange.class.getName());
    }

    public void deleteChatRoom(String id) {
        chatManager.deleteChatRoom(id);
    }
}