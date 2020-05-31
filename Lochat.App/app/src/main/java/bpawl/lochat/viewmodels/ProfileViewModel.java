package bpawl.lochat.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.services.IChatConnection;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;
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

    private MutableLiveData<Boolean> _isProcessing;
    private MutableLiveData<Collection<ChatRoom>> _chatRooms;

    @Override
    public void init() {
        _isProcessing = new MutableLiveData<Boolean>(true);
        _chatRooms = new MutableLiveData<Collection<ChatRoom>>(new ArrayList<ChatRoom>());
        chatManager.getUserCreated(new IRequestSuccessfulListener<List<ChatRoom>>() {
            @Override
            public void onRequestSuccessful(List<ChatRoom> result) {
                _chatRooms.setValue(result);
                _isProcessing.setValue(false);
                _onIsInit();
            }
        }, new IRequestFailedListener() {
            @Override
            public void onRequestFailed() { }
        });
    }

    public String getUsername() {
        return userManager.getUser().Username;
    }

    public LiveData<Boolean> getIsProcessing() {
        return _isProcessing;
    }

    public Collection<ChatRoom> getUserCreatedChatRooms() {
        return _chatRooms.getValue();
    }

    public void createChatRoom() {
        fragmentNavigation.navigateToFragment(ChatRoomCreation.class.getName());
    }

    public void changeUsername() {
        fragmentNavigation.navigateToFragment(UsernameChange.class.getName());
    }

    public boolean deleteChatRoom(String id) {
        return true;
    }

    public void selectChatRoom(String id) {
        if (chatConnection.connectToChat(id)) {
            fragmentNavigation.navigateToFragment(bpawl.lochat.ui.ChatRoom.class.getName());
        }
    }
}