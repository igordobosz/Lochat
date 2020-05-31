package bpawl.lochat.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bpawl.lochat.api.IChatroomAPI;
import bpawl.lochat.api.IRetrofitProvider;
import bpawl.lochat.api.RequestModel.GetChatRoomByOwnerRequest;
import bpawl.lochat.api.RequestModel.GetUserByEmailRequest;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.model.Message;
import bpawl.lochat.model.User;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ChatManager implements IChatManager, IChatConnection {
    private IUserManager _userManager;
    private IChatroomAPI _chatroomAPI;
    private Collection<ChatRoom> _chatRooms;
    private ChatRoom _connectedChat;
    private User _user;

    @Inject
    public ChatManager(IRetrofitProvider provider, IUserManager userManager) {
        _userManager = userManager;
        _chatroomAPI = provider.getRetrofit().create(IChatroomAPI.class);
    }

    @Override
    public void getUserCreated(IRequestSuccessfulListener<List<ChatRoom>> callback, IRequestFailedListener onFailed) {
        if (_userManager.getAuthToken() == null) {
            onFailed.onRequestFailed();
        } else {
            GetChatRoomByOwnerRequest request = new GetChatRoomByOwnerRequest();
            request.ownerId = _userManager.getUser().Id;
            _chatroomAPI.getChatRoomsByOwner(_userManager.getAuthToken(), request).enqueue(new Callback<List<ChatRoom>>() {
                @Override
                public void onResponse(Call<List<ChatRoom>> call, Response<List<ChatRoom>> response) {
                    if (response.isSuccessful()) {
                        callback.onRequestSuccessful(response.body());
                    }
                    else {
                        onFailed.onRequestFailed();
                    }
                }

                @Override
                public void onFailure(Call<List<ChatRoom>> call, Throwable t) {
                    onFailed.onRequestFailed();
                }
            });
        }
    }

    @Override
    public void deleteChatRoom(String id) {
        ChatRoom toDelete = _getChatBy(id);
        if (toDelete != null) {
            _chatRooms.remove(toDelete);
        }
    }

    @Override
    public void createChatRoom(String name, int duration, int range, IRequestSuccessfulListener<ChatRoom> callback, IRequestFailedListener onFailed) {
        if (_userManager.getAuthToken() == null) {
            onFailed.onRequestFailed();
        } else {
            ChatRoom toCreate = new ChatRoom();
            toCreate.OwnerId = _userManager.getUser().Id;
            toCreate.Name = name;
            toCreate.CreationTime = Instant.now();
            toCreate.TerminationTime = toCreate.CreationTime.plusSeconds(duration * 3600);
            toCreate.Latitude = 51.1000000;
            toCreate.Longitude = 17.0333300;
            _chatroomAPI.createChatroom(_userManager.getAuthToken(), toCreate).enqueue(new Callback<ChatRoom>() {
                @Override
                public void onResponse(Call<ChatRoom> call, Response<ChatRoom> response) {
                    if(response.isSuccessful()) {
                        callback.onRequestSuccessful(response.body());
                    }
                    else  {
                        onFailed.onRequestFailed();
                    }
                }

                @Override
                public void onFailure(Call<ChatRoom> call, Throwable t) {
                    onFailed.onRequestFailed();
                }
            });
        }
    }

    @Override
    public boolean connectToChat(String chatID) {
        ChatRoom toConnect = _getChatBy(chatID);
        if (toConnect != null) {
            _connectedChat = toConnect;
            return true;
        }
        return false;
    }

    @Override
    public ChatRoom getConnectedChat() {
        return _connectedChat;
    }

    @Override
    public boolean disconnectFromChat() {
        if (_connectedChat != null) {
            _connectedChat = null;
            return true;
        }
        return false;
    }

    public boolean sendMessage(String text) {
        Message newMessage = new Message();
        newMessage.Text = text;
        newMessage.Id = "100";
        newMessage.Author = _user;
        newMessage.AuthorId = _user.Id;
        newMessage.Chatroom = _connectedChat;
        newMessage.ChatroomId = _connectedChat.Id;
        newMessage.CreationTime = LocalDateTime.now();
        return true;
    }

    private ChatRoom _getChatBy(String chatID) { return null; }
}