package bpawl.lochat.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import bpawl.lochat.model.Message;
import bpawl.lochat.services.IChatConnection;
import bpawl.lochat.services.IChatManager;
import bpawl.lochat.services.IFragmentNavigation;
import bpawl.lochat.services.IUserManager;
import bpawl.lochat.services.utils.IChatUpdatesListener;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;

public class ChatRoomViewModel extends LochatViewModel implements IChatUpdatesListener, IRequestFailedListener, IRequestSuccessfulListener<Message> {
    @Inject
    public IFragmentNavigation fragmentNavigation;

    @Inject
    public IChatManager chatManager;

    @Inject
    public IChatConnection chatConnection;

    @Inject
    public IUserManager userManager;

    private MutableLiveData<List<Message>> _messages;
    private MutableLiveData<String> _currentMessage;
    private MutableLiveData<Boolean> _isProcessing;
    private MutableLiveData<Boolean> _isDeleting;
    private MutableLiveData<Boolean> _canDelete;
    private MediatorLiveData<Boolean> _isMessageValid;

    @Override
    public void init() {
        _messages = new MutableLiveData<List<Message>>(new ArrayList<Message>());
        _currentMessage = new MutableLiveData<String>("");
        _isProcessing = new MutableLiveData<Boolean>(true);
        _isDeleting = new MutableLiveData<Boolean>(false);
        _canDelete = new MutableLiveData<Boolean>(chatConnection.getConnectedChat().OwnerId.equals(userManager.getUser().Id));
        _isMessageValid = new MediatorLiveData<Boolean>();
        _isMessageValid.addSource(_currentMessage, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                _isMessageValid.setValue(_currentMessage.getValue().trim().length() > 0);
            }
        });

        IChatUpdatesListener that = this;
        chatConnection.getMessages(new IRequestSuccessfulListener<Collection<Message>>() {
            @Override
            public void onRequestSuccessful(Collection<Message> result) {
                _messages.setValue(result
                        .stream()
                        .sorted((first, second) -> first.CreationTime.compareTo(second.CreationTime)).collect(Collectors.toList()));
                _isProcessing.setValue(false);
                chatConnection.getNewMessagesUpdates(that);
            }
        }, new IRequestFailedListener() {
            @Override
            public void onRequestFailed() {
            }
        });

        super.init();
    }

    public LiveData<List<Message>> getMessages() {
        return _messages;
    }

    public MutableLiveData<String> getCurrentMessage() {
        return _currentMessage;
    }

    public LiveData<Boolean> getIsProcessing() {
        return _isProcessing;
    }

    public LiveData<Boolean> getIsDeleting() {
        return _isDeleting;
    }

    public LiveData<Boolean> getCanDelete() {
        return _canDelete;
    }

    public LiveData<Boolean> getIsMessageValid() {
        return _isMessageValid;
    }

    public String getChatName() {
        return chatConnection.getConnectedChat().Name;
    }

    public String getRemainingTime() {
        return chatConnection.getConnectedChat().getRemainingTimeString();
    }

    public void deleteChatRoom() {
        if (chatConnection.getConnectedChat() != null) {
            _isDeleting.setValue(true);
            chatManager.deleteChatRoom(chatConnection.getConnectedChat(),
                    new IRequestSuccessfulListener<Boolean>() {
                        @Override
                        public void onRequestSuccessful(Boolean result) {
                            chatConnection.disconnectFromChat();
                            _isDeleting.setValue(false);
                            fragmentNavigation.back();
                        }
                    }, new IRequestFailedListener() {
                        @Override
                        public void onRequestFailed() {
                            _isDeleting.setValue(false);
                        }
                    });
        }
    }

    public void sendMessage() {
        if (_currentMessage.getValue().trim().length() > 0) {
            Message newMessage = new Message();
            newMessage.AuthorId = userManager.getUser().Id;
            newMessage.AuthorName = userManager.getUser().Username;
            newMessage.ChatroomId = chatConnection.getConnectedChat().Id;
            newMessage.CreationTime = Instant.now();
            newMessage.Text = _currentMessage.getValue();

            chatConnection.sendMessage(newMessage, this, this);

            _currentMessage.setValue("");
            List<Message> currentMessages = _messages.getValue();
            currentMessages.add(newMessage);
            _messages.setValue(currentMessages);
        }
    }

    @Override
    public List<Message> getLocalMessages() {
        return _messages.getValue();
    }

    @Override
    public void onNewMessagesReceived(Collection<Message> newMessages) {
        List<Message> currentMessages = _messages.getValue();
        currentMessages.addAll(newMessages);
        _messages.setValue(currentMessages);
    }

    @Override
    public void onRequestFailed() {
    }

    @Override
    public void onRequestSuccessful(Message result) {
    }
}
