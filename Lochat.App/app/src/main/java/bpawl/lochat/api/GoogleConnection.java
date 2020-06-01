package bpawl.lochat.api;

public class GoogleConnection implements IGoogleConnection {
    private String _token;
    private String _email;

    @Override
    public String getToken() {
        return _token;
    }

    @Override
    public void setToken(String token) {
        _token = token;
    }

    @Override
    public String getEmail() {
        return _email;
    }

    @Override
    public void setEmail(String email) {
        _email = email;
    }
}
