package bpawl.lochat.services;

import java.util.List;

import javax.inject.Inject;

import bpawl.lochat.api.IAuthAPI;
import bpawl.lochat.api.IGoogleConnection;
import bpawl.lochat.api.IRetrofitProvider;
import bpawl.lochat.api.IUserAPI;
import bpawl.lochat.api.RequestModel.AuthRequest;
import bpawl.lochat.api.RequestModel.AuthResponse;
import bpawl.lochat.api.RequestModel.GetUserByEmailRequest;
import bpawl.lochat.model.User;
import bpawl.lochat.services.utils.IRequestFailedListener;
import bpawl.lochat.services.utils.IRequestSuccessfulListener;
import bpawl.lochat.services.utils.IUserManagerInitListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManager implements IUserManager, Callback<AuthResponse> {
    private User _user;
    private String _token;
    private IUserAPI _userAPI;
    private IAuthAPI _authAPI;
    private IGoogleConnection _googleConnection;
    private IUserManagerInitListener _callback;

    @Inject
    public UserManager(IRetrofitProvider provider, IGoogleConnection googleConnection) {
        _googleConnection = googleConnection;
        _authAPI = provider.getRetrofit().create(IAuthAPI.class);
        _userAPI = provider.getRetrofit().create(IUserAPI.class);
    }

    @Override
    public void init(IUserManagerInitListener callback) {
        if (_token == null) {
            _callback = callback;
            _tryAuthorize();
        }
    }

    @Override
    public User getUser() {
        return _user;
    }

    @Override
    public String getAuthToken() {
        return _token;
    }

    @Override
    public void signIn(IRequestSuccessfulListener<User> callback, IRequestFailedListener onFailed) {
        if (_token == null) {
            onFailed.onRequestFailed();
        } else {
            GetUserByEmailRequest request = new GetUserByEmailRequest();
            request.email = _googleConnection.getEmail();
            _userAPI.getUserByEmail(_token, request).enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.isSuccessful()) {
                        List<User> result = response.body();
                        if (result.size() > 0) {
                            _user = result.get(0);
                            callback.onRequestSuccessful(_user);
                            return;
                        }
                    }
                    onFailed.onRequestFailed();
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    onFailed.onRequestFailed();
                }
            });
        }
    }

    @Override
    public void changeUsername(String newUserName, IRequestSuccessfulListener<User> callback, IRequestFailedListener onFailed) {
        if (_token == null) {
            onFailed.onRequestFailed();
        } else {
            _user.Username = newUserName;
            _userAPI.updateUser(_token, _user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        callback.onRequestSuccessful(response.body());
                    } else {
                        onFailed.onRequestFailed();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    onFailed.onRequestFailed();
                }
            });
        }
    }

    private void _tryAuthorize() {
        AuthRequest request = new AuthRequest();
        request.idToken = _googleConnection.getToken();
        _authAPI.authorize(request).enqueue(this);
    }

    @Override
    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
        if (response.isSuccessful()) {
            _token = "Bearer " + response.body().accessToken;
            _callback.onUserManagerInit();
        } else {
            _tryAuthorize();
        }
    }

    @Override
    public void onFailure(Call<AuthResponse> call, Throwable t) {
        _tryAuthorize();
    }
}
