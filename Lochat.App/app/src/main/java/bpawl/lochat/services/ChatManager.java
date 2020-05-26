package bpawl.lochat.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.model.Message;
import bpawl.lochat.model.User;

@Singleton
public class ChatManager implements IChatManager, IChatConnection {

    private Collection<ChatRoom> _chatRooms;
    private ChatRoom _connectedChat;
    private User _user;

    @Inject
    public ChatManager() {
        // TODO: Use online service instead of the mocked data
        Message message = new Message();
        message.Id = "1";
        message.CreationTime = LocalDateTime.now();
        message.Text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

        ChatRoom first = new ChatRoom();
        first.CreationTime = LocalDateTime.now();
        first.Id = "1";
        first.IsDynamic = false;
        first.Latitude = 51.1000000;
        first.Longitude = 17.0333300;
        first.TerminationTime = LocalDateTime.now().plusHours(2);
        first.Messages = new ArrayList(Arrays.asList(message, message, message));
        first.Name = "First";

        ChatRoom second = new ChatRoom();
        second.CreationTime = LocalDateTime.now();
        second.Id = "2";
        second.IsDynamic = true;
        second.Latitude = 51.1000002;
        second.Longitude = 17.0333302;
        second.TerminationTime = LocalDateTime.now().plusHours(6);
        second.Messages = new ArrayList();
        second.Name = "Second";

        User user = new User();
        user.Username = "placeholder";
        user.Id = "0";
        user.Chatrooms = new ArrayList(Arrays.asList(first, second));
        _user = user;

        message.Author = user;
        message.Chatroom = first;

        first.Owner = user;
        second.Owner = user;

        _chatRooms = user.Chatrooms;
    }

    @Override
    public Collection<ChatRoom> getUserCreated(String userID) {
        if (userID == "0") {
            return _chatRooms;
        }
        return null;
    }

    @Override
    public boolean deleteChatRoom(String id) {
        ChatRoom toDelete = _getChatBy(id);
        if (toDelete != null) {
            _chatRooms.remove(toDelete);
            return true;
        }
        return false;
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
        _connectedChat.Messages.add(newMessage);
        return true;
    }

    @Override
    public ChatRoom getConnectedChat() {
        return _connectedChat;
    }

    private ChatRoom _getChatBy(String chatID) {
        for (ChatRoom chat : _chatRooms) {
            if (chat.Id == chatID) {
                return chat;
            }
        }
        return null;
    }
}