package bpawl.lochat.services;

import java.util.Collection;

import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.model.Message;
import bpawl.lochat.services.utils.IChatUpdatesListener;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;

public interface IChatConnection {
    void connectToChat(ChatRoom toConnect);
    ChatRoom getConnectedChat();
    void disconnectFromChat();
    void getMessages(IRequestSuccessfulListener<Collection<Message>> callback, IRequestFailedListener onFailed);
    void sendMessage(Message message, IRequestSuccessfulListener<Message> callback, IRequestFailedListener onFailed);
    void getNewMessagesUpdates(IChatUpdatesListener listener);
}