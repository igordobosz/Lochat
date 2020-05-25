package bpawl.lochat.services;

import bpawl.lochat.model.User;

public interface IUserManager {
    User getUser();
    void signIn();
    void changeUsername(String newUserName);
}