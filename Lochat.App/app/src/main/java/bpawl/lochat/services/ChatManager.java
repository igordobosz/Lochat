package bpawl.lochat.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.model.Message;
import bpawl.lochat.model.User;

public class ChatManager implements IChatManager {

    private Collection<ChatRoom> _chatRooms;

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
        first.Messages = Arrays.asList(message, message, message);
        first.Name = "First";

        ChatRoom second = new ChatRoom();
        second.CreationTime = LocalDateTime.now();
        second.Id = "2";
        second.IsDynamic = true;
        second.Latitude = 51.1000002;
        second.Longitude = 17.0333302;
        second.TerminationTime = LocalDateTime.now().plusHours(6);
        second.Messages = Arrays.asList();
        second.Name = "Second";

        User user = new User();
        user.Username = "placeholder";
        user.Id = "0";
        user.Chatrooms = new ArrayList(Arrays.asList(first, second));

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
    public void deleteChatRoom(String id) {
        ChatRoom toDelete = null;
        for (ChatRoom chat : _chatRooms) {
            if (chat.Id == id) {
                toDelete = chat;
                break;
            }
        }
        if (toDelete != null) {
            _chatRooms.remove(toDelete);
        }
    }
}
