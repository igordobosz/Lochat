package bpawl.lochat.api.RequestModel;

import com.google.gson.annotations.SerializedName;

public class GetChatRoomByOwnerRequest {
    @SerializedName("ownerId")
    public String ownerId;
}
