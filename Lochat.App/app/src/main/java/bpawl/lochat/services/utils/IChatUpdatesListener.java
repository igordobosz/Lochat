package bpawl.lochat.services.utils;

import java.util.Collection;
import java.util.List;

import bpawl.lochat.model.Message;

public interface IChatUpdatesListener {
    void onNewMessagesReceived(Collection<Message> newMessages);
    List<Message> getLocalMessages();
}