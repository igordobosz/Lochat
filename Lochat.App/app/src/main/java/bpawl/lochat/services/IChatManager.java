package bpawl.lochat.services;

import java.util.Collection;

import bpawl.lochat.model.ChatRoom;

public interface IChatManager {
    Collection<ChatRoom> getUserCreated(String userID);
    void deleteChatRoom(String id);
}
