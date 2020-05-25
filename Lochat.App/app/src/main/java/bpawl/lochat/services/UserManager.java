package bpawl.lochat.services;

import android.content.SharedPreferences;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.inject.Inject;

import bpawl.lochat.model.ChatRoom;
import bpawl.lochat.model.Message;
import bpawl.lochat.model.User;

public class UserManager implements IUserManager {
    private static final String USERNAME_KEY = "user-name";

    private User _user;

    // TODO: Use online data instead of shared preferences
    private SharedPreferences _sp;

    @Inject
    public UserManager(SharedPreferences sp) {
        _sp = sp;
    }

    @Override
    public User getUser() {
        if (_user == null) {
            signIn();
        }
        return _user;
    }

    @Override
    public void signIn() {
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

        _user = new User();
        _user.Username = _sp.getString(USERNAME_KEY, "");
        _user.Id = "0";
        _user.Chatrooms = Arrays.asList(first, second);

        message.Author = _user;
        message.Chatroom = first;

        first.Owner = _user;
        second.Owner = _user;
    }

    @Override
    public void changeUsername(String newUserName) {
        SharedPreferences.Editor editor = _sp.edit();
        editor.putString(USERNAME_KEY, newUserName);
        editor.commit();
    }
}
