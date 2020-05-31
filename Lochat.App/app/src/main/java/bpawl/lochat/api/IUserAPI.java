package bpawl.lochat.api;

import java.util.List;

import bpawl.lochat.api.RequestModel.GetUserByEmailRequest;
import bpawl.lochat.model.User;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IUserAPI {
    @POST("User/Get")
    Call<List<User>> getUserByEmail (@Header("Authorization") String credentials, @Body GetUserByEmailRequest request);

    @PUT("User/Update")
    Call<User> updateUser (@Header("Authorization") String credentials, @Body User toUpdate);
}
