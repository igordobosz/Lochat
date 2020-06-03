package bpawl.lochat.api.RequestModel;

import com.google.gson.annotations.SerializedName;

public class GetUserByEmailRequest {
    @SerializedName("email")
    public String email;
}