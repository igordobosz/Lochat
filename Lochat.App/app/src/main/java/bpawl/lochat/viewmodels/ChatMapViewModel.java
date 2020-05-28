package bpawl.lochat.viewmodels;

import javax.inject.Inject;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.ui.ChatRoomCreation;

public class ChatMapViewModel extends LochatViewModel {
    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IUserManager userManager;

    @Inject
    public IChatManager chatManager;

    public void createChatRoom() {
        fragmentNavigation.navigateToFragment(ChatRoomCreation.class.getName());
    }
}
