package bpawl.lochat.services;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import bpawl.lochat.api.IMessageAPI;
import bpawl.lochat.api.IRetrofitProvider;
import bpawl.lochat.api.RequestModel.GetMessagesForChatRoomRequest;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.model.Message;
import bpawl.lochat.services.utils.IChatUpdatesListener;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatConnection implements IChatConnection, IRequestSuccessfulListener<Collection<Message>>, IRequestFailedListener {
    private IUserManager _userManager;
    private IMessageAPI _messageAPI;

    private ChatRoom _connectedChat;
    private IChatUpdatesListener _listener;

    public ChatConnection(IRetrofitProvider provider, IUserManager userManager) {
        _userManager = userManager;
        _messageAPI = provider.getRetrofit().create(IMessageAPI.class);
    }

    @Override
    public void connectToChat(ChatRoom toConnect) {
        _connectedChat = toConnect;
    }

    @Override
    public ChatRoom getConnectedChat() {
        return _connectedChat;
    }

    @Override
    public void disconnectFromChat() {
        _listener = null;
        _connectedChat = null;
    }

    @Override
    public void getMessages(IRequestSuccessfulListener<Collection<Message>> callback, IRequestFailedListener onFailed) {
        if (_connectedChat == null) {
            onFailed.onRequestFailed();
        } else {
            GetMessagesForChatRoomRequest request = new GetMessagesForChatRoomRequest();
            request.chatroomId = _connectedChat.Id;
            _messageAPI.getMessagesForChatRoom(_userManager.getAuthToken(), request).enqueue(new Callback<List<Message>>() {
                @Override
                public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                    if (response.isSuccessful()) {
                        callback.onRequestSuccessful(response.body());
                    }
                    else {
                        onFailed.onRequestFailed();
                    }
                }

                @Override
                public void onFailure(Call<List<Message>> call, Throwable t) {
                    onFailed.onRequestFailed();
                }
            });
        }
    }

    @Override
    public void sendMessage(Message message, IRequestSuccessfulListener<Message> callback, IRequestFailedListener onFailed) {
        if (_connectedChat == null) {
            onFailed.onRequestFailed();
        } else {
            _messageAPI.createMessage(_userManager.getAuthToken(), message).enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if (response.isSuccessful()) {
                        callback.onRequestSuccessful(response.body());
                    }
                    else {
                        onFailed.onRequestFailed();
                    }
                }

                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    onFailed.onRequestFailed();
                }
            });
        }
    }

    @Override
    public void getNewMessagesUpdates(IChatUpdatesListener listener) {
        if (_connectedChat != null) {
            _listener = listener;
            IRequestSuccessfulListener<Collection<Message>> callback = this;
            IRequestFailedListener onFailed = this;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getMessages(callback, onFailed);
                }
            }).start();
        }
    }

    @Override
    public void onRequestFailed() {
        if (_listener != null) {
            IRequestSuccessfulListener<Collection<Message>> callback = this;
            IRequestFailedListener onFailed = this;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) { e.printStackTrace(); }
                    getMessages(callback, onFailed);
                }
            }).start();
        }
    }

    @Override
    public void onRequestSuccessful(Collection<Message> result) {
        if (_listener != null) {
            List<Message> newMessages = _getNewMessages(result);
            if(newMessages != null && newMessages.size() > 0) {
                _listener.onNewMessagesReceived(newMessages);
            }
            IRequestSuccessfulListener<Collection<Message>> callback = this;
            IRequestFailedListener onFailed = this;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) { e.printStackTrace(); }
                    getMessages(callback, onFailed);
                }
            }).start();
        }
    }

    private List<Message> _getNewMessages(Collection<Message> onlineMessages) {
        Instant lastUpdate = _getLastUpdatedMessageTime(_listener.getLocalMessages());
        return onlineMessages
                .stream()
                .filter((message) -> !message.AuthorId.equals(_userManager.getUser().Id)
                        && message.CreationTime.compareTo(lastUpdate) > 0)
                .sorted((first, second) -> first.CreationTime.compareTo(second.CreationTime))
                .collect(Collectors.toList());
    }

    private Instant _getLastUpdatedMessageTime(Collection<Message> localMessages) {
        Instant lastUpdate = Instant.MIN;
        Optional<Message> last = localMessages
                .stream()
                .filter(message -> !message.AuthorId.equals(_userManager.getUser().Id))
                .max((first, second) -> first.CreationTime.compareTo(second.CreationTime));
        if (last.isPresent()) {
            lastUpdate = last.get().CreationTime;
        }
        return lastUpdate;
    }

    private void wait(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException ex) { }
    }
}