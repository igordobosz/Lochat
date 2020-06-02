package bpawl.lochat.services;

import java.util.List;

import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;

public interface IChatManager {
    void getNearbyChatRooms(IRequestSuccessfulListener<List<ChatRoom>> callback, IRequestFailedListener onFailed);
    void getUserCreated(IRequestSuccessfulListener<List<ChatRoom>> callback, IRequestFailedListener onFailed);
    void deleteChatRoom(ChatRoom toDelete, IRequestSuccessfulListener<Boolean> callback, IRequestFailedListener onFailed);
    void createChatRoom(String name, int duration, int range, IRequestSuccessfulListener<ChatRoom> callback, IRequestFailedListener onFailed);
}
