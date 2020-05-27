package bpawl.lochat.services;

import java.util.Collection;

import bpawl.lochat.model.ChatRoom;

public interface IChatManager {
    Collection<ChatRoom> getUserCreated(String userID);
    boolean deleteChatRoom(String id);
    boolean createChatRoom(String name, int duration, int range);
}
