package bpawl.lochat.services;

import bpawl.lochat.model.ChatRoom;

public interface IChatConnection {
    boolean connectToChat(String chatID);
    boolean disconnectFromChat();
    boolean sendMessage(String text);
    ChatRoom getConnectedChat();
}
