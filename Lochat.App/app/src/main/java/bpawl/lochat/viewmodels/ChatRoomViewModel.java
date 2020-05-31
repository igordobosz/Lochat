package bpawl.lochat.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bpawl.lochat.model.Message;
import bpawl.lochat.services.IChatConnection;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.IFragmentNavigation;

public class ChatRoomViewModel extends LochatViewModel {
    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IChatManager chatManager;

    @Inject
    public IChatConnection chatConnection;

    private MutableLiveData<List<Message>> _messages;
    private MutableLiveData<String> _currentMessage;
    private MediatorLiveData<Boolean> _isMessageValid;

    @Override
    public void init() {
        _messages = new MutableLiveData<List<Message>>(new ArrayList<Message>());
        _currentMessage = new MutableLiveData<String>("");
        _isMessageValid = new MediatorLiveData<Boolean>();
        _isMessageValid.addSource(_currentMessage, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                _isMessageValid.setValue(_currentMessage.getValue().trim().length() > 0);
            }
        });
        super.init();
    }

    public String getChatName() {
        return chatConnection.getConnectedChat().Name;
    }

    public String getRemainingTime() {
        return chatConnection.getConnectedChat().getRemainingTimeString();
    }

    public void deleteChatRoom() {
        chatManager.deleteChatRoom(chatConnection.getConnectedChat().Id);
        if (true) {
            chatConnection.disconnectFromChat();
            fragmentNavigation.back();
        }
    }

    public LiveData<List<Message>> getMessages() {
        return _messages;
    }

    public MutableLiveData<String> getCurrentMessage() {
        return _currentMessage;
    }

    public LiveData<Boolean> getIsMessageValid() {
        return _isMessageValid;
    }

    public void sendMessage() {
        if (_currentMessage.getValue().trim().length() > 0) {
            chatConnection.sendMessage(_currentMessage.getValue());
            _currentMessage.setValue("");
            _update();
        }
    }

    private void _update() {
        _messages.setValue(new ArrayList<Message>());
    }
}
