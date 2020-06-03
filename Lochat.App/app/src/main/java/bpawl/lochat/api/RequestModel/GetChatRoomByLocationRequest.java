package bpawl.lochat.api.RequestModel;

import com.google.gson.annotations.SerializedName;

public class GetChatRoomByLocationRequest {
    @SerializedName("userLatitude")
    public double latitude;
    @SerializedName("userLongitude")
    public double longitude;
}
