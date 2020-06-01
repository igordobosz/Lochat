package bpawl.lochat.api;

import bpawl.lochat.api.RequestModel.AuthRequest;
import bpawl.lochat.api.RequestModel.AuthResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthAPI {
    @POST("Authorization/Google")
    Call<AuthResponse> authorize (@Body AuthRequest request);
}