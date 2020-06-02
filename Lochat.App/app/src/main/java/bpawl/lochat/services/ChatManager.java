package bpawl.lochat.services;

import java.time.Instant;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import bpawl.lochat.api.IChatroomAPI;
import bpawl.lochat.api.IRetrofitProvider;
import bpawl.lochat.api.RequestModel.GetChatRoomByOwnerRequest;
import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ChatManager implements IChatManager {
    private IUserManager _userManager;
    private IChatroomAPI _chatroomAPI;

    @Inject
    public ChatManager(IRetrofitProvider provider, IUserManager userManager) {
        _userManager = userManager;
        _chatroomAPI = provider.getRetrofit().create(IChatroomAPI.class);
    }

    @Override
    public void getNearbyChatRooms(IRequestSuccessfulListener<List<ChatRoom>> callback, IRequestFailedListener onFailed) {

    }

    @Override
    public void getUserCreated(IRequestSuccessfulListener<List<ChatRoom>> callback, IRequestFailedListener onFailed) {
        if (_userManager.getAuthToken() == null) {
            onFailed.onRequestFailed();
        } else {
            GetChatRoomByOwnerRequest request = new GetChatRoomByOwnerRequest();
            request.ownerId = "b36aae58-1afb-4b51-881d-053fc137517d";//_userManager.getUser().Id;
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
    public void deleteChatRoom(ChatRoom toDelete, IRequestSuccessfulListener<Boolean> callback, IRequestFailedListener onFailed) {
        if (_userManager.getAuthToken() == null) {
            onFailed.onRequestFailed();
        } else {
            _chatroomAPI.deleteChatroom(_userManager.getAuthToken(), toDelete).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        callback.onRequestSuccessful(true);
                    }
                    else {
                        onFailed.onRequestFailed();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    onFailed.onRequestFailed();
                }
            });
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
}