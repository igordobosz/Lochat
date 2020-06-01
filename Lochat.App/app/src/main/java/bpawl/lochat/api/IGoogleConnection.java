package bpawl.lochat.api;

public interface IGoogleConnection {
    String getToken();
    void setToken(String token);
    String getEmail();
    void setEmail(String email);
}
