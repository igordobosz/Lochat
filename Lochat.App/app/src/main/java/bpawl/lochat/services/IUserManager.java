package bpawl.lochat.services;

import bpawl.lochat.model.User;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;
import bpawl.lochat.services.utils.IUserManagerInitListener;

public interface IUserManager {
    void init(IUserManagerInitListener callback);
    User getUser();
    String getAuthToken();
    void signIn(IRequestSuccessfulListener<User> callback, IRequestFailedListener onFailed);
    void changeUsername(String newUserName, IRequestSuccessfulListener<User> callback, IRequestFailedListener onFailed);
}