package bpawl.lochat.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.services.IChatConnection;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.ILocationService;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.services.utils.IIsLocationServiceLoadedListener;
import bpawl.lochat.services.utils.ILocationUpdatedListener;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;
import bpawl.lochat.ui.ChatRoomCreation;

public class ChatMapViewModel extends LochatViewModel implements IRequestSuccessfulListener<List<ChatRoom>>, IRequestFailedListener, ILocationUpdatedListener, IIsLocationServiceLoadedListener {
    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IUserManager userManager;

    @Inject
    public IChatManager chatManager;

    @Inject
    public ILocationService locationService;

    @Inject
    public IChatConnection chatConnection;

    private MutableLiveData<Boolean> _isProcessing;
    private MutableLiveData<List<ChatRoom>> _chatRooms;
    private MutableLiveData<LatLng> _position;

    @Override
    public void init() {
        _isProcessing = new MutableLiveData<Boolean>(true);
        _position = new MutableLiveData<LatLng>(new LatLng(51.9189, 19.1344));
        _chatRooms = new MutableLiveData<List<ChatRoom>>(new ArrayList<ChatRoom>());
        if (locationService.isLoaded()) {
            _position.setValue(new LatLng(locationService.getLatitude(), locationService.getLongitude()));
            chatManager.getNearbyChatRooms(this, this);
            locationService.getLocationUpdates(this);
        } else {
            locationService.listenForLoaded(this);
        }
        super.init();
    }

    public LiveData<Boolean> getIsProcessing() {
        return _isProcessing;
    }

    public LiveData<List<ChatRoom>> getChatRooms() {
        return _chatRooms;
    }

    public LiveData<LatLng> getPosition() {
        return _position;
    }

    public void createChatRoom() {
        fragmentNavigation.navigateToFragment(ChatRoomCreation.class.getName());
    }

    public void connectToChatRoom(ChatRoom chat) {
        chatConnection.connectToChat(chat);
        fragmentNavigation.navigateToFragment(bpawl.lochat.ui.ChatRoom.class.getName());
    }

    @Override
    public void onRequestFailed() {
    }

    @Override
    public void onRequestSuccessful(List<ChatRoom> result) {
        _chatRooms.setValue(result);
        if (_isProcessing.getValue()) {
            _isProcessing.setValue(false);
        }
    }

    @Override
    public void onLocationServiceLoaded() {
        _position.setValue(new LatLng(locationService.getLatitude(), locationService.getLongitude()));
    }

    @Override
    public void onLocationUpdated() {
        _position.setValue(new LatLng(locationService.getLatitude(), locationService.getLongitude()));
        chatManager.getNearbyChatRooms(this, this);
    }
}
